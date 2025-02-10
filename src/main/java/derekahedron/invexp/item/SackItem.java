package derekahedron.invexp.item;

import derekahedron.invexp.Config;
import derekahedron.invexp.item.tooltip.SackTooltip;
import derekahedron.invexp.quiver.QuiverContents;
import derekahedron.invexp.sack.SackContents;
import derekahedron.invexp.sound.InvExpSoundEvents;
import derekahedron.invexp.util.InvExpUtil;
import derekahedron.invexp.util.OpenItemTexturesRegistry;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.apache.commons.lang3.math.Fraction;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Optional;

/**
 * Sack Item. Stores stacks of items in a SackContentsComponent.
 * Items can be used directly from the sack and are automatically inserted into the sack
 * on pickup. Items must be of the same sack type in order to be added.
 */
public class SackItem extends Item {
    public static final int FULL_ITEM_BAR_COLOR = Mth.color(1.0F, 0.33F, 0.33F);
    public static final int ITEM_BAR_COLOR = Mth.color(0.44F, 0.53F, 1.0F);

    /**
     * Class constructor.
     *
     * @param properties item properties to create the sack with
     */
    public SackItem(Item.Properties properties) {
        super(properties);
        OpenItemTexturesRegistry.addItem(this);
    }

    public int getMaxSackTypes() {
        return Config.MAX_SACK_TYPES.get();
    }

    public int getMaxSackWeight() {
        return Config.MAX_SACK_WEIGHT.get();
    }

    public int getMaxSackStacks() {
        return Config.MAX_SACK_STACKS.get();
    }

    /**
     * Handles when a sack stack is clicked over a slot in the inventory.
     * Adds/Removes and item to/from the quiver.
     *
     * @param stack the sack stack held in the cursor
     * @param slot the clicked slot
     * @param clickAction how the stack was clicked
     * @param player the player who clicked the stack
     * @return <code>true</code> if the click had an effect; <code>false</code> otherwise.
     */
    @Override
    @ParametersAreNonnullByDefault
    public boolean overrideStackedOnOther(ItemStack stack, Slot slot, ClickAction clickAction, Player player) {
        SackContents contents = SackContents.of(stack);
        ItemStack otherStack;

        // Make sure this is actually a valid sack
        if (contents == null) {
            return false;
        }
        otherStack = slot.getItem();

        if (clickAction == ClickAction.PRIMARY && !otherStack.isEmpty()) {

            if (!contents.canTryInsert(otherStack)) {
                // Don't do anything if the other stack does not match the types
                return false;
            } else if (contents.add(slot, player) > 0) {
                // If added, play sound and update screen handler
                playInsertSound(player);
                InvExpUtil.onContentChanged(player);
            }
            return true;
        }
        else if (clickAction == ClickAction.SECONDARY && otherStack.isEmpty()) {

            if (contents.popSelectedStack(slot)) {
                // If removed, play sound and update handler
                playRemoveSound(player);
                InvExpUtil.onContentChanged(player);
            }
            // Always return true so sack stays in cursor slot
            return true;
        }
        return false;
    }

    /**
     * Handles when a sack stack is clicked on by in the inventory.
     * Adds/Removes and item to/from the sack.
     *
     * @param stack the sack stack being clicked on
     * @param otherStack the stack held in the cursor
     * @param slot the clicked slot
     * @param clickAction how the stack was clicked
     * @param player player who clicked the stack
     * @param slotAccess reference to the cursor stack
     * @return <code>true</code> if the click had an effect; <code>false</code> otherwise.
     */
    @Override
    @ParametersAreNonnullByDefault
    public boolean overrideOtherStackedOnMe(
            ItemStack stack, ItemStack otherStack, Slot slot, ClickAction clickAction,
            Player player, SlotAccess slotAccess
    ) {
        SackContents contents = SackContents.of(stack);

        // Make sure this is actually a valid sack
        if (contents == null) {
            return false;
        }

        if (clickAction == ClickAction.PRIMARY && !otherStack.isEmpty()) {

            if (!contents.canTryInsert(otherStack)) {
                // Don't do anything if the other stack does not match the types
                return false;
            } else if (contents.add(otherStack) > 0) {
                // If added, play sound and update screen handler
                playInsertSound(player);
                InvExpUtil.onContentChanged(player);
            }
            return true;
        }
        else if (clickAction == ClickAction.SECONDARY && otherStack.isEmpty()) {

            if (slot.allowModification(player)) {
                ItemStack poppedStack = contents.popSelectedStack();

                if (!poppedStack.isEmpty()) {
                    // If removed, play sound and update handler
                    slotAccess.set(poppedStack);
                    playRemoveSound(player);
                    InvExpUtil.onContentChanged(player);
                }
                // Always return true so sack stays in cursor slot
                return true;
            }
        }
        return false;
    }

    /**
     * Shows the item bar if the sack has contents.
     *
     * @param stack sack stack to test
     * @return <code>true</code> if the item bar should be shown; <code>false</code> otherwise
     */
    @Override
    @ParametersAreNonnullByDefault
    public boolean isBarVisible(ItemStack stack) {
        SackContents contents = SackContents.of(stack);
        return contents != null && !contents.isEmpty();
    }

    /**
     * Gets how full the item bar should be based on how full the sack is.
     *
     * @param stack sack stack to test
     * @return int 0-13 representing how full the quiver is
     */
    @Override
    @ParametersAreNonnullByDefault
    public int getBarWidth(ItemStack stack) {
        SackContents contents = SackContents.of(stack);
        if (contents == null) {
            return 0;
        }
        Fraction fillFraction = contents.getFillFraction();
        return Math.min(
                13,
                1 + (fillFraction.getNumerator() * 12 / fillFraction.getDenominator())
        );
    }

    /**
     * Display a different item bar color if the sack is full
     *
     * @param sackStack     sack stack to test
     * @return              color that the sack bar should display
     */
    @Override
    @ParametersAreNonnullByDefault
    public int getBarColor(ItemStack sackStack) {
        SackContents contents = SackContents.of(sackStack);
        if (contents != null && contents.getTotalWeight() < contents.getMaxSackWeight()) {
            return ITEM_BAR_COLOR;
        }
        else {
            return FULL_ITEM_BAR_COLOR;
        }
    }

    /**
     * Adds quiver tooltip data to stack
     *
     * @param sackStack     sack stack to get tooltip from
     * @return              Optional tooltip data to add to the stack
     */
    @Override
    @ParametersAreNonnullByDefault
    public @NotNull Optional<TooltipComponent> getTooltipImage(ItemStack sackStack) {
        SackContents contents = SackContents.of(sackStack);
        if (contents != null) {
            return Optional.of(new SackTooltip(contents));
        }
        return Optional.empty();
    }


    /**
     * Drop all sack contents when item entity is destroyed
     *
     * @param entity    ItemEntity that was destroyed
     */
    @Override
    @ParametersAreNonnullByDefault
    @SuppressWarnings("deprecation")
    public void onDestroyed(ItemEntity entity) {
        QuiverContents contents = QuiverContents.of(entity.getItem());
        if (contents == null || contents.isEmpty()) {
            return;
        }
        ItemUtils.onContainerDestroyed(entity, contents.popAllStacks().stream());
    }

    /**
     * Make sure the contents are validated when ticked in a player inventory.
     * Also ticks the selected stack in the sack.
     *
     * @param sackStack     sack stack to tick
     * @param world         world the stack is ticked in
     * @param entity        the entity holding the item; usually a player
     * @param slot          slot the item is in
     * @param selected      whether the item is in the selected hotbar slot
     */
    @Override
    @ParametersAreNonnullByDefault
    public void inventoryTick(ItemStack sackStack, Level world, Entity entity, int slot, boolean selected) {
        if (entity instanceof Player player) {
            SackContents contents = SackContents.of(sackStack);
            if (contents == null) {
                return;
            }
            contents.validate(player);

            // After contents are validated, try ticking the selected stack
            if (contents.isEmpty()) {
                return;
            }
            ItemStack selectedStack = contents.copySelectedStack();
            selectedStack.getItem().inventoryTick(selectedStack, world, entity, slot, selected);
            contents.updateSelectedStack(selectedStack, leftoverStack -> {
                if (!player.getInventory().add(leftoverStack)) {
                    player.drop(leftoverStack, false);
                }
            });
        }
    }

    /**
     * Plays sack remove sound.
     *
     * @param entity    entity to play sound from
     */
    public void playRemoveSound(@NotNull Entity entity) {
        entity.playSound(
                InvExpSoundEvents.ITEM_SACK_REMOVE_ONE.get(),
                0.8F,
                0.8F + entity.level().getRandom().nextFloat() * 0.4F
        );
    }

    /**
     * Plays sack insert sound.
     *
     * @param entity    entity to play sound from
     */
    public void playInsertSound(@NotNull Entity entity) {
        entity.playSound(
                InvExpSoundEvents.ITEM_SACK_INSERT.get(),
                0.8F,
                0.8F + entity.level().getRandom().nextFloat() * 0.4F
        );
    }
}

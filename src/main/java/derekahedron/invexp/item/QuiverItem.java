package derekahedron.invexp.item;

import derekahedron.invexp.Config;
import derekahedron.invexp.item.tooltip.QuiverTooltip;
import derekahedron.invexp.quiver.QuiverContents;
import derekahedron.invexp.sound.InvExpSoundEvents;
import derekahedron.invexp.util.InvExpUtil;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.level.Level;
import org.apache.commons.lang3.math.Fraction;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Optional;

/**
 * Quiver Item. Stores stacks of arrows in a QuiverContentsComponent.
 * Arrows can be added and removed by clicking the stack, and are also inserted
 * automatically on pickup. Arrows are used directly from the quiver's selected stack.
 */
public class QuiverItem extends Item {
    public static final int FULL_ITEM_BAR_COLOR = Mth.color(1.0F, 0.33F, 0.33F);
    public static final int ITEM_BAR_COLOR = Mth.color(0.44F, 0.53F, 1.0F);

    /**
     * Class constructor.
     *
     * @param properties item properties to create the quiver with
     */
    public QuiverItem(Item.Properties properties) {
        super(properties);
    }

    /**
     * Gets the maximum number of mixed stacks of arrows this quiver can hold.
     *
     * @return the number of mixed stacks of arrows this quiver can hold
     */
    public int getMaxQuiverOccupancyStacks() {
        return Config.MAX_QUIVER_OCCUPANCY_STACKS.get();
    }

    /**
     * Gets the maximum number of total separate stacks allowed in this quiver.
     *
     * @return the number of total stacks this quiver can hold
     */
    public int getMaxQuiverStacks() {
        return Config.MAX_QUIVER_STACKS.get();
    }

    /**
     * Handles when a quiver stack is clicked over a slot in the inventory.
     * Adds/Removes and item to/from the quiver.
     *
     * @param stack the quiver stack held in the cursor
     * @param slot the clicked slot
     * @param clickAction how the stack was clicked
     * @param player the player who clicked the stack
     * @return <code>true</code> if the click had an effect; <code>false</code> otherwise.
     */
    @Override
    @ParametersAreNonnullByDefault
    public boolean overrideStackedOnOther(ItemStack stack, Slot slot, ClickAction clickAction, Player player) {
        QuiverContents contents = QuiverContents.of(stack);
        ItemStack otherStack;

        // Make sure this is actually a valid quiver
        if (contents == null) {
            return false;
        }
        otherStack = slot.getItem();

        if (clickAction == ClickAction.PRIMARY && !otherStack.isEmpty()) {

            if (!contents.canTryInsert(otherStack)) {
                // Don't do anything if the other stack is not an arrow
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
            // Always return true so quiver stays in cursor slot
            return true;
        }
        return false;
    }

    /**
     * Handles when a quiver stack is clicked on by in the inventory.
     * Adds/Removes and item to/from the quiver.
     *
     * @param stack the quiver stack being clicked on
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
            ItemStack stack, ItemStack otherStack, Slot slot, ClickAction clickAction, Player player,
            SlotAccess slotAccess
    ) {
        QuiverContents contents = QuiverContents.of(stack);

        // Make sure this is actually a valid quiver
        if (contents == null) {
            return false;
        }

        if (clickAction == ClickAction.PRIMARY && !otherStack.isEmpty()) {

            if (!contents.canTryInsert(otherStack)) {
                // Don't do anything if the other stack is not an arrow
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
                // Always return true so quiver stays in cursor slot
                return true;
            }
        }
        return false;
    }

    /**
     * Shows the item bar if the quiver has contents.
     *
     * @param stack quiver stack to test
     * @return <code>true</code> if the item bar should be shown; <code>false</code> otherwise
     */
    @Override
    @ParametersAreNonnullByDefault
    public boolean isBarVisible(ItemStack stack) {
        QuiverContents contents = QuiverContents.of(stack);
        return contents != null && !contents.isEmpty();
    }

    /**
     * Gets how full the item bar should be based on how full the quiver is.
     *
     * @param stack quiver stack to test
     * @return int 0-13 representing how full the quiver is
     */
    @Override
    @ParametersAreNonnullByDefault
    public int getBarWidth(ItemStack stack) {
        QuiverContents contents = QuiverContents.of(stack);
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
     * Display a different item bar color if the quiver is full
     *
     * @param stack quiver stack to test
     * @return color that the quiver bar should display
     */
    @Override
    @ParametersAreNonnullByDefault
    public int getBarColor(ItemStack stack) {
        QuiverContents contents = QuiverContents.of(stack);
        if (contents == null || contents.getTotalOccupancy().compareTo(contents.getMaxQuiverOccupancy()) < 0) {
            return ITEM_BAR_COLOR;
        }
        else {
            return FULL_ITEM_BAR_COLOR;
        }
    }

    /**
     * Adds quiver tooltip data to stack
     *
     * @param stack quiver stack to get tooltip from
     * @return Optional tooltip data to add to the stack
     */
    @Override
    @ParametersAreNonnullByDefault
    public @NotNull Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        QuiverContents contents = QuiverContents.of(stack);
        if (contents != null) {
            return Optional.of(new QuiverTooltip(contents));
        }
        return Optional.empty();
    }

    /**
     * Drop all quiver contents when item entity is destroyed
     *
     * @param entity ItemEntity that was destroyed
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
     * Makes sure the contents are validated when ticked in a player inventory.
     *
     * @param quiverStack quiver stack to tick
     * @param level world the stack is ticked in
     * @param entity the entity holding the item; usually a player
     * @param slot slot the item is in
     * @param selected whether the item is in the selected hotbar slot
     */
    @Override
    @ParametersAreNonnullByDefault
    public void inventoryTick(ItemStack quiverStack, Level level, Entity entity, int slot, boolean selected) {
        if (entity instanceof Player player) {
            QuiverContents contents = QuiverContents.of(quiverStack);
            if (contents == null) {
                return;
            }
            contents.validate(player);
        }
    }

    /**
     * Plays the quiver insert sound.
     *
     * @param entity the entity to play the insert sound from
     */
    @ParametersAreNonnullByDefault
    public void playInsertSound(Entity entity) {
        entity.playSound(
                InvExpSoundEvents.ITEM_QUIVER_REMOVE_ONE.get(),
                0.8F,
                0.8F + entity.level().getRandom().nextFloat() * 0.4F
        );
    }

    /**
     * Plays the quiver remove sound.
     *
     * @param entity the entity to play the remove sound from
     */
    @ParametersAreNonnullByDefault
    public void playRemoveSound(Entity entity) {
        entity.playSound(
                InvExpSoundEvents.ITEM_QUIVER_INSERT.get(),
                0.8F,
                0.8F + entity.level().getRandom().nextFloat() * 0.4F
        );
    }
}

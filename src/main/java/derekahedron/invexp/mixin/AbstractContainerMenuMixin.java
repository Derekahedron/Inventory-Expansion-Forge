package derekahedron.invexp.mixin;

import derekahedron.invexp.item.SackItem;
import derekahedron.invexp.item.QuiverItem;
import derekahedron.invexp.sack.SackContents;
import derekahedron.invexp.sack.SacksHelper;
import derekahedron.invexp.util.ContainerItemContents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;

@Mixin(AbstractContainerMenu.class)
public class AbstractContainerMenuMixin {

    /**
     * Adds functionality for double-clicking sacks and quivers in an inventory.
     * Doing so picks up all items that can be added and inserts them into the container.
     */
    @Inject(
            method = "doClick",
            at = @At("HEAD"),
            cancellable = true
    )
    private void doubleClickContainerItem(
            int slotIndex, int button, ClickType clickType, Player player, @NotNull CallbackInfo ci
    ) {
        AbstractContainerMenu self = (AbstractContainerMenu) (Object) this;
        // First, make sure it was a double click on a valid slot
        ItemStack cursorStack = self.getCarried();
        if (slotIndex < 0 || clickType != ClickType.PICKUP_ALL) {
            return;
        }
        Slot selectedSlot = self.slots.get(slotIndex);
        if (selectedSlot.hasItem() && selectedSlot.allowModification(player)) {
            return;
        }

        // Check that the item is a valid container
        ContainerItemContents contents = ContainerItemContents.of(cursorStack);
        if (contents == null || (contents.isEmpty() && contents instanceof SackContents)) {
            return;
        }

        // First pull from slots that aren't full, then pull from slots that are full.
        // This is in line with vanilla behavior.
        ArrayList<Slot> partialSlots = new ArrayList<>(self.slots.size());
        ArrayList<Slot> fullSlots = new ArrayList<>(self.slots.size());
        for (Slot slot : self.slots) {
            if (slot.hasItem() && self.canTakeItemForPickAll(cursorStack, slot)) {
                ItemStack stack = slot.getItem();
                if (stack.getCount() < stack.getMaxStackSize()) {
                    partialSlots.add(slot);
                }
                else {
                    fullSlots.add(slot);
                }
            }
        }

        // Following vanilla behavior, if button != 0, reverse order.
        if (button != 0) {
            Collections.reverse(partialSlots);
            Collections.reverse(fullSlots);
        }

        // Combine slot streams
        Stream<Slot> slots = Stream.concat(partialSlots.stream(), fullSlots.stream());

        if (contents instanceof SackContents sackContents) {
            // Only add slots that already have their type in the sack
            slots = slots.filter(slot -> sackContents.isInTypes(SacksHelper.getSackType(slot.getItem())));
        }

        // Try adding all slots and play sound if successful
        if (contents.add(slots, player) > 0) {
            if (cursorStack.getItem() instanceof SackItem sackItem) {
                sackItem.playInsertSound(player);
            }
            else if (cursorStack.getItem() instanceof QuiverItem quiverItem) {
                quiverItem.playInsertSound(player);
            }
        }
        ci.cancel();
    }
}

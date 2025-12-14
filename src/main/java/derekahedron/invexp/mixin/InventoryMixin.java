package derekahedron.invexp.mixin;

import derekahedron.invexp.entity.player.PlayerEntityDuck;
import derekahedron.invexp.quiver.QuiverContents;
import derekahedron.invexp.sack.SackContents;
import derekahedron.invexp.sack.SackContentsReader;
import derekahedron.invexp.sack.SackUsage;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Inventory.class)
public class InventoryMixin {
    /**
     * When an item is inserted into the players inventory, first try inserting into quivers and sacks.
     * First search for quivers. If there are no quivers, search again for sacks.
     */
    @Inject(
            method = "add(Lnet/minecraft/world/item/ItemStack;)Z",
            at = @At("HEAD"),
            cancellable = true
    )
    private void insertIntoContainerItem(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        Inventory self = (Inventory) (Object) this;

        // First attempt pickup into quivers
        // Start by attempting pickup into main hand
        if (QuiverContents.attemptPickup(self.getItem(self.selected), stack)) {
            cir.setReturnValue(true);
            return;
        }
        // Then try offhand
        else if (QuiverContents.attemptPickup(self.getItem(40), stack)) {
            cir.setReturnValue(true);
            return;
        }
        else {
            // Then try remaining slots
            for (int slot = 0; slot < self.items.size(); slot++) {
                if (QuiverContents.attemptPickup(self.getItem(slot), stack)) {
                    cir.setReturnValue(true);
                    return;
                }
            }
        }

        // Next try picking up into sacks
        // Start with main hand
        if (SackContents.attemptPickup(self.getItem(self.selected), stack, self.player)) {
            cir.setReturnValue(true);
        }
        // Then try offhand
        else if (SackContents.attemptPickup(self.getItem(40), stack, self.player)) {
            cir.setReturnValue(true);
        }
        else {
            // Finally try remaining slots
            for (int slot = 0; slot < self.items.size(); slot++) {
                if (SackContents.attemptPickup(self.getItem(slot), stack, self.player)) {
                    cir.setReturnValue(true);
                    break;
                }
            }
        }
    }

    /**
     * When removing an item from the player inventory, if the player is currently using
     * the sack, remove that item if it is in one of the players usages.
     */
    @Inject(
            method = "removeItem(Lnet/minecraft/world/item/ItemStack;)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private void removeFromSack(ItemStack stack, CallbackInfo ci) {
        Inventory self = (Inventory) (Object) this;
        if (((PlayerEntityDuck) self.player).invexp_$isUsingSack()) {
            SackUsage usage = ((PlayerEntityDuck) self.player).invexp_$getUsageForSelectedStack(stack);
            if (usage != null) {
                usage.selectedStack = ItemStack.EMPTY;
                ci.cancel();
            }
        }
    }

    /**
     * Dropping an item from a sack should drop one of the selected item if the entire stack
     * isn't being dropped.
     */
    @Inject(
            method = "removeFromSelected",
            at = @At("HEAD"),
            cancellable = true
    )
    private void dropSelectedItemFromSack(boolean entireStack, CallbackInfoReturnable<ItemStack> info) {
        // Dropping the entire stack should drop the sack
        if (entireStack) {
            return;
        }
        Inventory self = (Inventory) (Object) this;
        ItemStack sackStack = self.getSelected();
        SackContents contents = SackContents.of(sackStack, self.player.level());
        if (contents == null || contents.isEmpty()) {
            return;
        }
        info.setReturnValue(contents.popSelectedItem());
    }

    /**
     * After getting a slot with the stack fails, try again but instead check
     * for sacks with the stack in their contents.
     */
    @Inject(
            method = "findSlotMatchingItem",
            at = @At("RETURN"),
            cancellable = true
    )
    private void getSlotWithStackInSack(ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        // only run if the normal function does not find anything.
        if (cir.getReturnValue() != -1) {
            return;
        }
        Inventory self = (Inventory) (Object) this;

        for (int slot = 0; slot < self.items.size(); slot++) {
            SackContentsReader contents = SackContents.of(self.items.get(slot));
            if (contents != null && !contents.isEmpty()) {
                if (ItemStack.isSameItemSameTags(stack, contents.getSelectedStack())) {
                    // Find first sack that has the item selected already
                    cir.setReturnValue(slot);
                    return;
                }
            }
        }
    }
}

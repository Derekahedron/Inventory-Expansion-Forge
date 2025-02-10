package derekahedron.invexp.mixin;

import derekahedron.invexp.util.ContainerItemContents;
import net.minecraft.world.inventory.ShulkerBoxSlot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ShulkerBoxSlot.class)
public class ShulkerBoxSlotMixin {

    /**
     * This tests if an item can be inserted into a shulker box. If it can,
     * we want to make sure that this item is does not contain items that cannot be inserted.
     * This doesn't really have a use in vanilla, but can possibly prevent infinite stacking for mods.
     */
    @Inject(
            method = "mayPlace",
            at = @At("RETURN"),
            cancellable = true
    )
    private void mayPlaceContainerItem(ItemStack stack, @NotNull CallbackInfoReturnable<Boolean> cir) {
        if (cir.getReturnValue()) {
            ContainerItemContents contents = ContainerItemContents.of(stack);
            if (contents != null && !contents.isEmpty()) {
                ShulkerBoxSlot self = (ShulkerBoxSlot) (Object) this;
                for (ItemStack nestedStack : contents.getStacks()) {
                    if (!self.mayPlace(nestedStack)) {
                        cir.setReturnValue(false);
                        return;
                    }
                }
            }
        }
    }
}

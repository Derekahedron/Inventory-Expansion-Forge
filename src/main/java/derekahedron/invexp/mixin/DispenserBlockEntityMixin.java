package derekahedron.invexp.mixin;

import derekahedron.invexp.block.entity.DispenserBlockEntityDuck;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.DispenserBlockEntity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(DispenserBlockEntity.class)
public class DispenserBlockEntityMixin implements DispenserBlockEntityDuck {

    @Unique
    List<ItemStack> invexp$usageBuffer;

    @Override
    public void invexp$setUsageBuffer(@Nullable List<ItemStack> usageBuffer) {
        this.invexp$usageBuffer = usageBuffer;
    }

    /**
     * If the dispenser currently has a usage buffer, a sack is being used.
     * Add the stacks to the buffer instead.
     */
    @Inject(
            method = "addItem",
            at = @At("HEAD"),
            cancellable = true
    )
    public void catchAddedStacks(ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        if (invexp$usageBuffer != null) {
            invexp$usageBuffer.add(stack);
            cir.setReturnValue(stack.getCount());
        }
    }
}

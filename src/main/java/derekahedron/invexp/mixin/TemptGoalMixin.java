package derekahedron.invexp.mixin;

import derekahedron.invexp.sack.SackContents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TemptGoal.class)
public class TemptGoalMixin {

    /**
     * Modify the argument here to test for the held item in the sack.
     */
    @Inject(
            method = "shouldFollow",
            at = @At(value = "RETURN"),
            cancellable = true
    )
    private void shouldFollowSelectedStack(LivingEntity entity, CallbackInfoReturnable<Boolean> cir) {
        if (!cir.getReturnValue()) {
            TemptGoal self = (TemptGoal) (Object) this;
            if (self.items.test(SackContents.selectedStackOf(entity.getMainHandItem())) || self.items.test(SackContents.selectedStackOf(entity.getOffhandItem()))) {
                cir.setReturnValue(true);
            }
        }
    }
}

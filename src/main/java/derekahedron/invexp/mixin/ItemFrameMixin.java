package derekahedron.invexp.mixin;

import derekahedron.invexp.entity.player.PlayerEntityDuck;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemFrame.class)
public class ItemFrameMixin {

    /**
     * Stop using the sack early when interacting with ItemFrame.
     */
    @Inject(
            method = "interact",
            at = @At("HEAD")
    )
    private void stopUsingSack(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        ((PlayerEntityDuck) player).invexp_$stopUsingSack();
    }
}

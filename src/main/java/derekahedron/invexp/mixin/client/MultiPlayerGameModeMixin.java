package derekahedron.invexp.mixin.client;

import derekahedron.invexp.entity.player.PlayerEntityDuck;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MultiPlayerGameMode.class)
public class MultiPlayerGameModeMixin {

    /**
     * Start player using sack before they stop using an item.
     */
    @Inject(
            method = "releaseUsingItem",
            at = @At("HEAD")
    )
    private void beforeStopUsingItem(Player player, CallbackInfo ci) {
        ((PlayerEntityDuck) player).invexp_$startUsingSack();
    }

    /**
     * Stop player using sack after they stop using an item.
     */
    @Inject(
            method = "releaseUsingItem",
            at = @At("RETURN")
    )
    private void afterStopUsingItem(Player player, CallbackInfo ci) {
        ((PlayerEntityDuck) player).invexp_$stopUsingSack();
    }
}

package derekahedron.invexp.mixin;

import derekahedron.invexp.entity.player.PlayerEntityDuck;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    /**
     * Start player using sack before ticking active item stack.
     */
    @Inject(
            method = "updatingUsingItem",
            at = @At("HEAD")
    )
    private void beforeTickActiveItemStack(@NotNull CallbackInfo ci) {
        LivingEntity self = (LivingEntity) (Object) this;
        if (self instanceof Player player) {
            ((PlayerEntityDuck) player).invexp$startUsingSack();
        }
    }

    /**
     * Stop player using sack after ticking active item stack.
     */
    @Inject(
            method = "updatingUsingItem",
            at = @At("RETURN")
    )
    private void afterTickActiveItemStack(@NotNull CallbackInfo ci) {
        LivingEntity self = (LivingEntity) (Object) this;
        if (self instanceof Player player) {
            ((PlayerEntityDuck) player).invexp$stopUsingSack();
        }
    }

    /**
     * Start player using sack before setting tracked data.
     */
    @Inject(
            method = "onSyncedDataUpdated",
            at = @At("HEAD")
    )
    private void beforeTrackedDataSet(EntityDataAccessor<?> data, CallbackInfo ci) {
        LivingEntity self = (LivingEntity) (Object) this;
        if (self instanceof Player player &&
                player.level().isClientSide &&
                LivingEntity.DATA_LIVING_ENTITY_FLAGS.equals(data)
        ) {
            ((PlayerEntityDuck) player).invexp$startUsingSack();
        }
    }

    /**
     * Stop player using sack after setting tracked data.
     */
    @Inject(
            method = "onSyncedDataUpdated",
            at = @At("RETURN")
    )
    private void afterTrackedDataSet(EntityDataAccessor<?> data, CallbackInfo ci) {
        LivingEntity self = (LivingEntity) (Object) this;
        if (self instanceof Player player &&
                player.level().isClientSide &&
                LivingEntity.DATA_LIVING_ENTITY_FLAGS.equals(data)
        ) {
            ((PlayerEntityDuck) player).invexp$stopUsingSack();
        }
    }

    /**
     * Start player using sack before trying to use death protector.
     */
    @Inject(
            method = "checkTotemDeathProtection",
            at = @At("HEAD")
    )
    private void beforeUseDeathProtector(DamageSource source, @NotNull CallbackInfoReturnable<Boolean> cir) {
        LivingEntity self = (LivingEntity) (Object) this;
        if (self instanceof Player player) {
            ((PlayerEntityDuck) player).invexp$startUsingSack();
        }
    }

    /**
     * Stop player using sack after trying to use death protector.
     */
    @Inject(
            method = "checkTotemDeathProtection",
            at = @At("RETURN")
    )
    private void afterUseDeathProtector(DamageSource source, @NotNull CallbackInfoReturnable<Boolean> cir) {
        LivingEntity self = (LivingEntity) (Object) this;
        if (self instanceof Player player) {
            ((PlayerEntityDuck) player).invexp$stopUsingSack();
        }
    }
}

package derekahedron.invexp.mixin;

import derekahedron.invexp.quiver.QuiverContents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Predicate;

@Mixin(ProjectileWeaponItem.class)
public class ProjectileWeaponItemMixin {

    /**
     * When getting the held projectile, first check if a quiver is being held,
     * then check the contents of that quiver. If there is a match, return a created
     * QuiveredItemStack.
     */
    @Inject(
            method = "getHeldProjectile",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void getHeldProjectileFromQuiver(
            LivingEntity entity, Predicate<ItemStack> predicate, CallbackInfoReturnable<ItemStack> cir
    ) {
        // Check hands in reverse (Offhand first)
        for (int i = InteractionHand.values().length - 1; i >= 0; i--) {
            InteractionHand hand = InteractionHand.values()[i];

            QuiverContents contents = QuiverContents.of(entity.getItemInHand(hand));
            if (contents != null) {
                ItemStack projectile = contents.getProjectileStack(predicate);
                if (!projectile.isEmpty()) {
                    cir.setReturnValue(projectile);
                    return;
                }
            }
        }
    }
}

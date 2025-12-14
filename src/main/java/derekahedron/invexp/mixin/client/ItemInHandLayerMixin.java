package derekahedron.invexp.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import derekahedron.invexp.sack.SackContents;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemInHandLayer.class)
public class ItemInHandLayerMixin {


    /**
     * Use the selected stack for the main hand when getting the hand render type.
     */
    @ModifyVariable(
            method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/LivingEntity;FFFFFF)V",
            at = @At("STORE"),
            ordinal = 0
    )
    private ItemStack getRenderTypeForMainHand(
            ItemStack stack,
            PoseStack p_117204_,
            MultiBufferSource p_117205_,
            int p_117206_,
            LivingEntity p_117207_,
            float p_117208_,
            float p_117209_,
            float p_117210_,
            float p_117211_,
            float p_117212_,
            float p_117213_) {
        if (p_117207_ instanceof Player player && SackContents.selectedStackOf(player, stack) == player.getUseItem()) {
            return player.getUseItem();
        }
        return stack;
    }

    /**
     * Use the selected stack for the offhand when getting the hand render type.
     */
    @ModifyVariable(
            method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/LivingEntity;FFFFFF)V",
            at = @At("STORE"),
            ordinal = 1
    )
    private ItemStack getRenderTypeForOffHand(
            ItemStack stack,
            PoseStack p_117204_,
            MultiBufferSource p_117205_,
            int p_117206_,
            LivingEntity p_117207_,
            float p_117208_,
            float p_117209_,
            float p_117210_,
            float p_117211_,
            float p_117212_,
            float p_117213_) {
        if (p_117207_ instanceof Player player && SackContents.selectedStackOf(player, stack) == player.getUseItem()) {
            return player.getUseItem();
        }
        return stack;
    }
}

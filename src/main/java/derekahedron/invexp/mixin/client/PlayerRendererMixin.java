package derekahedron.invexp.mixin.client;

import derekahedron.invexp.sack.SackContents;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(PlayerRenderer.class)
public class PlayerRendererMixin {

    /**
     * When updating the render state, we check the stack in the hand to see if
     * it is a spyglass. This checks the selected stack so the spyglass is properly rendered
     * when used.
     */
    @ModifyVariable(
            method = "getArmPose",
            at = @At("STORE"),
            ordinal = 0
    )
    private static @NotNull ItemStack getSelectedStackInHand(ItemStack stack, AbstractClientPlayer player, InteractionHand hand) {
        if (player.isUsingItem() && player.getUsedItemHand() == hand) {
            return SackContents.selectedStackOf(player, stack);
        }
        return stack;
    }
}

package derekahedron.invexp.mixin.client;

import derekahedron.invexp.sack.SackContents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemInHandRenderer.class)
public class ItemInHandRendererMixin {

    @Shadow @Final private Minecraft minecraft;

    /**
     * Use the selected stack for the main hand when getting the hand render type.
     */
    @ModifyVariable(
            method = "evaluateWhichHandsToRender",
            at = @At("STORE"),
            ordinal = 0
    )
    private static @NotNull ItemStack getRenderTypeForMainHand(ItemStack stack, LocalPlayer player) {
        return SackContents.selectedStackOf(player, stack);
    }

    /**
     * Use the selected stack for the offhand when getting the hand render type.
     */
    @ModifyVariable(
            method = "evaluateWhichHandsToRender",
            at = @At("STORE"),
            ordinal = 1
    )
    private static @NotNull ItemStack getRenderTypeForOffHand(ItemStack stack, LocalPlayer player) {
        return SackContents.selectedStackOf(player, stack);
    }

    /**
     * Check if the selected stack is a charged crossbow to get the proper hand render type.
     */
    @ModifyArg(
            method = "selectionUsingItemWhileHoldingBowLike",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/ItemInHandRenderer;isChargedCrossbow(Lnet/minecraft/world/item/ItemStack;)Z"
            )
    )
    private static ItemStack isSackChargedCrossbow(ItemStack stack) {
        SackContents contents = SackContents.of(stack);
        if (contents == null || contents.isEmpty()) {
            return stack;
        }
        return contents.getSelectedStack();
    }

    /**
     * Renders the selected stack in the main hand
     */
    @ModifyVariable(
            method = "tick",
            at = @At("STORE"),
            ordinal = 0
    )
    private @NotNull ItemStack updateMainHandSelectedStack(ItemStack stack) {
        return SackContents.selectedStackOf(minecraft.player, stack);
    }

    /**
     * Renders the selected stack in the offhand
     */
    @ModifyVariable(
            method = "tick",
            at = @At("STORE"),
            ordinal = 1
    )
    private @NotNull ItemStack updateOffHandSelectedStack(ItemStack stack) {
        return SackContents.selectedStackOf(minecraft.player, stack);
    }
}

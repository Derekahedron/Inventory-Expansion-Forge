package derekahedron.invexp.mixin.client;

import derekahedron.invexp.client.util.OpenItemTextures;
import derekahedron.invexp.sack.SackContents;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class GuiMixin {

    @Shadow @Final protected Minecraft minecraft;
    @Unique
    private SackContents invexp$openContents;

    /**
     * Check if a sack is selected. If so, store the components so we don't have to repeat the calculation.
     * Then make a scissor area to prevent the item from rendering outside the sack.
     */
    @Inject(
            method = "renderSlot",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;getPopTime()I"
            )
    )
    private void calculateSackContentsComponent(
            GuiGraphics context, int x, int y, float tickCounter, Player player, ItemStack stack,
            int seed, @NotNull CallbackInfo ci
    ) {
        invexp$openContents = null;
        if (stack != player.getMainHandItem() && stack != player.getOffhandItem()) {
            return;
        }
        SackContents contents = SackContents.of(stack);
        if (contents != null && !contents.isEmpty()) {
            invexp$openContents = contents;
            context.enableScissor(x, y - 16, x + 16, y + 16);
        }
    }

    /**
     * If there is a non-null contents component, we want to render the
     * selected stack instead. We do this instead of rendering the component via
     * the model as that would make the sack overlay affected by the
     * bobbing animation, which looks odd.
     */
    @ModifyArg(
            method = "renderSlot",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphics;renderItem(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;III)V"
            )
    )
    private ItemStack renderSelectedStack(ItemStack stack) {
        if (invexp$openContents != null) {
            return SackContents.selectedStackOf(minecraft.player, stack);
        }
        return stack;
    }

    /**
     * Renders the sack overlay and the count of the matching items in the sacks.
     */
    @Inject(
            method = "renderSlot",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphics;renderItemDecorations(Lnet/minecraft/client/gui/Font;Lnet/minecraft/world/item/ItemStack;II)V"
            )
    )
    private void renderSackCount(
            GuiGraphics context, int x, int y, float tickCounter, Player player, ItemStack stack,
            int seed, @NotNull CallbackInfo ci
    ) {
        if (invexp$openContents != null) {
            // Close scissor area
            context.disableScissor();

            OpenItemTextures.renderOpenItem(context, stack, x, y, player.level(), player, seed);

            // Gather total count of nested items that match the selected stack
            ItemStack selectedStack = invexp$openContents.getSelectedStack();
            int maxCount = selectedStack.getMaxStackSize();
            int count = 0;
            for (ItemStack nestedStack : invexp$openContents.getStacks()) {
                if (ItemStack.isSameItemSameTags(nestedStack, selectedStack)) {
                    count += nestedStack.getCount();
                    if (count > maxCount) {
                        // If max count is surpassed, return early
                        break;
                    }
                }
            }

            // Do not render for counts 1 and below
            if (count <= 1) {
                return;
            }

            // If count surpasses max count, use max count but render as yellow
            String countLabel;
            if (count <= maxCount) {
                countLabel = String.valueOf(count);
            }
            else {
                countLabel = ChatFormatting.YELLOW + String.valueOf(maxCount);
            }

            // Render count
            Font renderer = minecraft.font;
            context.pose().pushPose();
            context.pose().translate(0.0D, 0.0D, 200.0F);
            context.drawString(renderer, countLabel, x + 19 - 2 - renderer.width(countLabel), y, 0xFFFFFF, true);
            context.pose().popPose();
        }
    }
}

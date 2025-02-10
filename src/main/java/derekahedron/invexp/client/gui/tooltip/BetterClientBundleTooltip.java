package derekahedron.invexp.client.gui.tooltip;

import derekahedron.invexp.bundle.BundleContents;
import derekahedron.invexp.bundle.BundleHelper;
import derekahedron.invexp.item.tooltip.BetterBundleTooltip;
import derekahedron.invexp.util.InvExpUtil;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.apache.commons.lang3.math.Fraction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BetterClientBundleTooltip implements ClientTooltipComponent, ContainerItemTooltipComponent {
    public static final ResourceLocation BUNDLE_PROGRESS_BAR_BORDER_TEXTURE = InvExpUtil.location("textures/gui/container/bundle/bundle_progressbar_border.png");
    public static final ResourceLocation BUNDLE_PROGRESS_BAR_FILL_TEXTURE = InvExpUtil.location("textures/gui/container/bundle/bundle_progressbar_fill.png");
    public static final ResourceLocation BUNDLE_PROGRESS_BAR_FULL_TEXTURE = InvExpUtil.location("textures/gui/container/bundle/bundle_progressbar_full.png");
    public static final ResourceLocation BUNDLE_SLOT_HIGHLIGHT_BACK_TEXTURE = InvExpUtil.location("textures/gui/container/bundle/slot_highlight_back.png");
    public static final ResourceLocation BUNDLE_SLOT_HIGHLIGHT_FRONT_TEXTURE = InvExpUtil.location("textures/gui/container/bundle/slot_highlight_front.png");
    public static final ResourceLocation BUNDLE_SLOT_BACKGROUND_TEXTURE = InvExpUtil.location("textures/gui/container/bundle/slot_background.png");
    public static final Component BUNDLE_FULL = Component.translatable("item.invexp.bundle.full");
    public static final Component BUNDLE_EMPTY = Component.translatable("item.invexp.bundle.empty");
    public static final Component BUNDLE_TOO_MANY_STACKS = Component.translatable("item.invexp.bundle.too_many_stacks");
    public static final Component BUNDLE_EMPTY_DESCRIPTION = Component.translatable("item.invexp.bundle.description.empty");
    public static final String BUNDLE_EMPTY_DESCRIPTION_PLURAL = "item.invexp.bundle.description.empty.plural";
    private final BundleContents contents;
    private Font lastFont;

    public BetterClientBundleTooltip(BetterBundleTooltip tooltip) {
        contents = tooltip.contents();
    }

    /**
     * Draws the bundle tooltip by first drawing either the contents or an empty description,
     * then finally drawing a progress bar.
     *
     * @param textRenderer  text renderer
     * @param x             x position to draw tooltip at
     * @param y             y position to draw tooltip at
     * @param drawContext   draw context
     */
    @Override
    public void renderImage(
            @NotNull Font textRenderer, int x, int y, @NotNull GuiGraphics drawContext
    ) {
        int width = getTooltipWidth();
        int top = y;
        if (contents.isEmpty()) {
            if (lastFont != null) {
                // Draw empty description
                Component bundleEmptyDescription = getBundleEmptyDescription();
                drawContext.drawWordWrap(
                        textRenderer, bundleEmptyDescription, x + getXMargin(width), y,
                        getTooltipWidth(), DESCRIPTION_TEXT_COLOR
                );
                y += getHeight(bundleEmptyDescription, textRenderer);
            }
        } else {
            // Draw contents
            drawContents(textRenderer, x, y, width, top, drawContext);
            y += getContentsHeight();
        }
        // Finally draw progress bar
        y += getProgressBarPadding();
        drawProgressBar(textRenderer, x, y, width, drawContext);
        // y += getProgressBarPadding();
    }

    /**
     * Gets the width this tooltip requires.
     *
     * @param textRenderer  text renderer
     * @return              width the tooltip takes up
     */
    @Override
    public int getWidth(@Nullable Font textRenderer) {
        lastFont = textRenderer;
        return getTooltipWidth();
    }

    /**
     * Gets the height this tooltip requires
     *
     * @return              height the tooltip takes up
     */
    @Override
    public int getHeight() {
        int height = 0;
        if (contents.isEmpty()) {
            if (lastFont != null) {
                height += getHeight(getBundleEmptyDescription(), lastFont);
            }
        } else {
            height += getContentsHeight();
        }
        height += getProgressBarPadding();
        height += getProgressBarHeight();
        height += getProgressBarPadding();
        return height;
    }

    /**
     * Generates description for an empty bundle.
     *
     * @return  empty bundle description text
     */
    public @NotNull Component getBundleEmptyDescription() {
        int maxStacks = BundleHelper.getMaxBundleWeightStacks(contents.bundleStack);
        if (maxStacks != 1) {
            return Component.translatable(BUNDLE_EMPTY_DESCRIPTION_PLURAL, maxStacks);
        }
        else {
            return BUNDLE_EMPTY_DESCRIPTION;
        }
    }

    /**
     * @return  list of stacks from the contents
     */
    @Override
    public @NotNull List<ItemStack> getStacks() {
        return contents.getStacks();
    }

    /**
     * @return  selected index of the contents
     */
    @Override
    public int getSelectedIndex() {
        return contents.getSelectedIndex();
    }

    /**
     * @return  fullness fraction of the contents
     */
    @Override
    public @NotNull Fraction getFillFraction() {
        return contents.getFillFraction();
    }

    /**
     * Gets a different progress bar texture depending on if the contents are full.
     *
     * @return  texture identifier for the progress bar
     */
    @Override
    public @NotNull ResourceLocation getProgressBarFillTexture() {
        if (contents.isFull()) {
            return BUNDLE_PROGRESS_BAR_FULL_TEXTURE;
        } else {
            return BUNDLE_PROGRESS_BAR_FILL_TEXTURE;
        }
    }

    /**
     * @return  texture identifier for the progress bar border
     */
    @Override
    public @NotNull ResourceLocation getProgressBarBorderTexture() {
        return BUNDLE_PROGRESS_BAR_BORDER_TEXTURE;
    }

    /**
     * @return  texture identifier for the slot background texture
     */
    @Override
    public @NotNull ResourceLocation getSlotBackgroundTexture() {
        return BUNDLE_SLOT_BACKGROUND_TEXTURE;
    }

    /**
     * @return  texture identifier for highlighted back texture
     */
    @Override
    public @NotNull ResourceLocation getSlotHighlightBackTexture() {
        return BUNDLE_SLOT_HIGHLIGHT_BACK_TEXTURE;
    }

    /**
     * @return  texture identifier for highlighted front texture
     */
    @Override
    public @NotNull ResourceLocation getSlotHighlightFrontTexture() {
        return BUNDLE_SLOT_HIGHLIGHT_FRONT_TEXTURE;
    }

    /**
     * Generate text to display on the progress bar. Either FULL, EMPTY,
     * TOO MANY STACKS, or nothing.
     *
     * @return  text to overlay on the progress bar
     */
    @Override
    public @Nullable Component getProgressBarLabel() {
        if (contents.getTotalWeight() >= contents.getMaxBundleWeight()) {
            return BUNDLE_FULL;
        }
        else if (getStacks().size() >= contents.getMaxBundleStacks()) {
            return BUNDLE_TOO_MANY_STACKS;
        }
        else if (contents.isEmpty()) {
            return BUNDLE_EMPTY;
        }
        else {
            return null;
        }
    }
}

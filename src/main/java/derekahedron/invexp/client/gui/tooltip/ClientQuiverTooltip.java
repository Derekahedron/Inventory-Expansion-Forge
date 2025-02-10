package derekahedron.invexp.client.gui.tooltip;

import derekahedron.invexp.quiver.QuiverContents;
import derekahedron.invexp.quiver.QuiverHelper;
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

/**
 * Special tooltip for displaying quivers and their contents.
 */
public class ClientQuiverTooltip implements ClientTooltipComponent, ContainerItemTooltipComponent {
    public static final ResourceLocation QUIVER_PROGRESS_BAR_BORDER_TEXTURE = InvExpUtil.location("textures/gui/container/quiver/quiver_progressbar_border.png");
    public static final ResourceLocation QUIVER_PROGRESS_BAR_FILL_TEXTURE = InvExpUtil.location("textures/gui/container/quiver/quiver_progressbar_fill.png");
    public static final ResourceLocation QUIVER_PROGRESS_BAR_FULL_TEXTURE = InvExpUtil.location("textures/gui/container/quiver/quiver_progressbar_full.png");
    public static final ResourceLocation QUIVER_SLOT_HIGHLIGHT_BACK_TEXTURE = InvExpUtil.location("textures/gui/container/quiver/slot_highlight_back.png");
    public static final ResourceLocation QUIVER_SLOT_HIGHLIGHT_FRONT_TEXTURE = InvExpUtil.location("textures/gui/container/quiver/slot_highlight_front.png");
    public static final ResourceLocation QUIVER_SLOT_BACKGROUND_TEXTURE = InvExpUtil.location("textures/gui/container/quiver/slot_background.png");
    public static final Component QUIVER_FULL = Component.translatable("item.invexp.quiver.full");
    public static final Component QUIVER_EMPTY = Component.translatable("item.invexp.quiver.empty");
    public static final Component QUIVER_TOO_MANY_STACKS = Component.translatable("item.invexp.quiver.too_many_stacks");
    public static final Component QUIVER_EMPTY_DESCRIPTION = Component.translatable("item.invexp.quiver.description.empty");
    public static final String QUIVER_EMPTY_DESCRIPTION_PLURAL = "item.invexp.quiver.description.empty.plural";
    public final QuiverContents contents;
    private Font lastFont;

    public ClientQuiverTooltip(QuiverContents contents) {
        this.contents = contents;
    }

    /**
     * Draws the quiver tooltip by first drawing either the contents or an empty description,
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
                Component quiverEmptyDescription = getQuiverEmptyDescription();
                drawContext.drawWordWrap(
                        textRenderer, quiverEmptyDescription, x + getXMargin(width), y,
                        getTooltipWidth(), DESCRIPTION_TEXT_COLOR
                );
                y += getHeight(quiverEmptyDescription, textRenderer);
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
                height += getHeight(getQuiverEmptyDescription(), lastFont);
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
     * Generates description for an empty quiver.
     *
     * @return  empty quiver description text
     */
    public @NotNull Component getQuiverEmptyDescription() {
        int maxStacks = QuiverHelper.getMaxQuiverOccupancyStacks(contents.quiverStack);
        if (maxStacks != 1) {
            return Component.translatable(QUIVER_EMPTY_DESCRIPTION_PLURAL, maxStacks);
        }
        else {
            return QUIVER_EMPTY_DESCRIPTION;
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
            return QUIVER_PROGRESS_BAR_FULL_TEXTURE;
        } else {
            return QUIVER_PROGRESS_BAR_FILL_TEXTURE;
        }
    }

    /**
     * @return  texture identifier for the progress bar border
     */
    @Override
    public @NotNull ResourceLocation getProgressBarBorderTexture() {
        return QUIVER_PROGRESS_BAR_BORDER_TEXTURE;
    }

    /**
     * @return  texture identifier for the slot background texture
     */
    @Override
    public @NotNull ResourceLocation getSlotBackgroundTexture() {
        return QUIVER_SLOT_BACKGROUND_TEXTURE;
    }

    /**
     * @return  texture identifier for highlighted back texture
     */
    @Override
    public @NotNull ResourceLocation getSlotHighlightBackTexture() {
        return QUIVER_SLOT_HIGHLIGHT_BACK_TEXTURE;
    }

    /**
     * @return  texture identifier for highlighted front texture
     */
    @Override
    public @NotNull ResourceLocation getSlotHighlightFrontTexture() {
        return QUIVER_SLOT_HIGHLIGHT_FRONT_TEXTURE;
    }

    /**
     * Generate text to display on the progress bar. Either FULL, EMPTY,
     * TOO MANY STACKS, or nothing.
     *
     * @return  text to overlay on the progress bar
     */
    @Override
    public @Nullable Component getProgressBarLabel() {
        if (contents.getTotalOccupancy().compareTo(contents.getMaxQuiverOccupancy()) >= 0) {
            return QUIVER_FULL;
        }
        else if (getStacks().size() >= contents.getMaxQuiverStacks()) {
            return QUIVER_TOO_MANY_STACKS;
        }
        else if (contents.isEmpty()) {
            return QUIVER_EMPTY;
        }
        else {
            return null;
        }
    }
}

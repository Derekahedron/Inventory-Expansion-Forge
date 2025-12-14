package derekahedron.invexp.client.gui.tooltip;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import org.apache.commons.lang3.math.Fraction;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Adds functions for rendering container item tooltips. Most of the default values
 * can be modified by overriding functions. You can do this to allow as many rows as you wish
 * as well as increase items in a row. This will probably never be used as the default values work well.
 */
public interface ContainerItemTooltipComponent {
    int DEFAULT_MAX_ROWS = 3;
    int MIN_MAX_ROWS = 3; // Hard coded minimum. If less rows are displayed, odd behavior may occur
    int DEFAULT_SLOT_LENGTH = 24;
    int DEFAULT_ROW_WIDTH = 4;
    int DEFAULT_PROGRESS_BAR_PADDING = 4;
    int DEFAULT_PROGRESS_BAR_HEIGHT = 13;
    int EXTRA_ITEMS_TEXT_COLOR = 0xFFFFFF;
    int PROGRESS_BAR_TEXT_COLOR = 0xFFFFFF;
    int DESCRIPTION_TEXT_COLOR = 0xAAAAAA;

    /**
     * @return  width that the tooltip takes up
     */
    default int getTooltipWidth() {
        return getRowWidth() * getSlotLength();
    }

    /**
     * @return  max rows that can be displayed at a time
     */
    default int getMaxRows() {
        return DEFAULT_MAX_ROWS;
    }

    /**
     * @return  max items that are displayed in a row
     */
    default int getRowWidth() {
        return DEFAULT_ROW_WIDTH;
    }

    /**
     * @return  length in pixels that an item slot takes up
     */
    default int getSlotLength() {
        return DEFAULT_SLOT_LENGTH;
    }

    /**
     * @return  height of the progress bar in pixels
     */
    default int getProgressBarHeight() {
        return DEFAULT_PROGRESS_BAR_HEIGHT;
    }

    /**
     * @return  get padding in pixels surrounding the progress bar
     */
    default int getProgressBarPadding() {
        return DEFAULT_PROGRESS_BAR_PADDING;
    }

    /**
     * Get list of stacks to display
     *
     * @return  list of stacks to display
     */
    List<ItemStack> getStacks();

    /**
     * Gets the index of the selected stack
     *
     * @return  selected index; or -1 if there is none
     */
    int getSelectedIndex();

    /**
     * Gets the fullness fraction for how full the container is
     *
     * @return  fraction depicting how full the progress bar should be
     */
    Fraction getFillFraction();

    /**
     * Gets the progress bar texture.
     *
     * @return  texture identifier for the progress bar
     */
    ResourceLocation getProgressBarFillTexture();

    /**
     * Gets the progress bar border texture.
     *
     * @return  texture identifier for the progress bar border
     */
    ResourceLocation getProgressBarBorderTexture();

    /**
     * Gets the slot background texture.
     *
     * @return  texture identifier for the slot background texture
     */
    ResourceLocation getSlotBackgroundTexture();

    /**
     * Gets the slot highlight back texture.
     *
     * @return  texture identifier for highlighted back texture
     */
    ResourceLocation getSlotHighlightBackTexture();

    /**
     * Gets the slot highlight front texture.
     *
     * @return  texture identifier for highlighted front texture
     */
    ResourceLocation getSlotHighlightFrontTexture();

    /**
     * Generates text to display over the progress bar.
     *
     * @return  text to overlay on the progress bar; can be null
     */
    @Nullable
    Component getProgressBarLabel();

    /**
     * Render contents of the container. Display the row with the selected item
     * and the surrounding rows. If there are items past the rows that are displayed,
     * render the amount of items at the start or end of the bottom/top row.
     *
     * @param textRenderer  text renderer
     * @param x             x position to draw contents at
     * @param y             y position to draw contents at
     * @param width         width of the entire tooltip; can be more than the default width
     * @param top           y position of the top of the tooltip
     * @param drawContext   draw context
     */
    default void drawContents(Font textRenderer, int x, int y, int width, int top, GuiGraphics drawContext) {
        // Calculate row values
        int numRows = getNumRows();
        int maxRows = Math.max(getMaxRows(), MIN_MAX_ROWS);
        int prevVisibleRows = maxRows / 2;
        int nextVisibleRows = (maxRows - 1) / 2;

        // Get selected stack values
        int selectedIndex = getSelectedIndex();
        ItemStack selectedStack;
        if (selectedIndex != -1) {
            selectedStack = getStacks().get(selectedIndex);
        }
        else {
            selectedStack = null;
            selectedIndex = 0;
        }
        int selectedRow = getRowForIndex(selectedIndex);

        // Clamp selected row to display the most rows possible when at the beginning or end of contents
        if (selectedRow < prevVisibleRows) {
            selectedRow = prevVisibleRows;
        }
        else if ((numRows - selectedRow - 1) < nextVisibleRows) {
            selectedRow = numRows - nextVisibleRows - 1;
        }

        // Create list to stores stacks that should be displayed
        List<ItemStack> displayedStacks = new ArrayList<>(maxRows * getRowWidth());
        int seedStart = 0;  // Count how many items come before displayedStacks to calculate seed
        int countPrev = 0;
        int countNext = 0;
        // Run through all stacks to either add their count or add them to be displayed
        for (int i = 0; i < getStacks().size(); i++) {
            int row = getRowForIndex(i);
            ItemStack stack = getStacks().get(i);
            if (row < selectedRow - prevVisibleRows) {
                seedStart++;
                countPrev += stack.getCount();
            }
            else if (row > selectedRow + nextVisibleRows) {
                countNext += stack.getCount();
            }
            else {
                displayedStacks.add(stack);
            }
        }

        // Get ends of display area
        int endX = x + getXMargin(width) + getTooltipWidth();
        int endY = y + Math.min(numRows, maxRows) * getSlotLength();

        // Calculate padding surrounding items
        int itemPadding = (getSlotLength() - 16) / 2;

        for (int i = 0; i < displayedStacks.size(); i++) {
            // iterate through list backwards to account for empty space at the beginning
            ItemStack stack = displayedStacks.get(displayedStacks.size() - 1 - i);
            int slotX = endX - (1 + i % getRowWidth()) * getSlotLength();
            int slotY = endY - (1 + i / getRowWidth()) * getSlotLength();
            if (i == displayedStacks.size() - 1 && countPrev > 0) {
                // If there are previous items, display the count instead
                countPrev += stack.getCount();
                drawContext.drawCenteredString(
                        textRenderer, "+" + countPrev,
                        slotX + getSlotLength() / 2, slotY + itemPadding + 6, EXTRA_ITEMS_TEXT_COLOR
                );
            }
            else if (i == 0 && countNext > 0) {
                // If there are next items, display the count instead
                countNext += stack.getCount();
                drawContext.drawCenteredString(
                        textRenderer, "+" + countNext,
                        slotX + getSlotLength() / 2, slotY + itemPadding + 6, EXTRA_ITEMS_TEXT_COLOR
                );
            }
            else {
                // Render background. If stack is selected, render a different background
                if (stack == selectedStack) {
                    blit(
                            drawContext, getSlotHighlightBackTexture(), slotX, slotY,
                            getSlotLength(), getSlotLength(), getSlotLength(), getSlotLength()
                    );
                }
                else {
                    blit(
                            drawContext, getSlotBackgroundTexture(), slotX, slotY,
                            getSlotLength(), getSlotLength(), getSlotLength(), getSlotLength()
                    );
                }
                drawContext.renderItem(
                        stack, slotX + itemPadding, slotY + itemPadding,
                        seedStart + displayedStacks.size() - 1 - i
                );
                drawContext.renderItemDecorations(
                        textRenderer, stack, slotX + itemPadding, slotY + itemPadding
                );
                if (stack == selectedStack) {
                    blit(
                            drawContext, getSlotHighlightFrontTexture(), slotX, slotY,
                            getSlotLength(), getSlotLength(), getSlotLength(), getSlotLength()
                    );
                }
            }
        }

        // Add selected tooltip at the top if there is a selected stack
        if (selectedStack != null) {
            Component name = selectedStack.getHoverName();
            int nameX = x + (width - textRenderer.width(name.getVisualOrderText())) / 2 - 12;
            int nameY = top - 15;
            drawContext.renderTooltip(
                    textRenderer, name, nameX, nameY
            );
        }
    }

    /**
     * Gets the row index for a given index of an item in the contents.
     * Does account for the initial empty spaces at the start of the first row.
     *
     * @param index     index of the item in the contents
     * @return          row index of the item
     */
    default int getRowForIndex(int index) {
        int emptySpaces = getRowWidth() - (1 + (getStacks().size() - 1) % getRowWidth());
        return (index + emptySpaces) / getRowWidth();
    }

    /**
     * Gets the number of rows that the total contents use.
     *
     * @return  total number of rows in the contents
     */
    default int getNumRows() {
        return 1 + (getStacks().size() - 1) / getRowWidth();
    }

    /**
     * Calculates the length in pixels that the progress bar should take up.
     *
     * @return  length in pixels of the progress bar
     */
    default int getProgressBarFill() {
        int progressBarWidth = getTooltipWidth() - 2;
        Fraction fillFraction = getFillFraction();
        return 1 + Mth.clamp(fillFraction.getNumerator() * progressBarWidth / fillFraction.getDenominator(), 0, progressBarWidth);
    }

    /**
     * Draws the fullness progress bar. Does not account for the padding surrounding the progress bar.
     *
     * @param textRenderer  text renderer
     * @param x             x position to draw progress bar at
     * @param y             y position to draw progress bar at
     * @param width         width of the entire tooltip; can be more than the default width
     * @param drawContext   draw context
     */
    default void drawProgressBar(
            Font textRenderer,
            int x,
            int y,
            int width,
            GuiGraphics drawContext) {
        x += getXMargin(width);
        // Draw bar and border
        blit(
                drawContext, getProgressBarFillTexture(), x, y,
                getProgressBarFill(), getProgressBarHeight(), width, getProgressBarHeight()
        );
        blit(
                drawContext, getProgressBarBorderTexture(), x, y,
                getTooltipWidth(), getProgressBarHeight(), width, getProgressBarHeight()
        );
        // If there is a label, draw it centered on the progress bar
        Component progressBarLabel = getProgressBarLabel();
        if (progressBarLabel != null) {
            drawContext.drawCenteredString(
                    textRenderer, progressBarLabel.getString(), x + getTooltipWidth() / 2,
                    y + (getProgressBarHeight() - (textRenderer.lineHeight - 2)) / 2, PROGRESS_BAR_TEXT_COLOR
            );
        }
    }

    /**
     * Calculates the height of the contents.
     *
     * @return  height in pixels that the contents take up
     */
    default int getContentsHeight() {
        return Math.min(getNumRows(), Math.max(getMaxRows(), MIN_MAX_ROWS)) * getSlotLength();
    }

    /**
     * Calculates how much x margin is needed to center the tooltip, given that the width
     * of the entire tooltip is greater than the width of the container tooltip.
     *
     * @param width     width of the entire tooltip; can be more than the default width
     * @return          horizontal margin needed to center the tooltip
     */
    default int getXMargin(int width) {
        return (width - getTooltipWidth()) / 2;
    }
    /**
     * Calculates the height that the given text takes up.
     *
     * @param text          text to get the height for
     * @param textRenderer  text renderer
     * @return              height in pixels that the given text takes up
     */
    default int getHeight(@Nullable Component text, Font textRenderer) {
        if (text == null) {
            return 0;
        }
        return textRenderer.split(text, getTooltipWidth()).size() * textRenderer.lineHeight;
    }

    default void blit(GuiGraphics guiGraphics, ResourceLocation texture, int x, int y, int width, int height, int maxWidth, int maxHeight) {
        guiGraphics.blit(texture, x, y, 0, 0, 0, width, height, maxWidth, maxHeight);
    }
}

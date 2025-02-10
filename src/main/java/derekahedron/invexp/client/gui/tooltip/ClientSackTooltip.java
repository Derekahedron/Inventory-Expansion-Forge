package derekahedron.invexp.client.gui.tooltip;

import derekahedron.invexp.sack.SackContents;
import derekahedron.invexp.sack.SackDataManager;
import derekahedron.invexp.sack.SacksHelper;
import derekahedron.invexp.util.InvExpUtil;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.apache.commons.lang3.math.Fraction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ClientSackTooltip implements ClientTooltipComponent, ContainerItemTooltipComponent {
    public static final ResourceLocation SACK_PROGRESS_BAR_BORDER_TEXTURE = InvExpUtil.location("textures/gui/container/sack/sack_progressbar_border.png");
    public static final ResourceLocation SACK_PROGRESS_BAR_FILL_TEXTURE = InvExpUtil.location("textures/gui/container/sack/sack_progressbar_fill.png");
    public static final ResourceLocation SACK_PROGRESS_BAR_FULL_TEXTURE = InvExpUtil.location("textures/gui/container/sack/sack_progressbar_full.png");
    public static final ResourceLocation SACK_SLOT_HIGHLIGHT_BACK_TEXTURE = InvExpUtil.location("textures/gui/container/sack/slot_highlight_back.png");
    public static final ResourceLocation SACK_SLOT_HIGHLIGHT_FRONT_TEXTURE = InvExpUtil.location("textures/gui/container/sack/slot_highlight_front.png");
    public static final ResourceLocation SACK_SLOT_BACKGROUND_TEXTURE = InvExpUtil.location("textures/gui/container/sack/slot_background.png");
    public static final Component SACK_FULL = Component.translatable("item.invexp.sack.full");
    public static final Component SACK_EMPTY = Component.translatable("item.invexp.sack.empty");
    public static final Component SACK_TOO_MANY_STACKS = Component.translatable("item.invexp.sack.too_many_stacks");
    public static final String SACK_PARTIAL = "item.invexp.sack.partial";
    public static final String SACK_EMPTY_DESCRIPTION = "item.invexp.sack.description.empty";
    public static final String SACK_TYPE_DESCRIPTION = "item.invexp.sack.description.type";
    public static final String SACK_TYPE_DESCRIPTION_CONJUNCTION = "item.invexp.sack.description.type.conjunction";
    public static final String SACK_TYPE_DESCRIPTION_MANY_CONJUNCTION = "item.invexp.sack.description.type.many.conjunction";
    public static final String SACK_TYPE_DESCRIPTION_MANY_LAST_CONJUNCTION = "item.invexp.sack.description.type.many.last_conjunction";

    public final SackContents contents;
    private Font lastFont;

    public ClientSackTooltip(SackContents contents) {
        this.contents = contents;
    }

    /**
     * Draws the sack tooltip by first drawing a description, then the contents if there are any,
     * then finally drawing a progress bar.
     *
     * @param textRenderer  text renderer
     * @param x             x position to draw tooltip at
     * @param y             y position to draw tooltip at
     * @param drawContext   draw context
     */
    @Override
    public void renderImage(@NotNull Font textRenderer, int x, int y, @NotNull GuiGraphics drawContext) {
        int width = getTooltipWidth();
        int top = y;  // Store top
        if (contents.isEmpty()) {
            if (lastFont != null) {
                // Draw empty description
                Component sackEmptyDescription = getSackEmptyDescription();
                drawContext.drawWordWrap(
                        textRenderer, sackEmptyDescription, x + getXMargin(width), y,
                        getTooltipWidth(), DESCRIPTION_TEXT_COLOR
                );
                y += getHeight(sackEmptyDescription, textRenderer);
            }
        } else {
            // Draw sack type description and contents
            if (lastFont != null) {
                Component sackTypeDescription = getSackTypeDescription();
                if (sackTypeDescription != null) {
                    drawContext.drawWordWrap(
                            textRenderer, sackTypeDescription, x + getXMargin(width), y,
                            getTooltipWidth(), DESCRIPTION_TEXT_COLOR
                    );
                    y += getHeight(sackTypeDescription, textRenderer);
                }
            }
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
        if (lastFont != null) {
            if (contents.isEmpty()) {
                height += getHeight(getSackEmptyDescription(), lastFont);
            } else {
                height += getHeight(getSackTypeDescription(), lastFont);
                height += getContentsHeight();
            }
        }
        height += getProgressBarPadding();
        height += getProgressBarHeight();
        height += getProgressBarPadding();
        return height;
    }

    /**
     * Generates description for an empty sack.
     *
     * @return  empty sack description text
     */
    public @NotNull Component getSackEmptyDescription() {
        return Component.translatable(SACK_EMPTY_DESCRIPTION, formatWeight(contents.getMaxSackWeight()));
    }

    /**
     * Generates description to display all the sack types that are used.
     *
     * @return  sack type description text
     */
    public @Nullable Component getSackTypeDescription() {
        if (SackDataManager.getInstance() == null) {
            return null;
        }
        Component description = null;
        // Get list of all names
        List<Component> names = new ArrayList<>(contents.getSackTypes().size());
        for (String sackType : contents.getSackTypes()) {
            Component name = SackDataManager.getInstance().getSackName(sackType);
            if (name != null) {
                names.add(name);
            }
        }

        for (int i = 0; i < names.size(); i++) {
            Component name = names.get(i);
            // If first entry, set description to name
            if (description == null) {
                description = name;
            }
            // If not last entry, add conjunction "original_list, new_entry"
            else if (i != names.size() - 1) {
                description = Component.translatable(SACK_TYPE_DESCRIPTION_MANY_CONJUNCTION, description, name);
            }
            // If last entry for list of size 2, add conjunction "original_list and new_entry"
            else if (names.size() == 2) {
                description = Component.translatable(SACK_TYPE_DESCRIPTION_CONJUNCTION, description, name);
            }
            // If last entry for list of size > 2, add conjunction "original_list, and new_entry"
            else {
                description = Component.translatable(SACK_TYPE_DESCRIPTION_MANY_LAST_CONJUNCTION, description, name);
            }
        }
        // If a description was made, prepend "Sack of " to names
        if (description != null) {
            description = Component.translatable(SACK_TYPE_DESCRIPTION, description);
        }
        return description;
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
            return SACK_PROGRESS_BAR_FULL_TEXTURE;
        } else {
            return SACK_PROGRESS_BAR_FILL_TEXTURE;
        }
    }

    /**
     * @return  texture identifier for the progress bar border
     */
    @Override
    public @NotNull ResourceLocation getProgressBarBorderTexture() {
        return SACK_PROGRESS_BAR_BORDER_TEXTURE;
    }

    /**
     * @return  texture identifier for the slot background texture
     */
    @Override
    public @NotNull ResourceLocation getSlotBackgroundTexture() {
        return SACK_SLOT_BACKGROUND_TEXTURE;
    }

    /**
     * @return  texture identifier for highlighted back texture
     */
    @Override
    public @NotNull ResourceLocation getSlotHighlightBackTexture() {
        return SACK_SLOT_HIGHLIGHT_BACK_TEXTURE;
    }

    /**
     * @return  texture identifier for highlighted front texture
     */
    @Override
    public @NotNull ResourceLocation getSlotHighlightFrontTexture() {
        return SACK_SLOT_HIGHLIGHT_FRONT_TEXTURE;
    }

    /**
     * Generate text to display on the progress bar. Either FULL, EMPTY,
     * TOO MANY STACKS, or a fraction of the total weight out of max weight.
     *
     * @return  text to overlay on the progress bar
     */
    @Override
    public @NotNull Component getProgressBarLabel() {
        if (contents.getTotalWeight() >= contents.getMaxSackWeight()) {
            return SACK_FULL;
        }
        else if (getStacks().size() >= contents.getMaxSackStacks()) {
            return SACK_TOO_MANY_STACKS;
        }
        else if (contents.isEmpty()) {
            return SACK_EMPTY;
        }
        else {
            return Component.translatable(SACK_PARTIAL, formatWeight(contents.getTotalWeight()), formatWeight(contents.getMaxSackWeight()));
        }
    }

    /**
     * Formats sack weight for display. The weight is divided by the default sack weight.
     * If the resulting value is a fraction, format to two decimal places.
     *
     * @param weight    sack weight to format
     * @return          formatted text representing the sack weight
     */
    public static @NotNull MutableComponent formatWeight(int weight) {
        if (weight % SacksHelper.DEFAULT_SACK_WEIGHT == 0) {
            return Component.literal(String.valueOf(weight / SacksHelper.DEFAULT_SACK_WEIGHT));
        }
        else {
            return Component.literal(String.format("%.2f", weight / (double) SacksHelper.DEFAULT_SACK_WEIGHT));
        }
    }
}

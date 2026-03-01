package derekahedron.invexp.item.tooltip;

import derekahedron.invexp.quiver.QuiverContents;
import derekahedron.invexp.sack.SackContentsReader;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.tooltip.TooltipComponent;

import javax.annotation.Nullable;

/**
 * Holds quiver tooltip data
 *
 * @param contents  Contents of the quiver
 */
public record QuiverTooltip(QuiverContents contents, @Nullable Component description) implements TooltipComponent {

    public QuiverTooltip(QuiverContents contents) {
        this(contents, null);
    }
}

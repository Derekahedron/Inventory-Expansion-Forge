package derekahedron.invexp.item.tooltip;

import derekahedron.invexp.sack.SackContentsReader;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.tooltip.TooltipComponent;

import javax.annotation.Nullable;

/**
 * Holds sack tooltip data
 *
 * @param contents  Contents of the sack
 */
public record SackTooltip(SackContentsReader contents, @Nullable Component description) implements TooltipComponent {

    public SackTooltip(SackContentsReader contents) {
        this(contents, null);
    }
}

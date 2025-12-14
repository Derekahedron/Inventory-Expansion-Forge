package derekahedron.invexp.item.tooltip;

import derekahedron.invexp.sack.SackContentsReader;
import net.minecraft.world.inventory.tooltip.TooltipComponent;

/**
 * Holds sack tooltip data
 *
 * @param contents  Contents of the sack
 */
public record SackTooltip(SackContentsReader contents) implements TooltipComponent {
}

package derekahedron.invexp.item.tooltip;

import derekahedron.invexp.sack.SackContents;
import net.minecraft.world.inventory.tooltip.TooltipComponent;

/**
 * Holds sack tooltip data
 *
 * @param contents  Contents of the sack
 */
public record SackTooltip(SackContents contents) implements TooltipComponent {
}

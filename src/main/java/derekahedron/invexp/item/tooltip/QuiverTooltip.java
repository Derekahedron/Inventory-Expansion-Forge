package derekahedron.invexp.item.tooltip;

import derekahedron.invexp.quiver.QuiverContents;
import net.minecraft.world.inventory.tooltip.TooltipComponent;

/**
 * Holds quiver tooltip data
 *
 * @param contents  Contents of the quiver
 */
public record QuiverTooltip(QuiverContents contents) implements TooltipComponent {
}

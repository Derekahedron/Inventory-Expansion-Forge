package derekahedron.invexp.item.tooltip;

import derekahedron.invexp.bundle.BundleContents;
import net.minecraft.world.inventory.tooltip.TooltipComponent;

public record BetterBundleTooltip(BundleContents contents) implements TooltipComponent {
}

package derekahedron.invexp.item.tooltip;

import derekahedron.invexp.bundle.BundleContents;
import derekahedron.invexp.quiver.QuiverContents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.tooltip.TooltipComponent;

import javax.annotation.Nullable;

public record BetterBundleTooltip(BundleContents contents, @Nullable Component description) implements TooltipComponent {

    public BetterBundleTooltip(BundleContents contents) {
        this(contents, null);
    }
}

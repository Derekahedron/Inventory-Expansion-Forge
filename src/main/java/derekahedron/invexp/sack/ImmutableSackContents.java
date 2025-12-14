package derekahedron.invexp.sack;

import net.minecraft.world.item.ItemStack;
import org.apache.commons.lang3.math.Fraction;

import javax.annotation.Nullable;
import java.util.List;

public class ImmutableSackContents implements SackContentsReader {
    public final ItemStack sackStack;
    public SackContentsComponent component;

    private ImmutableSackContents(ItemStack sackStack, SackContentsComponent component) {
        this.sackStack = sackStack;
        this.component = component;
    }

    @Nullable
    public static ImmutableSackContents of(@Nullable ItemStack sackStack) {
        SackContentsComponent component = SackContentsComponent.getComponent(sackStack);
        if (component == null) {
            return null;
        }
        return new ImmutableSackContents(sackStack, component);
    }

    @Override
    public ItemStack getSackStack() {
        return sackStack;
    }

    @Override
    public List<String> getSackTypes() {
        return component.sackTypes;
    }

    @Override
    public Fraction getTotalWeight() {
        return component.getTotalWeight();
    }

    @Override
    public List<ItemStack> getStacks() {
        return component.getStacks();
    }

    @Override
    public int getSelectedIndex() {
        return component.getSelectedIndex();
    }
}

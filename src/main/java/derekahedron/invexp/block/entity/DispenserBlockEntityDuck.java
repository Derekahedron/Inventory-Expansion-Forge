package derekahedron.invexp.block.entity;

import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Adds getter and setter methods for the usage buffer on dispensers.
 */
public interface DispenserBlockEntityDuck {
    void invexp$setUsageBuffer(@Nullable List<ItemStack> usageBuffer);
}

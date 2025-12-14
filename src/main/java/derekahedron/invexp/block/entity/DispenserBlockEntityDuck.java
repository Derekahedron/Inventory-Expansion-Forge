package derekahedron.invexp.block.entity;

import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Adds getter and setter methods for the usage buffer on dispensers.
 */
public interface DispenserBlockEntityDuck {
    void invexp_$setUsageBuffer(@Nullable List<ItemStack> usageBuffer);
}

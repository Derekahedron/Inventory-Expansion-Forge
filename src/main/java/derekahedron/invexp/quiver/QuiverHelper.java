package derekahedron.invexp.quiver;

import derekahedron.invexp.item.QuiverItem;
import net.minecraft.world.item.ItemStack;
import org.apache.commons.lang3.math.Fraction;
import org.jetbrains.annotations.NotNull;

/**
 * Contains helper functions and default values for data components related to quivers.
 */
public class QuiverHelper {

    /**
     * Return the maximum number of total stacks that can occupy this quiver.
     * The occupancy fraction is based off of this value, so a value of 8 would mean
     * the quiver can hold 8 assorted stacks of arrows.
     *
     * @param stack     stack to test
     * @return          maximum quiver occupancy stacks this item can hold as a quiver
     */
    public static int getMaxQuiverOccupancyStacks(@NotNull ItemStack stack) {
        if (stack.getItem() instanceof QuiverItem quiverItem) {
            return quiverItem.getMaxQuiverOccupancyStacks();
        }
        return 0;
    }

    /**
     * Return the maximum occupancy fraction this item can hold as a quiver.
     *
     * @param stack     stack to test
     * @return          maximum quiver occupancy this item can hold as a quiver
     */
    public static @NotNull Fraction getMaxQuiverOccupancy(@NotNull ItemStack stack) {
        return Fraction.getFraction(getMaxQuiverOccupancyStacks(stack));
    }

    /**
     * Return the maximum number of stacks this item can hold as a quiver.
     *
     * @param stack     stack to test
     * @return          maximum stacks this item can hold as a quiver
     */
    public static int getMaxQuiverStacks(@NotNull ItemStack stack) {
        if (stack.getItem() instanceof QuiverItem quiverItem) {
            return quiverItem.getMaxQuiverStacks();
        }
        return 0;
    }

    /**
     * Returns an occupancy fraction one of these items takes up.
     *
     * @param stack     stack to test
     * @return          how much of a stack one of these items takes up
     */
    public static @NotNull Fraction getOccupancy(@NotNull ItemStack stack) {
        return Fraction.getFraction(1, stack.getMaxStackSize());
    }


    /**
     * Returns an occupancy fraction this stack takes up.
     *
     * @param stack     stack to test
     * @return          how much of a stack the given stack takes up
     */
    public static @NotNull Fraction getOccupancyOfStack(@NotNull ItemStack stack) {
        return Fraction.getFraction(stack.getCount(), stack.getMaxStackSize());
    }
}

package derekahedron.invexp.quiver;

import derekahedron.invexp.item.QuiverItem;
import derekahedron.invexp.util.ContainerItemContentsReader;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import org.apache.commons.lang3.math.Fraction;

/**
 * Checker for contents of a quiver.
 */
public interface QuiverContentsReader extends ContainerItemContentsReader {

    /**
     * Gets the quiver stack holding the contents.
     *
     * @return  quiver stack that holds the contents
     */
     ItemStack getQuiverStack();

    /**
     * Gets the total occupancy of all items in the quiver.
     *
     * @return  total quiver occupancy that the contents hold
     */
    Fraction getTotalOccupancy();

    @Override
    default boolean canTryInsert(ItemStack stack) {
        return stack.is(ItemTags.ARROWS) && stack.getItem().canFitInsideContainerItems();
    }

    @Override
    default int getMaxAllowed(ItemStack stack) {
        if (!canTryInsert(stack)) {
            return 0;
        }
        int maxAllowedByOccupancy = getMaxAllowedByOccupancy(stack);
        if (maxAllowedByOccupancy == 0) {
            return 0;
        }
        else if (canAddStack()) {
            return maxAllowedByOccupancy;
        }
        int maxAllowed = 0;
        for (ItemStack nestedStack : getStacks()) {
            if (ItemStack.isSameItemSameTags(stack, nestedStack)) {
                maxAllowed += nestedStack.getMaxStackSize() - nestedStack.getCount();
                if (maxAllowed >= maxAllowedByOccupancy) {
                    return maxAllowedByOccupancy;
                }
            }
        }
        return maxAllowed;
    }

    /**
     * Check how many items of the given stack can be added, only considering available
     * occupancy.
     *
     * @param stack     stack to test
     * @return          how many items can be added
     */
    default int getMaxAllowedByOccupancy(ItemStack stack) {
        Fraction openOccupancy = getMaxQuiverOccupancy().subtract(getTotalOccupancy());
        return Math.max(openOccupancy.divideBy(QuiverHelper.getOccupancy(stack)).intValue(), 0);
    }

    @Override
    default boolean isFull() {
        return getTotalOccupancy().compareTo(getMaxQuiverOccupancy()) >= 0 || getStacks().size() >= getMaxQuiverStacks();
    }

    @Override
    default Fraction getFillFraction() {
        if (isFull()) {
            return Fraction.ONE;
        }
        else {
            return getTotalOccupancy().divideBy(getMaxQuiverOccupancy());
        }
    }

    @Override
    default boolean canAddStack() {
        return getStacks().size() < getMaxQuiverStacks();
    }

    /**
     * Gets max quiver stacks of the holding quiver stack.
     *
     * @return  max stacks the quiver can hold
     */
    default int getMaxQuiverStacks() {
        if (getQuiverStack().getItem() instanceof QuiverItem quiverItem) {
            return quiverItem.getMaxQuiverStacks();
        }
        return 0;
    }

    /**
     * Gets max occupancy of the holding quiver stack.
     *
     * @return  max occupancy the quiver can hold
     */
    default Fraction getMaxQuiverOccupancy() {
        return QuiverHelper.getMaxQuiverOccupancy(getQuiverStack());
    }
}

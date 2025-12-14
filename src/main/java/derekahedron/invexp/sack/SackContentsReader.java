package derekahedron.invexp.sack;

import derekahedron.invexp.item.SackItem;
import derekahedron.invexp.util.ContainerItemContentsReader;
import net.minecraft.world.item.ItemStack;
import org.apache.commons.lang3.math.Fraction;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Checker for contents of a sack.
 */
public interface SackContentsReader extends ContainerItemContentsReader {

    /**
     * Gets the sack <code>ItemStack</code> holding this contents.
     *
     * @return The <code>ItemStack</code> that holds this contents.
     *         This is intended to be a sack.
     */
    ItemStack getSackStack();

    /**
     * Gets all sack types that this sack can hold.
     *
     * @return  List of all sack types in the contents
     */
    List<String> getSackTypes();

    /**
     * Gets the total sack weight of all items in the sack.
     *
     * @return the total sack weight that this sack holds
     */
    Fraction getTotalWeight();

    /**
     * Check if the contents have reached max stacks or the total weight has reached max weight.
     *
     * @return  true if the contents should display as full
     */
    @Override
    default boolean isFull() {
        return (getTotalWeight().compareTo(getMaxSackWeight()) >= 0) || (getStacks().size() >= getMaxSackStacks());
    }

    /**
     * Gets a fraction for displaying fullness of contents.
     *
     * @return  fraction representing fullness
     */
    @Override
    default Fraction getFillFraction() {
        if (isFull()) {
            return Fraction.ONE;
        } else {
            return getTotalWeight().divideBy(getMaxSackWeight());
        }
    }

    @Override
    default boolean canTryInsert(ItemStack stack) {
        if (!stack.getItem().canFitInsideContainerItems()) {
            return false;
        }
        String sackType = SacksHelper.getSackType(stack);
        if (sackType == null) {
            return false;
        }
        return canAddType() || isInTypes(sackType);
    }

    @Override
    default int getMaxAllowed(ItemStack stack) {
        if (!canTryInsert(stack)) {
            return 0;
        }

        int maxAllowedByWeight = getMaxAllowedByWeight(stack);
        if (maxAllowedByWeight == 0) {
            return 0;
        } else if (canAddStack()) {
            // If we can add a stack, we can always add as much as is allowed by weight
            return maxAllowedByWeight;
        }

        // Iterate through stacks and see how many items can be added by merging
        int maxAllowed = 0;
        for (ItemStack nestedStack : getStacks()) {
            if (ItemStack.isSameItemSameTags(stack, nestedStack)) {
                maxAllowed += nestedStack.getMaxStackSize() - nestedStack.getCount();
                if (maxAllowed >= maxAllowedByWeight) {
                    return maxAllowedByWeight;
                }
            }
        }
        return maxAllowed;
    }

    /**
     * Check how many items of the given stack can be added, only considering available
     * weight.
     *
     * @param stack     stack to test
     * @return          how many items can be added
     */
    default int getMaxAllowedByWeight(ItemStack stack) {
        if (stack.isEmpty()) return 0;

        Fraction weight = SacksHelper.getSackWeight(stack);
        if (weight.compareTo(Fraction.ZERO) > 0) {
            Fraction openWeight = getMaxSackWeight().subtract(getTotalWeight());
            return Math.max(openWeight.divideBy(weight).intValue(), 0);
        } else {
            return stack.getCount();
        }
    }

    /**
     * Checks if the given sack type exists in the current types of the sack.
     *
     * @param sackType  sack type to test for
     * @return          true if the given type exists in the types; false otherwise
     */
    default boolean isInTypes(@Nullable String sackType) {
        if (sackType == null) return false;

        for (String nestedType : getSackTypes()) {
            if (sackType.equals(nestedType)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if there are fewer types than max types.
     *
     * @return  true if there is space for a new sack type; false otherwise
     */
    default boolean canAddType() {
        return getSackTypes().size() < getMaxSackTypes();
    }

    @Override
    default boolean canAddStack() {
        return getStacks().size() < getMaxSackStacks();
    }

    /**
     * Gets max sack stacks of the holding sack stack.
     *
     * @return  max stacks the sack can hold
     */
    default int getMaxSackStacks() {
        if (getSackStack().getItem() instanceof SackItem sackItem) {
            return sackItem.getMaxSackStacks();
        }
        return 0;
    }

    /**
     * Gets max sack types of the holding sack stack.
     *
     * @return  max sack types the sack can hold
     */
    default int getMaxSackTypes() {
        if (getSackStack().getItem() instanceof SackItem sackItem) {
            return sackItem.getMaxSackTypes();
        }
        return 0;
    }

    /**
     * Gets max sack weight of the holding sack stack.
     *
     * @return  max sack weight the sack can hold
     */
    default Fraction getMaxSackWeight() {
        if (getSackStack().getItem() instanceof SackItem sackItem) {
            return sackItem.getMaxSackWeight();
        }
        return Fraction.ZERO;
    }
}

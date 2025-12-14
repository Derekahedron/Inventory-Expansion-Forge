package derekahedron.invexp.bundle;

import derekahedron.invexp.item.BetterBundleItem;
import derekahedron.invexp.util.ContainerItemContentsReader;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.apache.commons.lang3.math.Fraction;

public interface BundleContentsReader extends ContainerItemContentsReader {

    /**
     * Gets the bundle stack holding the contents.
     *
     * @return bundle stack that holds the contents
     */
    ItemStack getBundleStack();

    /**
     * Gets the total weight of all items in the bundle.
     *
     * @return total bundle weight that the contents hold
     */
    int getTotalWeight();

    @Override
    default boolean isFull() {
        return getTotalWeight() >= getMaxBundleWeight() || getStacks().size() >= getMaxBundleStacks();
    }

    @Override
    default Fraction getFillFraction() {
        if (isFull()) {
            return Fraction.ONE;
        } else {
            return Fraction.getFraction(getTotalWeight(), getMaxBundleWeight());
        }
    }

    @Override
    default boolean canTryInsert(ItemStack stack) {
        return stack.getItem().canFitInsideContainerItems();
    }

    @Override
    default int getMaxAllowed(ItemStack stack) {
        if (!canTryInsert(stack)) {
            return 0;
        }

        int maxAllowedByWeight = getMaxAllowedByWeight(stack);
        if (maxAllowedByWeight == 0) {
            return 0;
        }
        else if (canAddStack()) {
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
        if (stack.isEmpty()) {
            return 0;
        }
        int itemWeight = BundleHelper.getBundleWeight(stack);
        if (itemWeight > 0) {
            return Math.min(stack.getCount(), Math.max(getMaxBundleWeight() - getTotalWeight(), 0) / itemWeight);
        }
        else {
            return stack.getCount();
        }
    }

    @Override
    default boolean canAddStack() {
        return getStacks().size() < getMaxBundleStacks();
    }

    /**
     * Gets max bundle stacks of the holding bundle stack.
     *
     * @return  max stacks the bundle can hold
     */
    default int getMaxBundleStacks() {
        ItemStack stack = getBundleStack();
        if (stack.is(Items.BUNDLE)) {
            return 64;
        } else if (stack.getItem() instanceof BetterBundleItem betterBundleItem) {
            return betterBundleItem.getMaxBundleStacks();
        }
        return 0;
    }

    /**
     * Gets max bundle weight of the holding bundle stack.
     *
     * @return  max bundle weight the bundle can hold
     */
    default int getMaxBundleWeight() {
        return BundleHelper.getMaxBundleWeightStacks(getBundleStack()) * 64;
    }
}

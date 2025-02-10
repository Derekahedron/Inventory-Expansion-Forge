package derekahedron.invexp.bundle;

import derekahedron.invexp.util.ContainerItemContentsChecker;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public interface BundleContentsChecker extends ContainerItemContentsChecker {

    /**
     * Gets the bundle stack holding the contents.
     *
     * @return bundle stack that holds the contents
     */
    @NotNull
    ItemStack getBundleStack();

    /**
     * Gets the total weight of all items in the bundle.
     *
     * @return total bundle weight that the contents hold
     */
    int getTotalWeight();

    /**
     * Checks that the item can be added by type as well as if it can be nested.
     *
     * @param stack stack to try to insert
     * @return if the stack can be added based on item
     */
    @Override
    default boolean canTryInsert(@NotNull ItemStack stack) {
        return stack.getItem().canFitInsideContainerItems();
    }

    /**
     * Checks the stacks weight, type, and available stacks to see how much of
     * the given stack can be added.
     *
     * @param stack     stack to test
     * @return          how much of the stack can be added
     */
    @Override
    default int getMaxAllowed(@NotNull ItemStack stack) {
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
    default int getMaxAllowedByWeight(@NotNull ItemStack stack) {
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

    /**
     * Check if there are fewer stacks than max stacks.
     *
     * @return  true if there is space for a new stack; false otherwise
     */
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
        return BundleHelper.getMaxBundleStacks(getBundleStack());
    }

    /**
     * Gets max bundle weight of the holding bundle stack.
     *
     * @return  max bundle weight the bundle can hold
     */
    default int getMaxBundleWeight() {
        return BundleHelper.getMaxBundleWeight(getBundleStack());
    }
}

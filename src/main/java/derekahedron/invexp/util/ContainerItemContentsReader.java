package derekahedron.invexp.util;

import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import org.apache.commons.lang3.math.Fraction;

import java.util.List;

/**
 * Interface that defines functions needed for checking contents and if an item can be
 * inserted into a container item
 */
public interface ContainerItemContentsReader {

    /**
     * Gets the item stack list inside the container item
     *
     * @return List of stack contents
     */
    List<ItemStack> getStacks();

    /**
     * Gets the selected index of the container item
     *
     * @return  selected index; -1 if there is none
     */
    int getSelectedIndex();

    /**
     * Checks if an item can potentially be inserted into the container based on the item itself.
     * General check based on the type of the item and not fullness of the contents.
     *
     * @param stack the <code>ItemStack</code> to test
     * @return <code>true</code> if the <code>ItemStack</code> matches contents criteria;
     *         <code>false</code> otherwise
     */
    boolean canTryInsert(ItemStack stack);

    /**
     * Gets the maximum number of items from the given <code>ItemStack</code> that
     * can be added to these contents.
     *
     * @param stack the <code>ItemStack</code> to test
     * @return the maximum number of items that can be inserted
     */
    int getMaxAllowed(ItemStack stack);

    /**
     * Checks if there is room to add a new stack in the container contents.
     *
     * @return <code>true</code> if there is space for a new stack; <code>false</code> otherwise
     */
    boolean canAddStack();

    /**
     * Test for if the contents should show as full. Either by weight or stacks.
     *
     * @return  true if the contents should render as full
     */
    boolean isFull();

    /**
     * Gets a fraction for displaying fullness of contents.
     *
     * @return  fraction representing fullness
     */
    Fraction getFillFraction();

    /**
     * Check if the container is empty.
     *
     * @return  true if the container is empty; false otherwise
     */
    default boolean isEmpty() {
        return getStacks().isEmpty();
    }

    /**
     * Gets the selected item stack from the contents.
     *
     * @return  selected ItemStack; EMPTY if there is none
     */
    default ItemStack getSelectedStack() {
        if (isEmpty()) {
            return ItemStack.EMPTY;
        }
        return getStacks().get(clampIndex(getSelectedIndex()));
    }

    /**
     * Turns index into a valid index that points to a stack.
     *
     * @param index     index to transform
     * @return          valid index; -1 if empty
     */
    default int clampIndex(int index) {
        if (isEmpty()) {
            return -1;
        }
        else {
            return Mth.clamp(index, 0, getStacks().size() - 1);
        }
    }

    /**
     * Search the contents for a stack that matches the given stack and returns the index.
     *
     * @param stack             stack to search for
     * @param startingIndex     index to start the search from
     * @return                  index of a stack that matches; -1 if there is none
     */
    default int indexOf(ItemStack stack, int startingIndex) {
        if (isEmpty()) {
            return -1;
        }
        startingIndex = clampIndex(startingIndex);
        // First check stacks after starting index
        for (int i = startingIndex; i < getStacks().size(); i++) {
            if (ItemStack.isSameItemSameTags(stack, getStacks().get(i))) {
                return i;
            }
        }
        // Next check stacks before starting index
        for (int i = startingIndex - 1; i >= 0; i--) {
            if (ItemStack.isSameItemSameTags(stack, getStacks().get(i))) {
                return i;
            }
        }
        return -1;
    }
}

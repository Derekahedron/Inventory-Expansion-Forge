package derekahedron.invexp.bundle;

import com.google.common.collect.Lists;
import derekahedron.invexp.util.ContainerItemContents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.apache.commons.lang3.math.Fraction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BundleContents extends ContainerItemContents implements BundleContentsChecker{
    public final ItemStack bundleStack;
    private BundleContentsComponent component;

    /**
     * Creates a new QuiverContents object. Private as of() should be used to create
     * QuiverContents, so we can ensure they are valid.
     *
     * @param stack stack containing the contents
     * @param component contents component
     */
    private BundleContents(@NotNull ItemStack stack, @NotNull BundleContentsComponent component) {
        this.bundleStack = stack;
        this.component = component;
    }

    /**
     * Create a new QuiverContents from the given stack. If the stack cannot have
     * contents, returns null
     *
     * @param stack stack to create contents from
     * @return created QuiverContents; null if not valid
     */
    public static @Nullable BundleContents of(@Nullable ItemStack stack) {
        BundleContentsComponent component = BundleContentsComponent.getComponent(stack);
        if (component == null) {
            return null;
        }
        return new BundleContents(stack, component);
    }

    /**
     * Checks the validity of the bundle contents. First checks the component validity, which
     * is calculated when the component is created.
     * Then checks for weight and max stacks, which are both instant checks.
     *
     * @return if the quiver contents are valid
     */
    public boolean isValid() {
        if (!component.isValid()) {
            return false;
        }
        return getTotalWeight() <= getMaxBundleWeight() && getStacks().size() <= getMaxBundleStacks();
    }

    /**
     * Checks if the contents are valid. If they are not, create a new BundleContents
     * and add each item one by one. Leftover stacks are given to the player after the validation.
     *
     * @param player player holding the quiver
     */
    public void validate(@NotNull Player player) {
        if (isValid()) {
            return;
        }

        ArrayList<ItemStack> removedStacks = new ArrayList<>(getStacks().size());
        BundleContents newContents = new BundleContents(bundleStack, new BundleContentsComponent());
        Builder builder = newContents.getBuilder();
        for (int i = getStacks().size() - 1; i >= 0; i--) {
            ItemStack stack = getStacks().get(i).copy();
            builder.add(stack, 0);
            if (!stack.isEmpty()) {
                removedStacks.add(stack);
            }
        }

        builder.selectedIndex = builder.nextSelectedIndex(getSelectedStack(), getSelectedIndex());
        builder.apply();
        component = newContents.component;
        for (ItemStack stack : removedStacks) {
            if (!player.getInventory().add(stack)) {
                player.drop(stack, false);
            }
        }
    }

    /**
     * Gets the stack from the component.
     *
     * @return List of stack contents
     */
    @Override
    public @NotNull List<ItemStack> getStacks() {
        return component.stacks;
    }

    /**
     * Gets the selected index from the component.
     *
     * @return selected index; -1 if there is none
     */
    @Override
    public int getSelectedIndex() {
        return component.selectedIndex;
    }

    /**
     * Sets selected index of contents
     *
     * @param selectedIndex new selected index
     */
    @Override
    public void setSelectedIndex(int selectedIndex) {
        if (selectedIndex != -1) {
            selectedIndex = clampIndex(selectedIndex);
        }
        if (selectedIndex != getSelectedIndex()) {
            ContainerItemContents.Builder builder = getBuilder();
            builder.setSelectedIndex(selectedIndex);
            builder.apply();
        }
    }

    /**
     * Check if the contents have reached max stacks or the total weight has reached max weight.
     *
     * @return true if the contents should display as full
     */
    @Override
    public boolean isFull() {
        return getTotalWeight() >= getMaxBundleWeight() || getStacks().size() >= getMaxBundleStacks();
    }

    /**
     * Gets a fraction for displaying fullness of contents.
     *
     * @return fraction representing fullness
     */
    @Override
    public @NotNull Fraction getFillFraction() {
        if (isFull()) {
            return Fraction.ONE;
        }
        else {
            return Fraction.getFraction(getTotalWeight(), getMaxBundleWeight());
        }
    }

    /**
     * Gets the stored bundle stack.
     *
     * @return bundle stack that holds the contents
     */
    @Override
    public @NotNull ItemStack getBundleStack() {
        return bundleStack;
    }

    /**
     * Gets the total occupancy from the component.
     *
     * @return total bundle weight that the contents hold
     */
    @Override
    public int getTotalWeight() {
        return component.getTotalWeight();
    }

    /**
     * Create a new builder for modifying bundle contents.
     *
     * @return builder for bundle contents
     */
    @Override
    public @NotNull BundleContents.Builder getBuilder() {
        return new BundleContents.Builder();
    }

    /**
     * Builder for BundleContents. Contains a copy of the bundle contents to be modified.
     */
    public class Builder extends ContainerItemContents.Builder implements BundleContentsChecker {
        public final List<ItemStack> stacks;
        public int selectedIndex;
        public int totalWeight;

        /**
         * Copies component data into modifiable versions.
         */
        public Builder() {
            this.stacks = new ArrayList<>(component.getStacks());
            this.selectedIndex = component.selectedIndex;
            this.totalWeight = component.getTotalWeight();
        }

        /**
         * Applies the copied values to the QuiverContents object this is attached to.
         */
        @Override
        public void apply() {
            component = new BundleContentsComponent(
                    List.copyOf(stacks),
                    Mth.clamp(selectedIndex, -1, stacks.size() - 1)
            );
            component.setComponent(bundleStack);
        }

        /**
         * Tries to add the given stack to the bundle. First tries merging with existing items,
         * then tries inserting at the given index.
         *
         * @param stack stack to add
         * @param insertAt where to insert the new stack
         * @return number of items added
         */
        @Override
        public int add(@NotNull ItemStack stack, int insertAt) {
            if (!canTryInsert(stack)) {
                return 0;
            }
            int weight = BundleHelper.getBundleWeight(stack);
            int added = 0;
            int toAdd = Math.min(stack.getCount(), getMaxAllowedByWeight(stack));
            if (toAdd > 0) {
                for (int i = 0; i < stacks.size(); i++) {
                    ItemStack nestedStack = stacks.get(i);
                    if (ItemStack.isSameItemSameTags(stack, nestedStack)) {
                        int amount = Math.min(toAdd, nestedStack.getMaxStackSize() - nestedStack.getCount());
                        if (amount > 0) {
                            stacks.set(i, nestedStack.copyWithCount(nestedStack.getCount() + amount));
                            stack.shrink(amount);
                            toAdd -= amount;
                            added += amount;
                            totalWeight += weight * amount;
                        }
                        if (toAdd <= 0) {
                            // Return early if all the item was added.
                            return added;
                        }
                    }
                }

                // Add remaining to new stack
                if (canAddStack()) {
                    if (selectedIndex != -1) {
                        selectedIndex++;
                    }
                    added += toAdd;
                    ItemStack newStack = stack.split(toAdd);
                    totalWeight += BundleHelper.getBundleWeightOfStack(newStack);
                    stacks.add(0, newStack);
                }
            }
            return added;
        }

        /**
         * Remove the given stack from the bundle contents, updating total weight.
         *
         * @param stack stack to remove
         * @param toRemove how many of the given stack to remove
         * @return how many items were removed
         */
        @Override
        public int remove(@NotNull ItemStack stack, int toRemove) {
            if (isEmpty() || stack.isEmpty()) {
                return 0;
            }
            int removed = 0;
            int weight = BundleHelper.getBundleWeight(stack);

            // Track if the selected index was removed
            boolean removedSelected = false;
            for (int i = 0; i < stacks.size() && toRemove > 0; i++) {
                ItemStack nestedStack = stacks.get(i);
                if (ItemStack.isSameItemSameTags(stack, nestedStack)) {
                    if (toRemove >= nestedStack.getCount()) {
                        removed += nestedStack.getCount();
                        toRemove -= nestedStack.getCount();
                        totalWeight -= weight * nestedStack.getCount();
                        stacks.remove(i);
                        // update selected index when removing
                        if (i < selectedIndex) {
                            selectedIndex--;
                        }
                        else if (i == selectedIndex) {
                            removedSelected = true;
                        }
                        i--;
                    }
                    else {
                        removed += toRemove;
                        totalWeight -= weight * toRemove;
                        stacks.set(i, nestedStack.copyWithCount(nestedStack.getCount() - toRemove));
                        toRemove = 0;
                    }
                }
            }

            // Update selected index to nearest matching stack
            if (removedSelected) {
                selectedIndex = nextSelectedIndex(stack, selectedIndex);
            }

            return removed;
        }

        /**
         * Pops the selected stack from the contents, updating occupancy.
         *
         * @return ItemStack popped from the contents; EMPTY if none
         */
        @Override
        public @NotNull ItemStack popSelectedStack() {
            if (isEmpty()) {
                return ItemStack.EMPTY;
            }

            ItemStack selectedStack = stacks.remove(clampIndex(selectedIndex)).copy();
            if (selectedIndex != -1) {
                selectedIndex = nextSelectedIndex(selectedStack, selectedIndex);
            }
            totalWeight -= BundleHelper.getBundleWeightOfStack(selectedStack);
            return selectedStack;
        }

        /**
         * Remove all stacks from contents and clear occupancy.
         *
         * @return List of copies of previous contents
         */
        @Override
        public @NotNull List<ItemStack> popAllStacks() {
            List<ItemStack> copies = Lists.transform(stacks, ItemStack::copy);
            stacks.clear();
            selectedIndex = -1;
            totalWeight = 0;
            return copies;
        }

        /**
         * Gets the bundle stored in the related BundleContents.
         *
         * @return bundle stack that holds the contents
         */
        @Override
        public @NotNull ItemStack getBundleStack() {
            return bundleStack;
        }

        /**
         * Gets the modified stacks.
         *
         * @return List of stack contents
         */
        @Override
        public @NotNull List<ItemStack> getStacks() {
            return stacks;
        }

        /**
         * Gets the modified selected index.
         *
         * @return selected index; -1 if there is none
         */
        @Override
        public int getSelectedIndex() {
            return selectedIndex;
        }

        /**
         * Modifies the selected index
         *
         * @param selectedIndex     new selected index
         */
        @Override
        public void setSelectedIndex(int selectedIndex) {
            this.selectedIndex = selectedIndex;
        }

        /**
         * Gets the modified total occupancy.
         *
         * @return total quiver occupancy that the contents hold
         */
        @Override
        public int getTotalWeight() {
            return totalWeight;
        }
    }
}

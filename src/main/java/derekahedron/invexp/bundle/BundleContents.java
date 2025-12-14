package derekahedron.invexp.bundle;

import com.google.common.collect.Lists;
import derekahedron.invexp.util.ContainerItemContents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class BundleContents extends ContainerItemContents implements BundleContentsReader {
    public final ItemStack bundleStack;
    private BundleContentsComponent component;

    /**
     * Creates a new QuiverContents object. Private as of() should be used to create
     * QuiverContents, so we can ensure they are valid.
     *
     * @param stack stack containing the contents
     * @param component contents component
     */
    private BundleContents(ItemStack stack, BundleContentsComponent component) {
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
    @Nullable
    public static BundleContents of(@Nullable ItemStack stack) {
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
    public void validate(Player player) {
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

    @Override
    public List<ItemStack> getStacks() {
        return component.stacks;
    }

    @Override
    public int getSelectedIndex() {
        return component.selectedIndex;
    }

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

    @Override
    public ItemStack getBundleStack() {
        return bundleStack;
    }

    @Override
    public int getTotalWeight() {
        return component.getTotalWeight();
    }

    @Override
    public BundleContents.Builder getBuilder() {
        return new BundleContents.Builder();
    }

    /**
     * Builder for BundleContents. Contains a copy of the bundle contents to be modified.
     */
    public class Builder extends ContainerItemContents.Builder implements BundleContentsReader {
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

        @Override
        public void apply() {
            component = new BundleContentsComponent(
                    List.copyOf(stacks),
                    Mth.clamp(selectedIndex, -1, stacks.size() - 1)
            );
            component.setComponent(bundleStack);
        }

        @Override
        public int add(ItemStack stack, int insertAt) {
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

        @Override
        public int remove(ItemStack stack, int toRemove) {
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

        @Override
        public ItemStack popSelectedStack() {
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

        @Override
        public List<ItemStack> popAllStacks() {
            List<ItemStack> copies = Lists.transform(stacks, ItemStack::copy);
            stacks.clear();
            selectedIndex = -1;
            totalWeight = 0;
            return copies;
        }

        @Override
        public ItemStack getBundleStack() {
            return bundleStack;
        }

        @Override
        public List<ItemStack> getStacks() {
            return stacks;
        }

        @Override
        public int getSelectedIndex() {
            return selectedIndex;
        }

        @Override
        public void setSelectedIndex(int selectedIndex) {
            this.selectedIndex = selectedIndex;
        }

        @Override
        public int getTotalWeight() {
            return totalWeight;
        }
    }
}

package derekahedron.invexp.bundle;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.item.BundleItem;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class BundleContentsComponent {
    public static final String ITEMS_KEY = "Items";
    public static final String SELECTED_INDEX_KEY = "InvExpSelectedIndex";

    public final List<ItemStack> stacks;
    public final int selectedIndex;
    public final int totalWeight;

    /**
     * Creates a new BundleContentsComponent from the given values.
     *
     * @param stacks            list of stacks to give the component
     * @param selectedIndex     selected index of the component
     */
    public BundleContentsComponent(List<ItemStack> stacks, int selectedIndex) {
        this.stacks = stacks;
        this.selectedIndex = selectedIndex;
        this.totalWeight = calculateTotalWeight(stacks);
    }

    /**
     * Creates a new empty BundleContentsComponent.
     */
    public BundleContentsComponent() {
        this(List.of(), -1);
    }

    /**
     * Gets stacks for the component.
     *
     * @return  list of stacks in the component
     */
    public List<ItemStack> getStacks() {
        return stacks;
    }

    /**
     * Gets selected index for the component.
     *
     * @return  selected index of the component
     */
    public int getSelectedIndex() {
        return selectedIndex;
    }

    /**
     * Gets the total weight stored of this bundle.
     *
     * @return  total occupancy of the component
     */
    public int getTotalWeight() {
        return totalWeight;
    }

    /**
     * Gets the validity of the component.
     *
     * @return  if the component is valid
     */
    public boolean isValid() {
        for (ItemStack nestedStack : stacks) {
            if (!nestedStack.getItem().canFitInsideContainerItems()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the component has stacks.
     *
     * @return  if the component is empty
     */
    public boolean isEmpty() {
        return stacks.isEmpty();
    }

    /**
     * Returns the selected stack stored in the component
     *
     * @return  the selected stack; EMPTY if there is none
     */
    public ItemStack getSelectedStack() {
        if (isEmpty()) {
            return ItemStack.EMPTY;
        }
        return stacks.get(selectedIndex);
    }

    /**
     * Sets the bundle contents in the given stack to those of this component.
     *
     * @param stack ItemStack to set contents for
     */
    public void setComponent(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        ListTag contentsTag = new ListTag();
        if (!isEmpty()) {
            for (ItemStack nestedStack : getStacks()) {
                CompoundTag itemTag = new CompoundTag();
                nestedStack.save(itemTag);
                contentsTag.add(itemTag);
            }
            tag.put(ITEMS_KEY, contentsTag);
        }
        else {
            tag.remove(ITEMS_KEY);
        }
        if (getSelectedIndex() != -1) {
            tag.putInt(SELECTED_INDEX_KEY, getSelectedIndex());
        }
        else {
            tag.remove(SELECTED_INDEX_KEY);
        }
    }

    /**
     * Helper for getting the BundleContentsComponent from a valid stack.
     *
     * @param stack     stack to get the component from
     * @return          BundleContentsComponent of the stack; null if invalid
     */
    @Nullable
    public static BundleContentsComponent getComponent(@Nullable ItemStack stack) {
        if (stack != null && !stack.isEmpty() && stack.getItem() instanceof BundleItem) {
            CompoundTag tag = stack.getTag();
            if (tag != null && tag.contains(ITEMS_KEY)) {
                ListTag contentsTag = tag.getList(ITEMS_KEY, 10);
                List<ItemStack> stacks = new ArrayList<>(contentsTag.size());
                for (int i = 0; i < contentsTag.size(); i++) {
                    stacks.add(ItemStack.of(contentsTag.getCompound(i)));
                }
                int selectedIndex = -1;
                if (tag.contains(SELECTED_INDEX_KEY)) {
                    selectedIndex = tag.getInt(SELECTED_INDEX_KEY);
                }
                return new BundleContentsComponent(stacks, selectedIndex);
            }
            else {
                return new BundleContentsComponent();
            }
        }
        else {
            return null;
        }
    }

    /**
     * Calculates the total bundle weight of the given stacks.
     *
     * @param stacks ItemStacks to calculate weight of
     * @return total bundle weight of the given stacks
     */
    public static int calculateTotalWeight(List<ItemStack> stacks) {
        int totalWeight = 0;
        for (ItemStack stack : stacks) {
            totalWeight += BundleHelper.getBundleWeightOfStack(stack);
        }
        return totalWeight;
    }
}

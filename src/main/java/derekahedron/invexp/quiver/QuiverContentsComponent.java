package derekahedron.invexp.quiver;

import derekahedron.invexp.item.QuiverItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import org.apache.commons.lang3.math.Fraction;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class QuiverContentsComponent {
    public static final String COMPOUND_KEY = "InvExpQuiverContents";
    public static final String ITEMS_KEY = "Items";
    public static final String SELECTED_INDEX_KEY = "SelectedIndex";
    public static final String TOTAL_OCCUPANCY_KEY = "TotalOccupancy";
    public static final String NUMERATOR_KEY = "Numerator";
    public static final String DENOMINATOR_KEY = "Denominator";

    public final List<ItemStack> stacks;
    public final int selectedIndex;
    private final Fraction totalOccupancy;

    /**
     * Creates a new QuiverContentsComponent from the given values.
     *
     * @param stacks            list of stacks to give the component
     * @param selectedIndex     selected index of the component
     */
    public QuiverContentsComponent(List<ItemStack> stacks, int selectedIndex) {
        this(stacks, selectedIndex, calculateTotalOccupancy(stacks));
    }

    /**
     * Creates a new QuiverContentsComponent from the given values.
     *
     * @param stacks list of stacks to give the component
     * @param selectedIndex selected index of the component
     * @param totalOccupancy total occupancy to store in the component
     */
    public QuiverContentsComponent(
            List<ItemStack> stacks, int selectedIndex, Fraction totalOccupancy
    ) {
        this.stacks = stacks;
        this.selectedIndex = selectedIndex;
        this.totalOccupancy = totalOccupancy;
    }

    /**
     * Creates a new empty QuiverContentsComponent.
     */
    public QuiverContentsComponent() {
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
     * Gets the total occupancy stored in the quiver.
     *
     * @return  total occupancy of the component
     */
    public Fraction getTotalOccupancy() {
        return totalOccupancy;
    }

    /**
     * Gets the validity of the component.
     *
     * @return  if the component is valid
     */
    public boolean isValid() {
        for (ItemStack nestedStack : stacks) {
            // Makes sure all items are arrows
            if (!nestedStack.getItem().canFitInsideContainerItems() || !nestedStack.is(ItemTags.ARROWS)) {
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
     * Sets the quiver contents in the given stack to those of this component.
     *
     * @param stack ItemStack to set contents for
     */
    public void setComponent(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        if (!isEmpty()) {
            CompoundTag quiverContents = new CompoundTag();
            ListTag contentsTag = new ListTag();
            for (ItemStack nestedStack : getStacks()) {
                CompoundTag itemTag = new CompoundTag();
                nestedStack.save(itemTag);
                contentsTag.add(itemTag);
            }
            quiverContents.put(ITEMS_KEY, contentsTag);
            quiverContents.putInt(SELECTED_INDEX_KEY, getSelectedIndex());

            CompoundTag occupancyTag = new CompoundTag();
            occupancyTag.putInt(NUMERATOR_KEY, totalOccupancy.getNumerator());
            occupancyTag.putInt(DENOMINATOR_KEY, totalOccupancy.getDenominator());
            quiverContents.put(TOTAL_OCCUPANCY_KEY, occupancyTag);
            tag.put(COMPOUND_KEY, quiverContents);
        }
        else {
            tag.remove(COMPOUND_KEY);
        }
    }

    /**
     * Helper for getting the QuiverContentsComponent from a valid stack.
     *
     * @param stack     stack to get the component from
     * @return          QuiverContentsComponent of the stack; null if invalid
     */
    @Nullable
    public static QuiverContentsComponent getComponent(@Nullable ItemStack stack) {
        if (stack != null && !stack.isEmpty() && stack.getItem() instanceof QuiverItem) {
            CompoundTag tag = stack.getTag();
            if (tag != null && tag.contains(COMPOUND_KEY)) {
                CompoundTag quiverContents = tag.getCompound(COMPOUND_KEY);
                ListTag contentsTag = quiverContents.getList(ITEMS_KEY, 10);
                List<ItemStack> stacks = new ArrayList<>(contentsTag.size());
                for (int i = 0; i < contentsTag.size(); i++) {
                    stacks.add(ItemStack.of(contentsTag.getCompound(i)));
                }
                int selectedIndex = quiverContents.getInt(SELECTED_INDEX_KEY);

                if (quiverContents.contains(TOTAL_OCCUPANCY_KEY, 10)) {
                    CompoundTag totalOccupancyTag = quiverContents.getCompound(TOTAL_OCCUPANCY_KEY);
                    Fraction totalOccupancy = Fraction.getFraction(
                            totalOccupancyTag.getInt(NUMERATOR_KEY),
                            totalOccupancyTag.getInt(DENOMINATOR_KEY));
                    return new QuiverContentsComponent(stacks, selectedIndex, totalOccupancy);
                } else {
                    return new QuiverContentsComponent(stacks, selectedIndex);
                }
            }
            else {
                return new QuiverContentsComponent();
            }
        }
        else {
            return null;
        }
    }

    /**
     * Calculates the total occupancy of the given stacks.
     *
     * @param stacks ItemStacks to calculate occupancy of
     * @return total occupancy of the given stacks
     */
    public static Fraction calculateTotalOccupancy(List<ItemStack> stacks) {
        Fraction totalOccupancy = Fraction.ZERO;
        for (ItemStack nestedStack : stacks) {
            totalOccupancy = totalOccupancy.add(QuiverHelper.getOccupancyOfStack(nestedStack));
        }
        return totalOccupancy;
    }
}

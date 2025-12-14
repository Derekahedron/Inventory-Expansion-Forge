package derekahedron.invexp.sack;

import derekahedron.invexp.item.SackItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.world.item.ItemStack;
import org.apache.commons.lang3.math.Fraction;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class SackContentsComponent {
    public static final String COMPOUND_KEY = "InvExpSackContents";
    public static final String TYPES_KEY = "Types";
    public static final String ITEMS_KEY = "Items";
    public static final String SELECTED_INDEX_KEY = "SelectedIndex";
    public static final String TOTAL_WEIGHT_KEY = "TotalWeight";
    public static final String NUMERATOR_KEY = "Numerator";
    public static final String DENOMINATOR_KEY = "Denominator";

    public final List<String> sackTypes;
    public final List<ItemStack> stacks;
    public final int selectedIndex;
    public final Fraction totalWeight;

    /**
     * Creates a new SackContentsComponent from the given values.
     *
     * @param sackTypes         sack types to start the component with
     * @param stacks            list of stacks to give the component
     * @param selectedIndex     selected index of the component
     */
    public SackContentsComponent(
            List<String> sackTypes, List<ItemStack> stacks, int selectedIndex
    ) {
        this(sackTypes, stacks, selectedIndex, calculateTotalWeight(stacks));
    }

    /**
     * Creates a new SackContentsComponent from the given values.
     *
     * @param sackTypes sack types to start the component with
     * @param stacks list of stacks to give the component
     * @param selectedIndex selected index of the component
     * @param totalWeight total sack weight to store in the component
     */
    public SackContentsComponent(
            List<String> sackTypes, List<ItemStack> stacks, int selectedIndex, Fraction totalWeight
    ) {
        this.sackTypes = sackTypes;
        this.stacks = stacks;
        this.selectedIndex = selectedIndex;
        this.totalWeight = totalWeight;
    }

    /**
     * Creates a new empty SackContentsComponent.
     */
    public SackContentsComponent() {
        this(List.of(), List.of(), -1);
    }

    /**
     * Gets sack types for the component.
     *
     * @return  list of sack types in the component
     */
    public List<String> getSackTypes() {
        return sackTypes;
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
     * Gets the total weight stored in the sack.
     *
     * @return  total weight of the component
     */
    public Fraction getTotalWeight() {
        return totalWeight;
    }

    /**
     * Gets the validity of the component.
     *
     * @return  if the component is valid
     */
    public boolean isValid() {
        if (SackDefaultManager.getInstance() == null) {
            return false;
        }

        if (totalWeight.compareTo(calculateTotalWeight(getStacks())) != 0) {
            return false;
        }

        // Because references can be decentralized, we use identifiers to check for type equality
        HashSet<String> sackTypesInSack = new HashSet<>();
        HashSet<String> sackTypes = new HashSet<>(getSackTypes());
        for (ItemStack stack : stacks) {
            if (!stack.getItem().canFitInsideContainerItems()) {
                return false;
            }
            // Make sure type exists and is in component
            String sackType = SacksHelper.getSackType(stack);
            if (sackType != null) {
                if (!sackTypes.contains(sackType)) {
                    return false;
                }
                sackTypesInSack.add(sackType);
            }
            else {
                return false;
            }
        }

        // Make sure the expected types and found types are equal
        return sackTypes.equals(sackTypesInSack);
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
     * Sets the sack contents in the given stack to those of this component.
     *
     * @param stack ItemStack to set contents for
     */
    public void setComponent(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();

        if (!isEmpty()) {
            CompoundTag sackContents = new CompoundTag();
            ListTag typesTag = new ListTag();
            for (String sackType : getSackTypes()) {
                typesTag.add(StringTag.valueOf(sackType));
            }
            sackContents.put(TYPES_KEY, typesTag);

            ListTag contentsTag = new ListTag();
            for (ItemStack nestedStack : getStacks()) {
                CompoundTag itemTag = new CompoundTag();
                nestedStack.save(itemTag);
                contentsTag.add(itemTag);
            }
            sackContents.put(ITEMS_KEY, contentsTag);

            sackContents.putInt(SELECTED_INDEX_KEY, getSelectedIndex());

            CompoundTag weightTag = new CompoundTag();
            weightTag.putInt(NUMERATOR_KEY, totalWeight.getNumerator());
            weightTag.putInt(DENOMINATOR_KEY, totalWeight.getDenominator());
            sackContents.put(TOTAL_WEIGHT_KEY, weightTag);

            tag.put(COMPOUND_KEY, sackContents);
        }
        else {
            tag.remove(COMPOUND_KEY);
        }
    }

    /**
     * Helper for getting the SackContentsComponent from a valid stack.
     *
     * @param stack     stack to get the component from
     * @return          SackContentsComponent of the stack; null if invalid
     */
    @Nullable
    public static SackContentsComponent getComponent(@Nullable ItemStack stack) {
        if (stack != null && !stack.isEmpty() && stack.getItem() instanceof SackItem) {
            CompoundTag tag = stack.getTag();
            if (tag != null && tag.contains(COMPOUND_KEY)) {
                CompoundTag sackContents = tag.getCompound(COMPOUND_KEY);

                ListTag typesTag = sackContents.getList(TYPES_KEY, 8);
                List<String> sackTypes = new ArrayList<>(typesTag.size());
                for (int i = 0; i < typesTag.size(); i++) {
                    sackTypes.add(typesTag.getString(i));
                }

                ListTag contentsTag = sackContents.getList(ITEMS_KEY, 10);
                List<ItemStack> stacks = new ArrayList<>(contentsTag.size());
                for (int i = 0; i < contentsTag.size(); i++) {
                    stacks.add(ItemStack.of(contentsTag.getCompound(i)));
                }
                int selectedIndex = sackContents.getInt(SELECTED_INDEX_KEY);
                if (sackContents.contains(TOTAL_WEIGHT_KEY, 10)) {
                    CompoundTag totalWeightTag = sackContents.getCompound(TOTAL_WEIGHT_KEY);
                    Fraction totalWeight = Fraction.getFraction(
                            totalWeightTag.getInt(NUMERATOR_KEY),
                            totalWeightTag.getInt(DENOMINATOR_KEY));
                    return new SackContentsComponent(sackTypes, stacks, selectedIndex, totalWeight);
                } else {
                    return new SackContentsComponent(sackTypes, stacks, selectedIndex);
                }
            }
            else {
                return new SackContentsComponent();
            }
        }
        else {
            return null;
        }
    }

    /**
     * Calculates the total sack weight of the given stacks.
     *
     * @param stacks ItemStacks to calculate weight of
     * @return total sack weight of the given stacks
     */
    public static Fraction calculateTotalWeight(List<ItemStack> stacks) {
        Fraction totalWeight = Fraction.ZERO;
        for (ItemStack stack : stacks) {
            totalWeight = totalWeight.add(SacksHelper.getSackWeightOfStack(stack));
        }
        return totalWeight;
    }
}

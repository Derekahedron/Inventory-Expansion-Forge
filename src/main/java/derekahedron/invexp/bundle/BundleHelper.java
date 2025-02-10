package derekahedron.invexp.bundle;

import derekahedron.invexp.item.BetterBundleItem;
import net.minecraft.world.item.BundleItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

public class BundleHelper {

    /**
     * Return the maximum number of total stacks that can occupy this bundle.
     *
     * @param stack     stack to test
     * @return          maximum bundle stacks this item can hold as a bundle
     */
    public static int getMaxBundleWeightStacks(@NotNull ItemStack stack) {
        if (stack.is(Items.BUNDLE)) {
            return 1;
        }
        else if (stack.getItem() instanceof BetterBundleItem betterBundleItem) {
            return betterBundleItem.getMaxBundleWeightStacks();
        }
        return 0;
    }

    /**
     * Return the maximum bundle weight an item can hold.
     *
     * @param stack     stack to test
     * @return          maximum bundle weight this item can hold
     */
    public static int getMaxBundleWeight(@NotNull ItemStack stack) {
        return getMaxBundleWeightStacks(stack) * 64;
    }

    /**
     * Return the maximum number of stacks this item can hold as a bundle.
     *
     * @param stack     stack to test
     * @return          maximum stacks this item can hold as a bundle
     */
    public static int getMaxBundleStacks(@NotNull ItemStack stack) {
        if (stack.is(Items.BUNDLE)) {
            return 64;
        }
        else if (stack.getItem() instanceof BetterBundleItem betterBundleItem) {
            return betterBundleItem.getMaxBundleStacks();
        }
        return 0;
    }

    /**
     * Gets the bundle weight related to this item.
     *
     * @param stack     stack to test
     * @return          bundle weight of the item
     */
    public static int getBundleWeight(@NotNull ItemStack stack) {
        return BundleItem.getWeight(stack);
    }

    /**
     * Gets bundle weight of entire stack.
     *
     * @param stack     stack to test
     * @return          weight this entire stack takes up
     */
    public static int getBundleWeightOfStack(@NotNull ItemStack stack) {
        return getBundleWeight(stack) * stack.getCount();
    }
}

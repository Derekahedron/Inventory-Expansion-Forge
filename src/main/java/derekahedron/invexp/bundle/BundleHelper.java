package derekahedron.invexp.bundle;

import derekahedron.invexp.item.BetterBundleItem;
import net.minecraft.world.item.BundleItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class BundleHelper {

    /**
     * Return the maximum number of total stacks that can occupy this bundle.
     *
     * @param stack     stack to test
     * @return          maximum bundle stacks this item can hold as a bundle
     */
    public static int getMaxBundleWeightStacks(ItemStack stack) {
        if (stack.is(Items.BUNDLE)) {
            return 1;
        } else if (stack.getItem() instanceof BetterBundleItem betterBundleItem) {
            return betterBundleItem.getMaxBundleWeightStacks();
        }
        return 0;
    }

    /**
     * Gets the bundle weight related to this item.
     *
     * @param stack     stack to test
     * @return          bundle weight of the item
     */
    public static int getBundleWeight(ItemStack stack) {
        return BundleItem.getWeight(stack);
    }

    /**
     * Gets bundle weight of entire stack.
     *
     * @param stack     stack to test
     * @return          weight this entire stack takes up
     */
    public static int getBundleWeightOfStack(ItemStack stack) {
        return getBundleWeight(stack) * stack.getCount();
    }
}

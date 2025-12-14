package derekahedron.invexp.sack;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import org.apache.commons.lang3.math.Fraction;

import javax.annotation.Nullable;

/**
 * Contains helper functions and default values for data components related to sacks.
 */
public class SacksHelper {
    public static final Fraction DEFAULT_SACK_WEIGHT = Fraction.ONE;

    /**
     * Gets the registry entry of the sack type related to this item.
     *
     * @param stack     stack to test
     * @return          registry entry of the sack type; null if there is none
     */
    @Nullable
    public static String getSackType(ItemStack stack) {
        if (SackDefaultManager.getInstance() != null) {
            ResourceKey<SackType> sackType = SackDefaultManager.getInstance().getType(stack);
            return sackType == null ? null
                    : sackType.location().toString();
        }

        // Fail if manager is not created before running this
        throw new RuntimeException("Sack Loading Error");
    }

    /**
     * Gets the sack weight related to this item.
     *
     * @param stack     stack to test
     * @return          sack weight of the item
     */
    public static Fraction getSackWeight(ItemStack stack) {
        if (SackDefaultManager.getInstance() != null) {
            return SackDefaultManager.getInstance().getWeight(stack)
                    .divideBy(Fraction.getFraction(stack.getMaxStackSize()));
        }

        // Fail if manager is not created before running this
        throw new RuntimeException("Sack Loading Error");
    }

    /**
     * Gets sack weight of entire stack.
     *
     * @param stack     stack to test
     * @return          weight this entire stack takes up
     */
    public static Fraction getSackWeightOfStack(ItemStack stack) {
        return getSackWeight(stack).multiplyBy(Fraction.getFraction(stack.getCount()));
    }
}

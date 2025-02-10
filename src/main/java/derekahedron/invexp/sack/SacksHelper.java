package derekahedron.invexp.sack;

import derekahedron.invexp.item.SackItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Contains helper functions and default values for data components related to sacks.
 */
public class SacksHelper {
    public static final int DEFAULT_SACK_WEIGHT = 100;

    /**
     * Gets the maximum stack types an item can hold.
     *
     * @param stack     stack to test
     * @return          maximum sack types this item can hold
     */
    public static int getMaxSackTypes(@NotNull ItemStack stack) {
        if (stack.getItem() instanceof SackItem sackItem) {
            return sackItem.getMaxSackTypes();
        }
        return 0;
    }

    /**
     * Return the maximum sack weight an item can hold.
     *
     * @param stack     stack to test
     * @return          maximum sack weight this item can hold
     */
    public static int getMaxSackWeight(@NotNull ItemStack stack) {
        if (stack.getItem() instanceof SackItem sackItem) {
            return sackItem.getMaxSackWeight();
        }
        return 0;
    }

    /**
     * Return the maximum number of stacks this item can hold as a sack.
     *
     * @param stack     stack to test
     * @return          maximum stacks this item can hold as a sack
     */
    public static int getMaxSackStacks(@NotNull ItemStack stack) {
        if (stack.getItem() instanceof SackItem sackItem) {
            return sackItem.getMaxSackStacks();
        }
        return 0;
    }

    /**
     * Gets the registry entry of the sack type related to this item.
     *
     * @param stack     stack to test
     * @return          registry entry of the sack type; null if there is none
     */
    public static @Nullable String getSackType(@NotNull ItemStack stack) {
        if (SackDataManager.getInstance() != null) {
            SackDataManager.DefaultSackData data = SackDataManager.getInstance().getDefaultSackData(stack.getItem());
            if (data != null) {
                return data.sackType();
            }

            // If not defined, try to guess based on item
            if (stack.getItem() instanceof SpawnEggItem) {
                return "invexp:spawn_egg";
            } else if (stack.getItem() instanceof BucketItem) {
                return "invexp:bucket";
            }

            return null;
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
    public static int getSackWeight(@NotNull ItemStack stack) {
        if (SackDataManager.getInstance() != null) {
            SackDataManager.DefaultSackData data = SackDataManager.getInstance().getDefaultSackData(stack.getItem());
            if (data != null) {
                return data.sackWeight();
            }

            // If not defined, try to guess based on item
            if (stack.getItem() instanceof PotionItem && PotionUtils.getPotion(stack) == Potions.EMPTY) {
                return DEFAULT_SACK_WEIGHT * 4;
            }
            else if (stack.getItem() instanceof BucketItem bucketItem) {
                if (bucketItem.getFluid().isSame(Fluids.EMPTY)) {
                    return DEFAULT_SACK_WEIGHT * 4;
                }
                else {
                    return DEFAULT_SACK_WEIGHT * 16;
                }
            }

            return DEFAULT_SACK_WEIGHT;
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
    public static int getSackWeightOfStack(@NotNull ItemStack stack) {
        return getSackWeight(stack) * stack.getCount();
    }

}

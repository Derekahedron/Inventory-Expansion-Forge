package derekahedron.invexp.item;

import derekahedron.invexp.bundle.BundleContents;
import derekahedron.invexp.util.OpenItemTexturesRegistry;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BundleItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.apache.commons.lang3.math.Fraction;
public class BetterBundleItem extends BundleItem {
    public static final int FULL_ITEM_BAR_COLOR = Mth.color(1.0F, 0.33F, 0.33F);

    public BetterBundleItem(Properties properties) {
        super(properties);
        OpenItemTexturesRegistry.addItem(this);
    }

    /**
     * Makes sure the contents are validated when ticked in a player inventory.
     *
     * @param stack bundle stack to tick
     * @param level world the stack is ticked in
     * @param entity the entity holding the item; usually a player
     * @param slot slot the item is in
     * @param selected whether the item is in the selected hotbar slot
     */
    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        if (entity instanceof Player player) {
            BundleContents contents = BundleContents.of(stack);
            if (contents == null) {
                return;
            }
            contents.validate(player);
        }
    }

    /**
     * Gets how full the item bar should be based on how full the bundle is.
     *
     * @param stack bundle stack to test
     * @return int 0-13 representing how full the bundle is
     */
    @Override
    public int getBarWidth(ItemStack stack) {
        BundleContents contents = BundleContents.of(stack);
        if (contents == null) {
            return 0;
        }
        Fraction fillFraction = contents.getFillFraction();
        return Math.min(
                13,
                1 + (fillFraction.getNumerator() * 12 / fillFraction.getDenominator())
        );
    }

    public int getMaxBundleWeightStacks() {
        return 1;
    }

    public int getMaxBundleStacks() {
        return 64;
    }
}

package derekahedron.invexp.util;

import derekahedron.invexp.InventoryExpansion;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;

/**
 * Contains utility function for Inventory Expansion
 */
public class InvExpUtil {

    /**
     * Creates a new identifier under the Inventory Expansion namespace
     *
     * @param id    String to create the identifier for
     * @return      Identifier under the Inventory Expansion namespace
     */
    public static ResourceLocation location(String id) {
        return new ResourceLocation(InventoryExpansion.MOD_ID, id);
    }

    /**
     * Signal to players screen handler that content was changed
     *
     * @param player    player whose inventory changed
     */
    public static void onContentChanged(Player player) {
        AbstractContainerMenu menu = player.containerMenu;
        menu.slotsChanged(player.getInventory());
    }
}

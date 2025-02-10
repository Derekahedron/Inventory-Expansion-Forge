package derekahedron.invexp.util;

import derekahedron.invexp.InventoryExpansion;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import org.jetbrains.annotations.NotNull;

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
    public static @NotNull ResourceLocation location(@NotNull String id) {
        return new ResourceLocation(InventoryExpansion.MOD_ID, id);
    }

    /**
     * Signal to players screen handler that content was changed
     *
     * @param player    player whose inventory changed
     */
    public static void onContentChanged(@NotNull Player player) {
        AbstractContainerMenu menu = player.containerMenu;
        menu.slotsChanged(player.getInventory());
    }
}

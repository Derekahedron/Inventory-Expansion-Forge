package derekahedron.invexp.client.util;

import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class InvExpClientUtil {

    public static Slot getTrueSlot(Slot slot, Player player) {
        if (player == null) {
            return null;
        }
        // Creative screens do not have slot ids that are synced, so we must find the corresponding slot
        // in the creative inventory
        if (player.containerMenu instanceof CreativeModeInventoryScreen.ItemPickerMenu) {
            ItemStack stack = slot.getItem();
            slot = null;
            for (Slot s : player.inventoryMenu.slots) {
                if (s.getItem() == stack) {
                    slot = s;
                    break;
                }
            }
        }
        return slot;
    }
}

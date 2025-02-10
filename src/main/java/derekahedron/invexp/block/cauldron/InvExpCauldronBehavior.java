package derekahedron.invexp.block.cauldron;

import derekahedron.invexp.item.InvExpItems;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;

/**
 * Initializer for cauldron behaviors
 */
public class InvExpCauldronBehavior {

    /**
     * Adds cauldron behaviors for modded items
     */
    public static void initialize(FMLLoadCompleteEvent event) {
        event.enqueueWork(() -> {
            CauldronInteraction.WATER.put(InvExpItems.SACK.get(), CauldronInteraction.DYED_ITEM);
        });
    }
}

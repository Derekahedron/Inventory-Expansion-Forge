package derekahedron.invexp.block.dispenser;

import derekahedron.invexp.item.InvExpItems;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;

/**
 * Initializer for dispenser behaviors
 */
public class InvExpDispenserBehavior {

    /**
     * Adds dispenser behaviors for modded items
     */
    public static void initialize(FMLLoadCompleteEvent event) {
        event.enqueueWork(() -> {
            DispenserBlock.registerBehavior(InvExpItems.SACK::get, new SackDispenserBehavior());
            DispenserBlock.registerBehavior(InvExpItems.QUIVER::get, new QuiverDispenserBehavior());
        });
    }
}

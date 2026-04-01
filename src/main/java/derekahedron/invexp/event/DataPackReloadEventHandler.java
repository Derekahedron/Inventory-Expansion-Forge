package derekahedron.invexp.event;

import derekahedron.invexp.InventoryExpansion;
import derekahedron.invexp.sack.SackDefaultManager;
import net.minecraftforge.event.TagsUpdatedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = InventoryExpansion.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DataPackReloadEventHandler {

    @SubscribeEvent
    public static void updateSackDefaults(TagsUpdatedEvent event) {
        if (event.shouldUpdateStaticData()) {
            SackDefaultManager.createNewInstance(event.getRegistryAccess());
        }
    }
}

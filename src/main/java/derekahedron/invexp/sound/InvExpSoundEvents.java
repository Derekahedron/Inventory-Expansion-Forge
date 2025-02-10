package derekahedron.invexp.sound;

import derekahedron.invexp.InventoryExpansion;
import derekahedron.invexp.util.InvExpUtil;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class InvExpSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS;

    public static final RegistryObject<SoundEvent> ITEM_SACK_INSERT;
    public static final RegistryObject<SoundEvent> ITEM_SACK_REMOVE_ONE;
    public static final RegistryObject<SoundEvent> ITEM_QUIVER_INSERT;
    public static final RegistryObject<SoundEvent> ITEM_QUIVER_REMOVE_ONE;

    static {
        SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, InventoryExpansion.MOD_ID);

        ITEM_SACK_INSERT = register("item.sack.insert");
        ITEM_SACK_REMOVE_ONE = register("item.sack.remove_one");
        ITEM_QUIVER_INSERT = register("item.quiver.insert");
        ITEM_QUIVER_REMOVE_ONE = register("item.quiver.remove_one");
    }

    /**
     * Register an Inventory Expansion sound event.
     *
     * @param id String to register the sound event under
     * @return SoundEvent that was created and registered
     */
    public static RegistryObject<SoundEvent> register(String id) {
        return SOUND_EVENTS.register(id, () -> SoundEvent.createVariableRangeEvent(InvExpUtil.location(id)));
    }
}

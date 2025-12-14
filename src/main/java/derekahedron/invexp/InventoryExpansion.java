package derekahedron.invexp;

import com.mojang.logging.LogUtils;
import derekahedron.invexp.block.cauldron.InvExpCauldronBehavior;
import derekahedron.invexp.block.dispenser.InvExpDispenserBehavior;
import derekahedron.invexp.client.ClientConfig;
import derekahedron.invexp.item.InvExpCreativeTabs;
import derekahedron.invexp.item.InvExpItems;
import derekahedron.invexp.network.InvExpPacketHandler;
import derekahedron.invexp.recipe.InvExpRecipes;
import derekahedron.invexp.registry.InvExpRegistryKeys;
import derekahedron.invexp.sound.InvExpSoundEvents;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(InventoryExpansion.MOD_ID)
public class InventoryExpansion
{
    public static final String MOD_ID = "invexp";
    public static final String MOD_NAME = "Inventory Expansion";
    public static final Logger LOGGER = LogUtils.getLogger();

    public InventoryExpansion(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        InvExpItems.ITEMS.register(modEventBus);
        InvExpRecipes.RECIPE_SERIALIZERS.register(modEventBus);
        InvExpSoundEvents.SOUND_EVENTS.register(modEventBus);

        modEventBus.addListener(InvExpRegistryKeys::initialize);
        modEventBus.addListener(InvExpPacketHandler::initialize);
        modEventBus.addListener(InvExpCauldronBehavior::initialize);
        modEventBus.addListener(InvExpDispenserBehavior::initialize);
        modEventBus.addListener(InvExpCreativeTabs::initialize);

        context.registerConfig(ModConfig.Type.CLIENT, ClientConfig.CLIENT_SPEC);
        context.registerConfig(ModConfig.Type.SERVER, Config.SERVER_SPEC);
    }
}

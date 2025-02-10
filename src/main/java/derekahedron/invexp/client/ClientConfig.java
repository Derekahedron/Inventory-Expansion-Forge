package derekahedron.invexp.client;

import derekahedron.invexp.InventoryExpansion;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = InventoryExpansion.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientConfig {

    private static final ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.BooleanValue DISPLAY_SACK_DATA_IN_TOOLTIP = CLIENT_BUILDER
            .comment("If sack data for the item should be displayed in the advanced tooltip (for debugging)")
            .define("displaySackDataInTooltip", false);

    public static final ForgeConfigSpec CLIENT_SPEC = CLIENT_BUILDER.build();

    public static boolean displaySackDataInTooltip;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        displaySackDataInTooltip = DISPLAY_SACK_DATA_IN_TOOLTIP.get();
    }
}

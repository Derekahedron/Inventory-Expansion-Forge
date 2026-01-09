package derekahedron.invexp.client;

import derekahedron.invexp.InventoryExpansion;
import derekahedron.invexp.client.gui.tooltip.BetterClientBundleTooltip;
import derekahedron.invexp.client.gui.tooltip.ClientQuiverTooltip;
import derekahedron.invexp.client.gui.tooltip.ClientSackTooltip;
import derekahedron.invexp.client.util.OpenItemTextures;
import derekahedron.invexp.item.InvExpItems;
import derekahedron.invexp.item.tooltip.BetterBundleTooltip;
import derekahedron.invexp.item.tooltip.QuiverTooltip;
import derekahedron.invexp.item.tooltip.SackTooltip;
import derekahedron.invexp.quiver.QuiverContents;
import derekahedron.invexp.util.InvExpUtil;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.client.event.RegisterClientTooltipComponentFactoriesEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = InventoryExpansion.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class InventoryExpansionClient {

    @SubscribeEvent
    public static void setupTooltips(RegisterClientTooltipComponentFactoriesEvent event)
    {
        event.register(SackTooltip.class, (sackTooltipData) -> new ClientSackTooltip(sackTooltipData.contents()));
        event.register(QuiverTooltip.class, (quiverTooltipData) -> new ClientQuiverTooltip(quiverTooltipData.contents()));
        event.register(BetterBundleTooltip.class, BetterClientBundleTooltip::new);
    }

    @SubscribeEvent
    public static void setupItemColors(RegisterColorHandlersEvent.Item event) {
        event.register((ItemStack stack, int tintIndex) -> tintIndex > 0 ? -1 : ((DyeableLeatherItem) stack.getItem()).getColor(stack), InvExpItems.SACK.get());
    }

    @SubscribeEvent
    public static void setupItemOverrides(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ItemProperties.register(InvExpItems.QUIVER.get(), InvExpUtil.location("quiver/has_contents"), (stack, level, entity, id) -> {
                QuiverContents contents = QuiverContents.of(stack);
                return contents != null && !contents.isEmpty() ? 1.0F : 0.0F;
            });
            ItemProperties.register(Items.BUNDLE, new ResourceLocation("filled"),
                    (stack, level, entity, id) -> 1.0F);
        });
    }

    @SubscribeEvent
    public static void setupSackOpenTextures(ModelEvent.RegisterAdditional event) {
        for (ModelResourceLocation resource : OpenItemTextures.setupLocations()) {
            event.register(resource);
        }
    }
}

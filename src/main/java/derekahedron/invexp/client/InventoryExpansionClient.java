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
import derekahedron.invexp.sack.SackContents;
import derekahedron.invexp.util.InvExpUtil;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.world.item.BundleItem;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.ItemStack;
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
            ItemProperties.register(InvExpItems.SACK.get(), InvExpUtil.location("sack/has_contents"), (stack, level, entity, id) -> {
                SackContents contents = SackContents.of(stack);
                return contents != null && !contents.isEmpty() ? 1.0F : 0.0F;
            });
            ItemProperties.register(InvExpItems.QUIVER.get(), InvExpUtil.location("quiver/has_contents"), (stack, level, entity, id) -> {
                QuiverContents contents = QuiverContents.of(stack);
                return contents != null && !contents.isEmpty() ? 1.0F : 0.0F;
            });
            ItemProperties.register(InvExpItems.WHITE_BUNDLE.get(), InvExpUtil.location("bundle/has_contents"),
                    (stack, level, entity, id) -> BundleItem.getContentWeight(stack) > 0 ? 1.0F : 0.0F);
            ItemProperties.register(InvExpItems.ORANGE_BUNDLE.get(), InvExpUtil.location("bundle/has_contents"),
                    (stack, level, entity, id) -> BundleItem.getContentWeight(stack) > 0 ? 1.0F : 0.0F);
            ItemProperties.register(InvExpItems.MAGENTA_BUNDLE.get(), InvExpUtil.location("bundle/has_contents"),
                    (stack, level, entity, id) -> BundleItem.getContentWeight(stack) > 0 ? 1.0F : 0.0F);
            ItemProperties.register(InvExpItems.LIGHT_BLUE_BUNDLE.get(), InvExpUtil.location("bundle/has_contents"),
                    (stack, level, entity, id) -> BundleItem.getContentWeight(stack) > 0 ? 1.0F : 0.0F);
            ItemProperties.register(InvExpItems.YELLOW_BUNDLE.get(), InvExpUtil.location("bundle/has_contents"),
                    (stack, level, entity, id) -> BundleItem.getContentWeight(stack) > 0 ? 1.0F : 0.0F);
            ItemProperties.register(InvExpItems.LIME_BUNDLE.get(), InvExpUtil.location("bundle/has_contents"),
                    (stack, level, entity, id) -> BundleItem.getContentWeight(stack) > 0 ? 1.0F : 0.0F);
            ItemProperties.register(InvExpItems.PINK_BUNDLE.get(), InvExpUtil.location("bundle/has_contents"),
                    (stack, level, entity, id) -> BundleItem.getContentWeight(stack) > 0 ? 1.0F : 0.0F);
            ItemProperties.register(InvExpItems.GRAY_BUNDLE.get(), InvExpUtil.location("bundle/has_contents"),
                    (stack, level, entity, id) -> BundleItem.getContentWeight(stack) > 0 ? 1.0F : 0.0F);
            ItemProperties.register(InvExpItems.LIGHT_GRAY_BUNDLE.get(), InvExpUtil.location("bundle/has_contents"),
                    (stack, level, entity, id) -> BundleItem.getContentWeight(stack) > 0 ? 1.0F : 0.0F);
            ItemProperties.register(InvExpItems.PURPLE_BUNDLE.get(), InvExpUtil.location("bundle/has_contents"),
                    (stack, level, entity, id) -> BundleItem.getContentWeight(stack) > 0 ? 1.0F : 0.0F);
            ItemProperties.register(InvExpItems.CYAN_BUNDLE.get(), InvExpUtil.location("bundle/has_contents"),
                    (stack, level, entity, id) -> BundleItem.getContentWeight(stack) > 0 ? 1.0F : 0.0F);
            ItemProperties.register(InvExpItems.BLUE_BUNDLE.get(), InvExpUtil.location("bundle/has_contents"),
                    (stack, level, entity, id) -> BundleItem.getContentWeight(stack) > 0 ? 1.0F : 0.0F);
            ItemProperties.register(InvExpItems.BROWN_BUNDLE.get(), InvExpUtil.location("bundle/has_contents"),
                    (stack, level, entity, id) -> BundleItem.getContentWeight(stack) > 0 ? 1.0F : 0.0F);
            ItemProperties.register(InvExpItems.GREEN_BUNDLE.get(), InvExpUtil.location("bundle/has_contents"),
                    (stack, level, entity, id) -> BundleItem.getContentWeight(stack) > 0 ? 1.0F : 0.0F);
            ItemProperties.register(InvExpItems.RED_BUNDLE.get(), InvExpUtil.location("bundle/has_contents"),
                    (stack, level, entity, id) -> BundleItem.getContentWeight(stack) > 0 ? 1.0F : 0.0F);
            ItemProperties.register(InvExpItems.BLACK_BUNDLE.get(), InvExpUtil.location("bundle/has_contents"),
                    (stack, level, entity, id) -> BundleItem.getContentWeight(stack) > 0 ? 1.0F : 0.0F);
        });
    }

    @SubscribeEvent
    public static void setupSackOpenTextures(ModelEvent.RegisterAdditional event) {
        for (ModelResourceLocation resource : OpenItemTextures.setupLocations()) {
            event.register(resource);
        }
    }
}

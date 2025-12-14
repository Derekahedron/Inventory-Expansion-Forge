package derekahedron.invexp.client.util;

import com.mojang.blaze3d.platform.Lighting;
import derekahedron.invexp.InventoryExpansion;
import derekahedron.invexp.util.OpenItemTexturesRegistry;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import org.joml.Matrix4f;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public record OpenItemTextures(ModelResourceLocation backTexture, ModelResourceLocation frontTexture) {
    private static final HashMap<Item, OpenItemTextures> OPEN_TEXTURES = new HashMap<>();

    @Nullable
    public static OpenItemTextures getTextures(Item item) {
        return OPEN_TEXTURES.getOrDefault(item, null);
    }

    public static List<ModelResourceLocation> setupLocations() {
        OPEN_TEXTURES.clear();
        List<Item> items = OpenItemTexturesRegistry.getItems();
        List<ModelResourceLocation> locations = new ArrayList<>(items.size() * 2);
        for (Item item : items) {
            ForgeRegistries.ITEMS.getDelegate(item).ifPresent(itemReference -> {
                String namespace = itemReference.key().location().getNamespace();
                String path = itemReference.key().location().getPath();
                OpenItemTextures textures = new OpenItemTextures(
                        new ModelResourceLocation(namespace, path + "_open_back", "inventory"),
                        new ModelResourceLocation(namespace, path + "_open_front", "inventory")
                );
                OPEN_TEXTURES.put(item, textures);
                locations.add(textures.backTexture);
                locations.add(textures.frontTexture);
            });
        }
        return locations;
    }

    public static void renderOpenItem(
            GuiGraphics guiGraphics,
            ItemStack stack,
            int x,
            int y,
            @Nullable Level level,
            @Nullable LivingEntity entity,
            int seed) {
        ItemRenderer renderer = guiGraphics.minecraft.getItemRenderer();
        OpenItemTextures textures = OpenItemTextures.getTextures(stack.getItem());
        if (textures == null) return;
        BakedModel backModel = resolveModelOverride(
                renderer.getItemModelShaper().getModelManager().getModel(textures.backTexture()),
                stack, level, entity, seed
        );
        BakedModel frontModel = resolveModelOverride(
                renderer.getItemModelShaper().getModelManager().getModel(textures.frontTexture()),
                stack, level, entity, seed
        );

        guiGraphics.pose().pushPose();
        try {
            guiGraphics.pose().translate(x + 8, y + 8, 150);
            guiGraphics.pose().mulPoseMatrix((new Matrix4f()).scaling(1.0F, -1.0F, 1.0F));
            guiGraphics.pose().scale(16.0F, 16.0F, 16.0F);

            Lighting.setupForFlatItems();

            guiGraphics.pose().pushPose();
            try {
                guiGraphics.pose().translate(0, 0, -1.0F);
                renderer.render(stack,
                        ItemDisplayContext.GUI,
                        false,
                        guiGraphics.pose(),
                        guiGraphics.bufferSource(),
                        0xF000F0,
                        OverlayTexture.NO_OVERLAY,
                        backModel
                );
            }
            catch (Throwable throwable) {
                InventoryExpansion.LOGGER.error("Error rendering open back model");
            }
            guiGraphics.pose().popPose();

            guiGraphics.pose().pushPose();
            try {
                guiGraphics.pose().translate(0, 0, 1.0F);
                renderer.render(stack,
                        ItemDisplayContext.GUI,
                        false,
                        guiGraphics.pose(),
                        guiGraphics.bufferSource(),
                        0xF000F0,
                        OverlayTexture.NO_OVERLAY,
                        frontModel
                );
            }
            catch (Throwable throwable) {
                InventoryExpansion.LOGGER.error("Error rendering open front model");
            }
            guiGraphics.pose().popPose();

            guiGraphics.flush();
            Lighting.setupFor3DItems();
        }
        catch (Throwable throwable) {
            InventoryExpansion.LOGGER.error("Error rendering open model");
        }
        guiGraphics.pose().popPose();
    }

    public static BakedModel resolveModelOverride(
            BakedModel model,
            ItemStack stack,
            @Nullable Level level,
            @Nullable LivingEntity entity,
            int seed) {
        ClientLevel clientLevel = level instanceof ClientLevel ? (ClientLevel) level : null;
        BakedModel bakedmodel = model.getOverrides().resolve(model, stack, clientLevel, entity, seed);
        return bakedmodel == null ? model : bakedmodel;
    }
}

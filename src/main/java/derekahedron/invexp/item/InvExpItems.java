package derekahedron.invexp.item;

import derekahedron.invexp.InventoryExpansion;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class InvExpItems {

    public static final DeferredRegister<Item> ITEMS;

    public static final RegistryObject<Item> SACK;
    public static final RegistryObject<Item> QUIVER;
    public static final RegistryObject<Item> WHITE_BUNDLE;
    public static final RegistryObject<Item> ORANGE_BUNDLE;
    public static final RegistryObject<Item> MAGENTA_BUNDLE;
    public static final RegistryObject<Item> LIGHT_BLUE_BUNDLE;
    public static final RegistryObject<Item> YELLOW_BUNDLE;
    public static final RegistryObject<Item> LIME_BUNDLE;
    public static final RegistryObject<Item> PINK_BUNDLE;
    public static final RegistryObject<Item> GRAY_BUNDLE;
    public static final RegistryObject<Item> LIGHT_GRAY_BUNDLE;
    public static final RegistryObject<Item> PURPLE_BUNDLE;
    public static final RegistryObject<Item> CYAN_BUNDLE;
    public static final RegistryObject<Item> BLUE_BUNDLE;
    public static final RegistryObject<Item> BROWN_BUNDLE;
    public static final RegistryObject<Item> GREEN_BUNDLE;
    public static final RegistryObject<Item> RED_BUNDLE;
    public static final RegistryObject<Item> BLACK_BUNDLE;

    static {
        ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, InventoryExpansion.MOD_ID);

        SACK = ITEMS.register("sack", () -> new DyeableSackItem(new Item.Properties().stacksTo(1)));
        QUIVER = ITEMS.register("quiver", () -> new QuiverItem(new Item.Properties().stacksTo(1)));
        WHITE_BUNDLE = ITEMS.register("white_bundle", () -> new BetterBundleItem(new Item.Properties().stacksTo(1)));
        ORANGE_BUNDLE = ITEMS.register("orange_bundle", () -> new BetterBundleItem(new Item.Properties().stacksTo(1)));
        MAGENTA_BUNDLE = ITEMS.register("magenta_bundle", () -> new BetterBundleItem(new Item.Properties().stacksTo(1)));
        LIGHT_BLUE_BUNDLE = ITEMS.register("light_blue_bundle", () -> new BetterBundleItem(new Item.Properties().stacksTo(1)));
        YELLOW_BUNDLE = ITEMS.register("yellow_bundle", () -> new BetterBundleItem(new Item.Properties().stacksTo(1)));
        LIME_BUNDLE = ITEMS.register("lime_bundle", () -> new BetterBundleItem(new Item.Properties().stacksTo(1)));
        PINK_BUNDLE = ITEMS.register("pink_bundle", () -> new BetterBundleItem(new Item.Properties().stacksTo(1)));
        GRAY_BUNDLE = ITEMS.register("gray_bundle", () -> new BetterBundleItem(new Item.Properties().stacksTo(1)));
        LIGHT_GRAY_BUNDLE = ITEMS.register("light_gray_bundle", () -> new BetterBundleItem(new Item.Properties().stacksTo(1)));
        PURPLE_BUNDLE = ITEMS.register("purple_bundle", () -> new BetterBundleItem(new Item.Properties().stacksTo(1)));
        CYAN_BUNDLE = ITEMS.register("cyan_bundle", () -> new BetterBundleItem(new Item.Properties().stacksTo(1)));
        BLUE_BUNDLE = ITEMS.register("blue_bundle", () -> new BetterBundleItem(new Item.Properties().stacksTo(1)));
        BROWN_BUNDLE = ITEMS.register("brown_bundle", () -> new BetterBundleItem(new Item.Properties().stacksTo(1)));
        GREEN_BUNDLE = ITEMS.register("green_bundle", () -> new BetterBundleItem(new Item.Properties().stacksTo(1)));
        RED_BUNDLE = ITEMS.register("red_bundle", () -> new BetterBundleItem(new Item.Properties().stacksTo(1)));
        BLACK_BUNDLE = ITEMS.register("black_bundle", () -> new BetterBundleItem(new Item.Properties().stacksTo(1)));
    }
}

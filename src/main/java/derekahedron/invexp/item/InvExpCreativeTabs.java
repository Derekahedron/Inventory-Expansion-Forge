package derekahedron.invexp.item;

import net.minecraft.world.item.*;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;

public class InvExpCreativeTabs {

    public static void initialize(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            addAfter(event, Items.CROSSBOW, InvExpItems.QUIVER.get());
        }
        else if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            if (!event.getEntries().contains(new ItemStack(Items.BUNDLE))) {
                addAfter(event, Items.LEAD, Items.BUNDLE);
            }
            addAfter(event, Items.BUNDLE, InvExpItems.WHITE_BUNDLE.get());
            addAfter(event, InvExpItems.WHITE_BUNDLE.get(), InvExpItems.LIGHT_GRAY_BUNDLE.get());
            addAfter(event, InvExpItems.LIGHT_GRAY_BUNDLE.get(), InvExpItems.GRAY_BUNDLE.get());
            addAfter(event, InvExpItems.GRAY_BUNDLE.get(), InvExpItems.BLACK_BUNDLE.get());
            addAfter(event, InvExpItems.BLACK_BUNDLE.get(), InvExpItems.BROWN_BUNDLE.get());
            addAfter(event, InvExpItems.BROWN_BUNDLE.get(), InvExpItems.RED_BUNDLE.get());
            addAfter(event, InvExpItems.RED_BUNDLE.get(), InvExpItems.ORANGE_BUNDLE.get());
            addAfter(event, InvExpItems.ORANGE_BUNDLE.get(), InvExpItems.YELLOW_BUNDLE.get());
            addAfter(event, InvExpItems.YELLOW_BUNDLE.get(), InvExpItems.LIME_BUNDLE.get());
            addAfter(event, InvExpItems.LIME_BUNDLE.get(), InvExpItems.GREEN_BUNDLE.get());
            addAfter(event, InvExpItems.GREEN_BUNDLE.get(), InvExpItems.CYAN_BUNDLE.get());
            addAfter(event, InvExpItems.CYAN_BUNDLE.get(), InvExpItems.LIGHT_BLUE_BUNDLE.get());
            addAfter(event, InvExpItems.LIGHT_BLUE_BUNDLE.get(), InvExpItems.BLUE_BUNDLE.get());
            addAfter(event, InvExpItems.BLUE_BUNDLE.get(), InvExpItems.PURPLE_BUNDLE.get());
            addAfter(event, InvExpItems.PURPLE_BUNDLE.get(), InvExpItems.MAGENTA_BUNDLE.get());
            addAfter(event, InvExpItems.MAGENTA_BUNDLE.get(), InvExpItems.PINK_BUNDLE.get());
            addAfter(event, InvExpItems.PINK_BUNDLE.get(), InvExpItems.SACK.get());
        }
    }

    private static void addAfter(BuildCreativeModeTabContentsEvent event, Item after, Item key) {
        event.getEntries().putAfter(new ItemStack(after), new ItemStack(key), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
    }
}

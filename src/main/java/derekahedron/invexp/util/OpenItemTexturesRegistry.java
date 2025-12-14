package derekahedron.invexp.util;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.Unmodifiable;

import java.util.HashSet;
import java.util.List;

public class OpenItemTexturesRegistry {
    private static final HashSet<Item> OPEN_ITEMS = new HashSet<>();

    public static void addItem(Item item) {
        OPEN_ITEMS.add(item);
    }

    @Unmodifiable
    public static List<Item> getItems() {
        return OPEN_ITEMS.stream().toList();
    }

    static {
        OPEN_ITEMS.add(Items.BUNDLE);
    }
}

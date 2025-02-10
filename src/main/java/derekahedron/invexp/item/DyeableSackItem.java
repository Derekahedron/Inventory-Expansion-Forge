package derekahedron.invexp.item;

import net.minecraft.world.item.DyeableLeatherItem;

public class DyeableSackItem extends SackItem implements DyeableLeatherItem {

    /**
     * Create a new Sack Item from the given settings
     *
     * @param properties Settings to create the sack with
     */
    public DyeableSackItem(Properties properties) {
        super(properties);
    }
}

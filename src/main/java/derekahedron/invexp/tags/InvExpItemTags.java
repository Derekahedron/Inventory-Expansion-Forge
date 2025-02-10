package derekahedron.invexp.tags;

import derekahedron.invexp.util.InvExpUtil;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class InvExpItemTags {

    public static final TagKey<Item> DYEABLE_BUNDLES;
    
    static {
        DYEABLE_BUNDLES = TagKey.create(Registries.ITEM, InvExpUtil.location("dyeable_bundles"));
    }
}

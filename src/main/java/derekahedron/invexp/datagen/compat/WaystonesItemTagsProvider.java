package derekahedron.invexp.datagen.compat;

import derekahedron.invexp.datagen.InvExpItemTagProvider;
import derekahedron.invexp.item.InvExpItemTags;
import derekahedron.invexp.util.CompatUtil;

public class WaystonesItemTagsProvider extends CompatibilityItemTagsProvider {

    public WaystonesItemTagsProvider(InvExpItemTagProvider provider) {
        super(provider);
    }

    @Override
    public void makeTags() {
        tag(InvExpItemTags.SackType.WAYSTONE)
                .addOptionalTag(CompatUtil.waystones("waystones"))
                .addOptional(CompatUtil.waystones("deepslate_waystone"))
                .addOptional(CompatUtil.waystones("blackstone_waystone"))
                .addOptional(CompatUtil.waystones("end_stone_waystone"));
        tag(InvExpItemTags.SackType.SHARESTONE)
                .addOptionalTag(CompatUtil.waystones("sharestones"));
        tag(InvExpItemTags.SackType.PORTSTONE)
                .addOptional(CompatUtil.waystones("portstone"));
        tag(InvExpItemTags.SackType.WARP_PLATE)
                .addOptional(CompatUtil.waystones("warp_plate"));
    }
}

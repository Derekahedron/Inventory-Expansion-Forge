package derekahedron.invexp.datagen.compat;

import derekahedron.invexp.datagen.InvExpItemTagProvider;
import derekahedron.invexp.item.InvExpItemTags;
import derekahedron.invexp.util.CompatUtil;

public class YungsCaveBiomesItemTagsProvider extends CompatibilityItemTagsProvider {

    public YungsCaveBiomesItemTagsProvider(InvExpItemTagProvider provider) {
        super(provider);
    }

    @Override
    public void makeTags() {
        tag(InvExpItemTags.SackType.SANDSTONE)
                .addOptional(CompatUtil.yungsCaveBiomes("ancient_sandstone"))
                .addOptional(CompatUtil.yungsCaveBiomes("ancient_sandstone_stairs"))
                .addOptional(CompatUtil.yungsCaveBiomes("ancient_sandstone_slab"))
                .addOptional(CompatUtil.yungsCaveBiomes("ancient_sandstone_wall"))
                .addOptional(CompatUtil.yungsCaveBiomes("brittle_ancient_sandstone"))
                .addOptional(CompatUtil.yungsCaveBiomes("layered_ancient_sandstone"))
                .addOptional(CompatUtil.yungsCaveBiomes("cut_ancient_sandstone"))
                .addOptional(CompatUtil.yungsCaveBiomes("cut_ancient_sandstone_slab"))
                .addOptional(CompatUtil.yungsCaveBiomes("chiseled_ancient_sandstone"))
                .addOptional(CompatUtil.yungsCaveBiomes("smooth_ancient_sandstone"))
                .addOptional(CompatUtil.yungsCaveBiomes("smooth_ancient_sandstone_stairs"))
                .addOptional(CompatUtil.yungsCaveBiomes("smooth_ancient_sandstone_slab"))
                .addOptional(CompatUtil.yungsCaveBiomes("brittle_sandstone"))
                .addOptional(CompatUtil.yungsCaveBiomes("layered_sandstone"))
                .addOptional(CompatUtil.yungsCaveBiomes("brittle_red_sandstone"))
                .addOptional(CompatUtil.yungsCaveBiomes("layered_red_sandstone"));
        tag(InvExpItemTags.SackType.ICE)
                .addOptional(CompatUtil.yungsCaveBiomes("icicle"))
                .addOptional(CompatUtil.yungsCaveBiomes("frost_lily"))
                .addOptional(CompatUtil.yungsCaveBiomes("rare_ice"))
                .addOptional(CompatUtil.yungsCaveBiomes("ice_sheet"));
        tag(InvExpItemTags.SackType.PLANT)
                .addOptional(CompatUtil.yungsCaveBiomes("prickly_peach_cactus"))
                .addOptional(CompatUtil.yungsCaveBiomes("prickly_vines"));
        tag(InvExpItemTags.SackType.FOOD)
                .addOptional(CompatUtil.yungsCaveBiomes("prickly_peach"));
        tag(InvExpItemTags.SackType.SMITHING_TEMPLATE)
                .addOptional(CompatUtil.yungsCaveBiomes("ancient_armor_trim_smithing_template"));

        tag(InvExpItemTags.SackWeight.HALF)
                .addOptional(CompatUtil.yungsCaveBiomes("ice_sheet"));
        tag(InvExpItemTags.SackWeight.DOUBLE);
    }
}

package derekahedron.invexp.datagen.compat;

import derekahedron.invexp.datagen.InvExpItemTagProvider;
import derekahedron.invexp.item.InvExpItemTags;
import derekahedron.invexp.util.CompatUtil;

public class NethersDelightItemTagsProvider extends CompatibilityItemTagsProvider {

    public NethersDelightItemTagsProvider(InvExpItemTagProvider provider) {
        super(provider);
    }

    @Override
    public void makeTags() {
        tag(InvExpItemTags.SackType.FUNGUS)
                .addOptional(CompatUtil.nethersDelight("crimson_fungus_colony"))
                .addOptional(CompatUtil.nethersDelight("warped_fungus_colony"));
        tag(InvExpItemTags.SackType.SOIL)
                .addOptional(CompatUtil.nethersDelight("soul_compost"))
                .addOptional(CompatUtil.nethersDelight("rich_soul_soil"));
        tag(InvExpItemTags.SackType.PLANT)
                .addOptional(CompatUtil.nethersDelight("propelplant_cane"));
        tag(InvExpItemTags.SackType.FOOD)
                .addOptional(CompatUtil.nethersDelight("propelpearl"))
                .addOptional(CompatUtil.nethersDelight("hoglin_loin"))
                .addOptional(CompatUtil.nethersDelight("hoglin_sirloin"))
                .addOptional(CompatUtil.nethersDelight("grilled_strider"))
                .addOptional(CompatUtil.nethersDelight("ground_strider"))
                .addOptional(CompatUtil.nethersDelight("warped_moldy_meat"))
                .addOptional(CompatUtil.nethersDelight("strider_moss_stew"))
                .addOptional(CompatUtil.nethersDelight("plate_of_stuffed_hoglin_snout"))
                .addOptional(CompatUtil.nethersDelight("plate_of_stuffed_hoglin_ham"))
                .addOptional(CompatUtil.nethersDelight("plate_of_stuffed_hoglin_roast"))
                .addOptional(CompatUtil.nethersDelight("nether_skewer"))
                .addOptional(CompatUtil.nethersDelight("magma_gelatin"));
        tag(InvExpItemTags.SackType.CREATURE)
                .addOptional(CompatUtil.nethersDelight("hoglin_loin"))
                .addOptional(CompatUtil.nethersDelight("hoglin_ear"))
                .addOptional(CompatUtil.nethersDelight("strider_slice"))
                .addOptional(CompatUtil.nethersDelight("hoglin_hide"));
        tag(InvExpItemTags.SackType.TROPHY)
                .addOptional(CompatUtil.nethersDelight("hoglin_trophy"));
        tag(InvExpItemTags.SackType.TORCH)
                .addOptional(CompatUtil.nethersDelight("propelplant_torch"));

        tag(InvExpItemTags.SackWeight.HALF)
                .addOptional(CompatUtil.nethersDelight("magma_gelatin"));
    }
}

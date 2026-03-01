package derekahedron.invexp.datagen.compat;

import derekahedron.invexp.datagen.InvExpItemTagProvider;
import derekahedron.invexp.item.InvExpItemTags;
import derekahedron.invexp.util.CompatUtil;

public class EndersDelightItemTagsProvider extends CompatibilityItemTagsProvider {

    public EndersDelightItemTagsProvider(InvExpItemTagProvider provider) {
        super(provider);
    }

    @Override
    public void makeTags() {
        // Sack Types
        tag(InvExpItemTags.SackType.PLANT)
                .addOptional(CompatUtil.endersDelight("ethereal_saffron"))
                .addOptional(CompatUtil.endersDelight("voidpepper"))
                .addOptional(CompatUtil.endersDelight("chorusflame"));
        tag(InvExpItemTags.SackType.FUNGUS)
                .addOptional(CompatUtil.endersDelight("amberveil"));
        tag(InvExpItemTags.SackType.CHORUS_FRUIT)
                .addOptional(CompatUtil.endersDelight("chorus_crate"));
        tag(InvExpItemTags.SackType.CREATURE)
                .addOptional(CompatUtil.endersDelight("mite_crust"))
                .addOptional(CompatUtil.endersDelight("enderman_sight"))
                .addOptional(CompatUtil.endersDelight("shulker_mollusk"))
                .addOptional(CompatUtil.endersDelight("ender_shard"))
                .addOptional(CompatUtil.endersDelight("sight_fragment"));
        tag(InvExpItemTags.SackType.FOOD)
                .addOptional(CompatUtil.endersDelight("shulker_filet"))
                .addOptional(CompatUtil.endersDelight("chorus_juice"))
                .addOptional(CompatUtil.endersDelight("uncanny_cookies"))
                .addOptional(CompatUtil.endersDelight("strange_eclair"))
                .addOptional(CompatUtil.endersDelight("chorus_pie_slice"))
                .addOptional(CompatUtil.endersDelight("crawling_sandwich"))
                .addOptional(CompatUtil.endersDelight("crispy_skewer"))
                .addOptional(CompatUtil.endersDelight("twisted_cereal"))
                .addOptional(CompatUtil.endersDelight("endermite_stew"))
                .addOptional(CompatUtil.endersDelight("pearl_pasta"))
                .addOptional(CompatUtil.endersDelight("ender_paella"))
                .addOptional(CompatUtil.endersDelight("chorus_stew"))
                .addOptional(CompatUtil.endersDelight("amberveil_stew"))
                .addOptional(CompatUtil.endersDelight("amberveiled_curry"))
                .addOptional(CompatUtil.endersDelight("steak_fries"))
                .addOptional(CompatUtil.endersDelight("veil_of_flames_risotto"))
                .addOptional(CompatUtil.endersDelight("chicken_curry"))
                .addOptional(CompatUtil.endersDelight("stuffed_shulker_bowl"));

    }
}

package derekahedron.invexp.datagen.compat;

import derekahedron.invexp.datagen.InvExpItemTagProvider;
import derekahedron.invexp.item.InvExpItemTags;
import derekahedron.invexp.util.CompatUtil;

public class AlexsMobsItemTagsProvider extends CompatibilityItemTagsProvider {

    public AlexsMobsItemTagsProvider(InvExpItemTagProvider provider) {
        super(provider);
    }

    @Override
    public void makeTags() {
        tag(InvExpItemTags.SackType.WOOL)
                .addOptional(CompatUtil.alexsMobs("bison_fur_block"))
                .addOptional(CompatUtil.alexsMobs("bison_carpet"));
        tag(InvExpItemTags.SackType.GLASS)
                .addOptional(CompatUtil.alexsMobs("rainbow_glass"));
        tag(InvExpItemTags.SackType.BANNER)
                .addOptional(CompatUtil.alexsMobs("banner_pattern_bear"))
                .addOptional(CompatUtil.alexsMobs("banner_pattern_australia_0"))
                .addOptional(CompatUtil.alexsMobs("banner_pattern_australia_1"))
                .addOptional(CompatUtil.alexsMobs("banner_pattern_new_mexico"))
                .addOptional(CompatUtil.alexsMobs("banner_pattern_brazil"));
        tag(InvExpItemTags.SackType.PLANT)
                .addOptional(CompatUtil.alexsMobs("acacia_blossom"));
        tag(InvExpItemTags.SackType.EGG)
                .addOptional(CompatUtil.alexsMobs("emu_egg"))
                .addOptional(CompatUtil.alexsMobs("triops_eggs"));
        tag(InvExpItemTags.SackType.SCULK)
                .addOptional(CompatUtil.alexsMobs("sculk_boomer"));
        tag(InvExpItemTags.SackType.FIREWORK_ROCKET)
                .addOptional(CompatUtil.alexsMobs("enderiophage_rocket"));
        tag(InvExpItemTags.SackType.FOOD)
                .addOptional(CompatUtil.alexsMobs("banana"))
                .addOptional(CompatUtil.alexsMobs("cooked_lobster_tail"))
                .addOptional(CompatUtil.alexsMobs("cooked_moose_ribs"))
                .addOptional(CompatUtil.alexsMobs("shrimp_fried_rice"))
                .addOptional(CompatUtil.alexsMobs("boiled_emu_egg"))
                .addOptional(CompatUtil.alexsMobs("cooked_kangaroo_meat"))
                .addOptional(CompatUtil.alexsMobs("kangaroo_burger"))
                .addOptional(CompatUtil.alexsMobs("cooked_catfish"))
                .addOptional(CompatUtil.alexsMobs("mosquito_repellent_stew"));
        tag(InvExpItemTags.SackType.RAW_FISH)
                .addOptional(CompatUtil.alexsMobs("blobfish"))
                .addOptional(CompatUtil.alexsMobs("cosmic_cod"))
                .addOptional(CompatUtil.alexsMobs("flying_fish"));
        tag(InvExpItemTags.SackType.BOTTLE)
                .addOptional(CompatUtil.alexsMobs("lava_bottle"))
                .addOptional(CompatUtil.alexsMobs("komodo_spit_bottle"))
                .addOptional(CompatUtil.alexsMobs("poison_bottle"))
                .addOptional(CompatUtil.alexsMobs("fish_oil"))
                .addOptional(CompatUtil.alexsMobs("warped_mixure"))
                .addOptional(CompatUtil.alexsMobs("stink_bottle"));
        tag(InvExpItemTags.SackType.CREATURE)
                .addOptional(CompatUtil.alexsMobs("bear_fur"))
                .addOptional(CompatUtil.alexsMobs("roadrunner_feather"))
                .addOptional(CompatUtil.alexsMobs("bone_serpent_tooth"))
                .addOptional(CompatUtil.alexsMobs("gazelle_horn"))
                .addOptional(CompatUtil.alexsMobs("crocodile_scute"))
                .addOptional(CompatUtil.alexsMobs("maggot"))
                .addOptional(CompatUtil.alexsMobs("blood_sac"))
                .addOptional(CompatUtil.alexsMobs("mosquito_proboscis"))
                .addOptional(CompatUtil.alexsMobs("rattlesnake_rattle"))
                .addOptional(CompatUtil.alexsMobs("shark_tooth"))
                .addOptional(CompatUtil.alexsMobs("lobster_tail"))
                .addOptional(CompatUtil.alexsMobs("komodo_spit"))
                .addOptional(CompatUtil.alexsMobs("centipede_leg"))
                .addOptional(CompatUtil.alexsMobs("mosquito_larva"))
                .addOptional(CompatUtil.alexsMobs("moose_antler"))
                .addOptional(CompatUtil.alexsMobs("moose_ribs"))
                .addOptional(CompatUtil.alexsMobs("mimicream"))
                .addOptional(CompatUtil.alexsMobs("raccoon_tail"))
                .addOptional(CompatUtil.alexsMobs("cockroach_wing_fragment"))
                .addOptional(CompatUtil.alexsMobs("cockroach_wing"))
                .addOptional(CompatUtil.alexsMobs("soul_heart"))
                .addOptional(CompatUtil.alexsMobs("spiked_scute"))
                .addOptional(CompatUtil.alexsMobs("guster_eye"))
                .addOptional(CompatUtil.alexsMobs("warped_muscle"))
                .addOptional(CompatUtil.alexsMobs("hemolymph_sac"))
                .addOptional(CompatUtil.alexsMobs("straddlite"))
                .addOptional(CompatUtil.alexsMobs("emu_feather"))
                .addOptional(CompatUtil.alexsMobs("dropbear_claw"))
                .addOptional(CompatUtil.alexsMobs("kangaroo_meat"))
                .addOptional(CompatUtil.alexsMobs("kangaroo_hide"))
                .addOptional(CompatUtil.alexsMobs("ambergris"))
                .addOptional(CompatUtil.alexsMobs("cachalot_whale_tooth"))
                .addOptional(CompatUtil.alexsMobs("gongylidia"))
                .addOptional(CompatUtil.alexsMobs("leafcutter_ant_pupa"))
                .addOptional(CompatUtil.alexsMobs("tarantula_hawk_wing_fragment"))
                .addOptional(CompatUtil.alexsMobs("tarantula_hawk_wing"))
                .addOptional(CompatUtil.alexsMobs("void_worm_mandible"))
                .addOptional(CompatUtil.alexsMobs("void_worm_eye"))
                .addOptional(CompatUtil.alexsMobs("serrated_shark_tooth"))
                .addOptional(CompatUtil.alexsMobs("froststalker_horn"))
                .addOptional(CompatUtil.alexsMobs("shed_snake_skin"))
                .addOptional(CompatUtil.alexsMobs("rocky_shell"))
                .addOptional(CompatUtil.alexsMobs("rainbow_jelly"))
                .addOptional(CompatUtil.alexsMobs("mungal_spores"))
                .addOptional(CompatUtil.alexsMobs("bison_fur"))
                .addOptional(CompatUtil.alexsMobs("lost_tentacle"))
                .addOptional(CompatUtil.alexsMobs("raw_catfish"))
                .addOptional(CompatUtil.alexsMobs("fish_bones"))
                .addOptional(CompatUtil.alexsMobs("farseer_arm"))
                .addOptional(CompatUtil.alexsMobs("skreecher_soul"))
                .addOptional(CompatUtil.alexsMobs("elastic_tendon"))
                .addOptional(CompatUtil.alexsMobs("banana_slug_slime"))
                .addOptional(CompatUtil.alexsMobs("straddlite_block"))
                .addOptional(CompatUtil.alexsMobs("capsid"))
                .addOptional(CompatUtil.alexsMobs("void_worm_beak"))
                .addOptional(CompatUtil.alexsMobs("banana_slug_slime_block"));

        tag(InvExpItemTags.SackWeight.FOURTH)
                .addOptional(CompatUtil.alexsMobs("lava_bottle"));
        tag(InvExpItemTags.SackWeight.HALF)
                .addOptional(CompatUtil.alexsMobs("banner_pattern_bear"))
                .addOptional(CompatUtil.alexsMobs("banner_pattern_australia_0"))
                .addOptional(CompatUtil.alexsMobs("banner_pattern_australia_1"))
                .addOptional(CompatUtil.alexsMobs("banner_pattern_new_mexico"))
                .addOptional(CompatUtil.alexsMobs("banner_pattern_brazil"))
                .addOptional(CompatUtil.alexsMobs("mosquito_repellent_stew"));
    }
}

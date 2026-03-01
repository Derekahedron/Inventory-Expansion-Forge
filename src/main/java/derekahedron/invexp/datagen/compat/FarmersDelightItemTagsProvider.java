package derekahedron.invexp.datagen.compat;

import derekahedron.invexp.datagen.InvExpItemTagProvider;
import derekahedron.invexp.item.InvExpItemTags;
import derekahedron.invexp.util.CompatUtil;

public class FarmersDelightItemTagsProvider extends CompatibilityItemTagsProvider {

    public FarmersDelightItemTagsProvider(InvExpItemTagProvider provider) {
        super(provider);
    }

    @Override
    public void makeTags() {
        tag(InvExpItemTags.SackType.FUNGUS)
                .addOptional(CompatUtil.farmersDelight("brown_mushroom_colony"))
                .addOptional(CompatUtil.farmersDelight("red_mushroom_colony"));
        tag(InvExpItemTags.SackType.PLANT)
                .addOptional(CompatUtil.farmersDelight("sandy_shrub"));
        tag(InvExpItemTags.SackType.FOOD)
                .addOptional(CompatUtil.farmersDelight("fried_egg"))
                .addOptional(CompatUtil.farmersDelight("hot_cocoa"))
                .addOptional(CompatUtil.farmersDelight("apple_cider"))
                .addOptional(CompatUtil.farmersDelight("melon_juice"))
                .addOptional(CompatUtil.farmersDelight("tomato_sauce"))
                .addOptional(CompatUtil.farmersDelight("wheat_dough"))
                .addOptional(CompatUtil.farmersDelight("raw_pasta"))
                .addOptional(CompatUtil.farmersDelight("pumpkin_slice"))
                .addOptional(CompatUtil.farmersDelight("cabbage_leaf"))
                .addOptional(CompatUtil.farmersDelight("minced_beef"))
                .addOptional(CompatUtil.farmersDelight("beef_patty"))
                .addOptional(CompatUtil.farmersDelight("chicken_cuts"))
                .addOptional(CompatUtil.farmersDelight("cooked_chicken_cuts"))
                .addOptional(CompatUtil.farmersDelight("bacon"))
                .addOptional(CompatUtil.farmersDelight("cooked_bacon"))
                .addOptional(CompatUtil.farmersDelight("cod_slice"))
                .addOptional(CompatUtil.farmersDelight("cooked_cod_slice"))
                .addOptional(CompatUtil.farmersDelight("salmon_slice"))
                .addOptional(CompatUtil.farmersDelight("cooked_salmon_slice"))
                .addOptional(CompatUtil.farmersDelight("mutton_chops"))
                .addOptional(CompatUtil.farmersDelight("cooked_mutton_chops"))
                .addOptional(CompatUtil.farmersDelight("ham"))
                .addOptional(CompatUtil.farmersDelight("smoked_ham"))
                .addOptional(CompatUtil.farmersDelight("pie_crust"))
                .addOptional(CompatUtil.farmersDelight("apple_pie"))
                .addOptional(CompatUtil.farmersDelight("sweet_berry_cheesecake"))
                .addOptional(CompatUtil.farmersDelight("chocolate_pie"))
                .addOptional(CompatUtil.farmersDelight("cake_slice"))
                .addOptional(CompatUtil.farmersDelight("apple_pie_slice"))
                .addOptional(CompatUtil.farmersDelight("sweet_berry_cheesecake_slice"))
                .addOptional(CompatUtil.farmersDelight("chocolate_pie_slice"))
                .addOptional(CompatUtil.farmersDelight("sweet_berry_cookie"))
                .addOptional(CompatUtil.farmersDelight("honey_cookie"))
                .addOptional(CompatUtil.farmersDelight("melon_popsicle"))
                .addOptional(CompatUtil.farmersDelight("glow_berry_custard"))
                .addOptional(CompatUtil.farmersDelight("fruit_salad"))
                .addOptional(CompatUtil.farmersDelight("mixed_salad"))
                .addOptional(CompatUtil.farmersDelight("nether_salad"))
                .addOptional(CompatUtil.farmersDelight("barbecue_stick"))
                .addOptional(CompatUtil.farmersDelight("egg_sandwich"))
                .addOptional(CompatUtil.farmersDelight("chicken_sandwich"))
                .addOptional(CompatUtil.farmersDelight("hamburger"))
                .addOptional(CompatUtil.farmersDelight("bacon_sandwich"))
                .addOptional(CompatUtil.farmersDelight("mutton_wrap"))
                .addOptional(CompatUtil.farmersDelight("dumplings"))
                .addOptional(CompatUtil.farmersDelight("stuffed_potato"))
                .addOptional(CompatUtil.farmersDelight("cabbage_rolls"))
                .addOptional(CompatUtil.farmersDelight("salmon_roll"))
                .addOptional(CompatUtil.farmersDelight("cod_roll"))
                .addOptional(CompatUtil.farmersDelight("kelp_roll"))
                .addOptional(CompatUtil.farmersDelight("kelp_roll_slice"))
                .addOptional(CompatUtil.farmersDelight("cooked_rice"))
                .addOptional(CompatUtil.farmersDelight("bone_broth"))
                .addOptional(CompatUtil.farmersDelight("beef_stew"))
                .addOptional(CompatUtil.farmersDelight("chicken_soup"))
                .addOptional(CompatUtil.farmersDelight("vegetable_soup"))
                .addOptional(CompatUtil.farmersDelight("fish_stew"))
                .addOptional(CompatUtil.farmersDelight("fried_rice"))
                .addOptional(CompatUtil.farmersDelight("pumpkin_soup"))
                .addOptional(CompatUtil.farmersDelight("baked_cod_stew"))
                .addOptional(CompatUtil.farmersDelight("noodle_soup"))
                .addOptional(CompatUtil.farmersDelight("bacon_and_eggs"))
                .addOptional(CompatUtil.farmersDelight("pasta_with_meatballs"))
                .addOptional(CompatUtil.farmersDelight("pasta_with_mutton_chop"))
                .addOptional(CompatUtil.farmersDelight("mushroom_rice"))
                .addOptional(CompatUtil.farmersDelight("roasted_mutton_chops"))
                .addOptional(CompatUtil.farmersDelight("vegetable_noodles"))
                .addOptional(CompatUtil.farmersDelight("steak_and_potatoes"))
                .addOptional(CompatUtil.farmersDelight("ratatouille"))
                .addOptional(CompatUtil.farmersDelight("squid_ink_pasta"))
                .addOptional(CompatUtil.farmersDelight("grilled_salmon"))
                .addOptional(CompatUtil.farmersDelight("roast_chicken"))
                .addOptional(CompatUtil.farmersDelight("stuffed_pumpkin"))
                .addOptional(CompatUtil.farmersDelight("honey_glazed_ham"))
                .addOptional(CompatUtil.farmersDelight("shepherds_pie"));
        tag(InvExpItemTags.SackType.BOTTLE)
                .addOptional(CompatUtil.farmersDelight("milk_bottle"));

        tag(InvExpItemTags.SackType.TOMATO)
                .addOptional(CompatUtil.farmersDelight("rotten_tomato"));
        tag(InvExpItemTags.SackType.STRAW)
                .addOptional(CompatUtil.farmersDelight("straw_bale"))
                .addOptional(CompatUtil.farmersDelight("straw"));
        tag(InvExpItemTags.SackType.ROPE)
                .addOptional(CompatUtil.farmersDelight("rope"));

        // Farmer's Delight Sack Types
        tag(InvExpItemTags.SackType.RICE_PANICLE)
                .addOptional(CompatUtil.farmersDelight("rice_bale"))
                .addOptional(CompatUtil.farmersDelight("rice_panicle"));
        tag(InvExpItemTags.SackType.ANIMAL_FOOD)
                .addOptional(CompatUtil.farmersDelight("dog_food"))
                .addOptional(CompatUtil.farmersDelight("horse_feed"));
    }
}

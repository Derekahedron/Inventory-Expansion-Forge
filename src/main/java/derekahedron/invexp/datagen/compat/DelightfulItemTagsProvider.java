package derekahedron.invexp.datagen.compat;

import derekahedron.invexp.datagen.InvExpItemTagProvider;
import derekahedron.invexp.item.InvExpItemTags;
import derekahedron.invexp.util.CompatUtil;

public class DelightfulItemTagsProvider extends CompatibilityItemTagsProvider {

    public DelightfulItemTagsProvider(InvExpItemTagProvider provider) {
        super(provider);
    }

    @Override
    public void makeTags() {
        tag(InvExpItemTags.SackType.FOOD)
                .addOptional(CompatUtil.delightful("matcha_ice_cream"))
                .addOptional(CompatUtil.delightful("salmonberry_ice_cream"))
                .addOptional(CompatUtil.delightful("source_berry_ice_cream"))
                .addOptional(CompatUtil.delightful("matcha_milkshake"))
                .addOptional(CompatUtil.delightful("salmonberry_milkshake"))
                .addOptional(CompatUtil.delightful("source_berry_milkshake"))
                .addOptional(CompatUtil.delightful("salmonberry_pie_slice"))
                .addOptional(CompatUtil.delightful("pumpkin_pie_slice"))
                .addOptional(CompatUtil.delightful("source_berry_pie_slice"))
                .addOptional(CompatUtil.delightful("source_berry_cookie"))
                .addOptional(CompatUtil.delightful("glow_jam_cookie"))
                .addOptional(CompatUtil.delightful("baklava_slice"))
                .addOptional(CompatUtil.delightful("acorn"))
                .addOptional(CompatUtil.delightful("roasted_acorn"))
                .addOptional(CompatUtil.delightful("nut_dough"))
                .addOptional(CompatUtil.delightful("chopped_clover"))
                .addOptional(CompatUtil.delightful("cactus_flesh"))
                .addOptional(CompatUtil.delightful("cactus_steak"))
                .addOptional(CompatUtil.delightful("cactus_chili"))
                .addOptional(CompatUtil.delightful("cactus_soup"))
                .addOptional(CompatUtil.delightful("venison_stew"))
                .addOptional(CompatUtil.delightful("sinigang"))
                .addOptional(CompatUtil.delightful("field_salad"))
                .addOptional(CompatUtil.delightful("matcha_latte"))
                .addOptional(CompatUtil.delightful("berry_matcha_latte"))
                .addOptional(CompatUtil.delightful("ender_nectar"))
                .addOptional(CompatUtil.delightful("lavender_tea"))
                .addOptional(CompatUtil.delightful("jam_jar"))
                .addOptional(CompatUtil.delightful("glow_jam_jar"))
                .addOptional(CompatUtil.delightful("nut_butter_bottle"))
                .addOptional(CompatUtil.delightful("nut_butter_and_jam_sandwich"))
                .addOptional(CompatUtil.delightful("cheeseburger"))
                .addOptional(CompatUtil.delightful("deluxe_cheeseburger"))
                .addOptional(CompatUtil.delightful("rock_candy"))
                .addOptional(CompatUtil.delightful("marshmallow_stick"))
                .addOptional(CompatUtil.delightful("cooked_marshmallow_stick"))
                .addOptional(CompatUtil.delightful("smore"))
                .addOptional(CompatUtil.delightful("venison_chops"))
                .addOptional(CompatUtil.delightful("cooked_venison_chops"))
                .addOptional(CompatUtil.delightful("cooked_goat"))
                .addOptional(CompatUtil.delightful("cantaloupe_bread"))
                .addOptional(CompatUtil.delightful("wrapped_cantaloupe"))
                .addOptional(CompatUtil.delightful("cantaloupe_popsicle"))
                .addOptional(CompatUtil.delightful("stuffed_cantaloupe"))
                .addOptional(CompatUtil.delightful("source_berry_gummy"));
        tag(InvExpItemTags.SackType.PLANT)
                .addOptional(CompatUtil.delightful("green_tea_leaf"));
        tag(InvExpItemTags.SackType.CREATURE)
                .addOptional(CompatUtil.delightful("animal_fat"))
                .addOptional(CompatUtil.delightful("raw_goat"));
        tag(InvExpItemTags.SackType.CANTALOUPE)
                .addOptional(CompatUtil.delightful("cantaloupe"))
                .addOptional(CompatUtil.delightful("cantaloupe_slice"));
        tag(InvExpItemTags.SackType.MELON)
                .addOptional(CompatUtil.delightful("mini_melon"));
        tag(InvExpItemTags.SackType.BOTTLE)
                .addOptional(CompatUtil.delightful("animal_oil_bottle"));

        tag(InvExpItemTags.SackWeight.HALF)
                .addOptional(CompatUtil.delightful("matcha_ice_cream"))
                .addOptional(CompatUtil.delightful("salmonberry_ice_cream"))
                .addOptional(CompatUtil.delightful("source_berry_ice_cream"))
                .addOptional(CompatUtil.delightful("cantaloupe_slice"));
    }
}

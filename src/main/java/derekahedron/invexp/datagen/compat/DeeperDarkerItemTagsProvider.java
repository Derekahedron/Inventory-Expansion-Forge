package derekahedron.invexp.datagen.compat;

import derekahedron.invexp.datagen.InvExpItemTagProvider;
import derekahedron.invexp.item.InvExpItemTags;
import derekahedron.invexp.util.CompatUtil;

public class DeeperDarkerItemTagsProvider extends CompatibilityItemTagsProvider {

    public DeeperDarkerItemTagsProvider(InvExpItemTagProvider provider) {
        super(provider);
    }

    @Override
    public void makeTags() {
        tag(InvExpItemTags.SackType.WOOD)
                .addOptionalTag(CompatUtil.deeperDarker("echo_logs"))
                .addOptionalTag(CompatUtil.deeperDarker("bloom_stems"));
        tag(InvExpItemTags.SackType.STONE)
                .addOptional(CompatUtil.deeperDarker("sculk_stone_stairs"))
                .addOptional(CompatUtil.deeperDarker("sculk_stone_slab"))
                .addOptional(CompatUtil.deeperDarker("sculk_stone_wall"))
                .addOptional(CompatUtil.deeperDarker("cobbled_sculk_stone"))
                .addOptional(CompatUtil.deeperDarker("cobbled_sculk_stone_stairs"))
                .addOptional(CompatUtil.deeperDarker("cobbled_sculk_stone_slab"))
                .addOptional(CompatUtil.deeperDarker("cobbled_sculk_stone_wall"))
                .addOptional(CompatUtil.deeperDarker("polished_sculk_stone"))
                .addOptional(CompatUtil.deeperDarker("polished_sculk_stone_stairs"))
                .addOptional(CompatUtil.deeperDarker("polished_sculk_stone_slab"))
                .addOptional(CompatUtil.deeperDarker("polished_sculk_stone_wall"))
                .addOptional(CompatUtil.deeperDarker("sculk_stone_bricks"))
                .addOptional(CompatUtil.deeperDarker("sculk_stone_brick_stairs"))
                .addOptional(CompatUtil.deeperDarker("sculk_stone_brick_slab"))
                .addOptional(CompatUtil.deeperDarker("sculk_stone_brick_wall"))
                .addOptional(CompatUtil.deeperDarker("sculk_stone_tiles"))
                .addOptional(CompatUtil.deeperDarker("sculk_stone_tile_stairs"))
                .addOptional(CompatUtil.deeperDarker("sculk_stone_tile_slab"))
                .addOptional(CompatUtil.deeperDarker("sculk_stone_tile_wall"))
                .addOptional(CompatUtil.deeperDarker("smooth_sculk_stone"))
                .addOptional(CompatUtil.deeperDarker("smooth_sculk_stone_stairs"))
                .addOptional(CompatUtil.deeperDarker("smooth_sculk_stone_slab"))
                .addOptional(CompatUtil.deeperDarker("smooth_sculk_stone_wall"))
                .addOptional(CompatUtil.deeperDarker("cut_sculk_stone"))
                .addOptional(CompatUtil.deeperDarker("cut_sculk_stone_stairs"))
                .addOptional(CompatUtil.deeperDarker("cut_sculk_stone_slab"))
                .addOptional(CompatUtil.deeperDarker("cut_sculk_stone_wall"))
                .addOptional(CompatUtil.deeperDarker("chiseled_sculk_stone"))
                .addOptional(CompatUtil.deeperDarker("blooming_sculk_stone"))
                .addOptional(CompatUtil.deeperDarker("gloomslate_stairs"))
                .addOptional(CompatUtil.deeperDarker("gloomslate_slab"))
                .addOptional(CompatUtil.deeperDarker("gloomslate_wall"))
                .addOptional(CompatUtil.deeperDarker("cobbled_gloomslate"))
                .addOptional(CompatUtil.deeperDarker("cobbled_gloomslate_stairs"))
                .addOptional(CompatUtil.deeperDarker("cobbled_gloomslate_slab"))
                .addOptional(CompatUtil.deeperDarker("cobbled_gloomslate_wall"))
                .addOptional(CompatUtil.deeperDarker("polished_gloomslate"))
                .addOptional(CompatUtil.deeperDarker("polished_gloomslate_stairs"))
                .addOptional(CompatUtil.deeperDarker("polished_gloomslate_slab"))
                .addOptional(CompatUtil.deeperDarker("polished_gloomslate_wall"))
                .addOptional(CompatUtil.deeperDarker("gloomslate_bricks"))
                .addOptional(CompatUtil.deeperDarker("gloomslate_brick_stairs"))
                .addOptional(CompatUtil.deeperDarker("gloomslate_brick_slab"))
                .addOptional(CompatUtil.deeperDarker("gloomslate_brick_wall"))
                .addOptional(CompatUtil.deeperDarker("gloomslate_tiles"))
                .addOptional(CompatUtil.deeperDarker("gloomslate_tile_stairs"))
                .addOptional(CompatUtil.deeperDarker("gloomslate_tile_slab"))
                .addOptional(CompatUtil.deeperDarker("gloomslate_tile_wall"))
                .addOptional(CompatUtil.deeperDarker("smooth_gloomslate"))
                .addOptional(CompatUtil.deeperDarker("smooth_gloomslate_stairs"))
                .addOptional(CompatUtil.deeperDarker("smooth_gloomslate_slab"))
                .addOptional(CompatUtil.deeperDarker("smooth_gloomslate_wall"))
                .addOptional(CompatUtil.deeperDarker("cut_gloomslate"))
                .addOptional(CompatUtil.deeperDarker("cut_gloomslate_stairs"))
                .addOptional(CompatUtil.deeperDarker("cut_gloomslate_slab"))
                .addOptional(CompatUtil.deeperDarker("cut_gloomslate_wall"))
                .addOptional(CompatUtil.deeperDarker("chiseled_gloomslate"));
        tag(InvExpItemTags.SackType.GLASS)
                .addOptional(CompatUtil.deeperDarker("soundproof_glass"));
        tag(InvExpItemTags.SackType.SOIL)
                .addOptional(CompatUtil.deeperDarker("sculk_grime"))
                .addOptional(CompatUtil.deeperDarker("echo_soil"))
                .addOptional(CompatUtil.deeperDarker("grime_ball"));
        tag(InvExpItemTags.SackType.PLANT)
                .addOptional(CompatUtil.deeperDarker("blooming_moss_block"))
                .addOptional(CompatUtil.deeperDarker("glowing_grass"))
                .addOptional(CompatUtil.deeperDarker("gloomy_grass"))
                .addOptional(CompatUtil.deeperDarker("gloomy_cactus"))
                .addOptional(CompatUtil.deeperDarker("glowing_roots"))
                .addOptional(CompatUtil.deeperDarker("ice_lily"));
        tag(InvExpItemTags.SackType.SCULK)
                .addOptional(CompatUtil.alexsMobs("sculk_boomer"))
                .addOptional(CompatUtil.deeperDarker("gloomy_sculk"))
                .addOptional(CompatUtil.deeperDarker("gloomy_geyser"))
                .addOptional(CompatUtil.deeperDarker("sculk_gleam"))
                .addOptional(CompatUtil.deeperDarker("sculk_tendrils"))
                .addOptional(CompatUtil.deeperDarker("sculk_vines"))
                .addOptional(CompatUtil.deeperDarker("infested_sculk"))
                .addOptional(CompatUtil.deeperDarker("sculk_jaw"));
        tag(InvExpItemTags.SackType.POT)
                .addOptional(CompatUtil.deeperDarker("ancient_vase"));
        tag(InvExpItemTags.SackType.CREATURE)
                .addOptional(CompatUtil.deeperDarker("sculk_bone"))
                .addOptional(CompatUtil.deeperDarker("soul_dust"))
                .addOptional(CompatUtil.deeperDarker("soul_crystal"))
                .addOptional(CompatUtil.deeperDarker("resonarium"))
                .addOptional(CompatUtil.deeperDarker("heart_of_the_deep"))
                .addOptional(CompatUtil.deeperDarker("warden_carapace"));
        tag(InvExpItemTags.SackType.SMITHING_TEMPLATE)
                .addOptional(CompatUtil.deeperDarker("warden_upgrade_smithing_template"));

        // Deeper and Darker Sack Types
        tag(InvExpItemTags.SackType.SCULK_GRIME_BRICKS)
                .addOptional(CompatUtil.deeperDarker("sculk_grime_bricks"))
                .addOptional(CompatUtil.deeperDarker("sculk_grime_brick_stairs"))
                .addOptional(CompatUtil.deeperDarker("sculk_grime_brick_slab"))
                .addOptional(CompatUtil.deeperDarker("sculk_grime_brick_wall"))
                .addOptional(CompatUtil.deeperDarker("grime_brick"));
        tag(InvExpItemTags.SackType.BLOOM_BERRIES)
                .addOptional(CompatUtil.deeperDarker("bloom_berries"));

        tag(InvExpItemTags.SackWeight.FOURTH)
                .addOptional(CompatUtil.deeperDarker("grime_ball"))
                .addOptional(CompatUtil.deeperDarker("grime_brick"));
        tag(InvExpItemTags.SackWeight.HALF)
                .addOptional(CompatUtil.deeperDarker("bloom_berries"));
    }
}

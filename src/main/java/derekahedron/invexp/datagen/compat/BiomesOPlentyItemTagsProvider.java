package derekahedron.invexp.datagen.compat;

import derekahedron.invexp.datagen.InvExpItemTagProvider;
import derekahedron.invexp.item.InvExpItemTags;
import derekahedron.invexp.util.CompatUtil;

public class BiomesOPlentyItemTagsProvider extends CompatibilityItemTagsProvider {

    public BiomesOPlentyItemTagsProvider(InvExpItemTagProvider provider) {
        super(provider);
    }

    @Override
    public void makeTags() {
        tag(InvExpItemTags.SackType.WOOD)
                .addOptional(CompatUtil.biomesOPlenty("dead_branch"));
        tag(InvExpItemTags.SackType.STONE)
                .addOptional(CompatUtil.biomesOPlenty("thermal_calcite"))
                .addOptional(CompatUtil.biomesOPlenty("thermal_calcite_vent"))
                .addOptional(CompatUtil.biomesOPlenty("brimstone"))
                .addOptional(CompatUtil.biomesOPlenty("brimstone_bricks"))
                .addOptional(CompatUtil.biomesOPlenty("brimstone_brick_slab"))
                .addOptional(CompatUtil.biomesOPlenty("brimstone_brick_stairs"))
                .addOptional(CompatUtil.biomesOPlenty("brimstone_brick_wall"))
                .addOptional(CompatUtil.biomesOPlenty("chiseled_brimstone_bricks"))
                .addOptional(CompatUtil.biomesOPlenty("brimstone_fumarole"))
                .addOptional(CompatUtil.biomesOPlenty("brimstone_cluster"))
                .addOptional(CompatUtil.biomesOPlenty("brimstone_bud"))
                .addOptional(CompatUtil.biomesOPlenty("blackstone_spines"))
                .addOptional(CompatUtil.biomesOPlenty("blackstone_bulb"))
                .addOptional(CompatUtil.biomesOPlenty("algal_end_stone"));
        tag(InvExpItemTags.SackType.SANDSTONE)
                .addOptional(CompatUtil.biomesOPlenty("cut_white_sandstone_slab"))
                .addOptional(CompatUtil.biomesOPlenty("smooth_white_sandstone_slab"))
                .addOptional(CompatUtil.biomesOPlenty("smooth_white_sandstone_stairs"))
                .addOptional(CompatUtil.biomesOPlenty("white_sandstone_slab"))
                .addOptional(CompatUtil.biomesOPlenty("white_sandstone_stairs"))
                .addOptional(CompatUtil.biomesOPlenty("white_sandstone_wall"))
                .addOptional(CompatUtil.biomesOPlenty("cut_orange_sandstone_slab"))
                .addOptional(CompatUtil.biomesOPlenty("smooth_orange_sandstone_slab"))
                .addOptional(CompatUtil.biomesOPlenty("smooth_orange_sandstone_stairs"))
                .addOptional(CompatUtil.biomesOPlenty("orange_sandstone_slab"))
                .addOptional(CompatUtil.biomesOPlenty("orange_sandstone_stairs"))
                .addOptional(CompatUtil.biomesOPlenty("orange_sandstone_wall"))
                .addOptional(CompatUtil.biomesOPlenty("cut_black_sandstone_slab"))
                .addOptional(CompatUtil.biomesOPlenty("smooth_black_sandstone_slab"))
                .addOptional(CompatUtil.biomesOPlenty("smooth_black_sandstone_stairs"))
                .addOptional(CompatUtil.biomesOPlenty("black_sandstone_slab"))
                .addOptional(CompatUtil.biomesOPlenty("black_sandstone_stairs"))
                .addOptional(CompatUtil.biomesOPlenty("black_sandstone_wall"));
        tag(InvExpItemTags.SackType.CRYSTAL_BLOCK)
                .addOptional(CompatUtil.biomesOPlenty("rose_quartz_block"));
        tag(InvExpItemTags.SackType.SOIL)
                .addOptional(CompatUtil.biomesOPlenty("dried_salt"))
                .addOptional(CompatUtil.biomesOPlenty("origin_grass_block"))
                .addOptional(CompatUtil.biomesOPlenty("ancient_sand"));
        tag(InvExpItemTags.SackType.ORE)
                .addOptional(CompatUtil.biomesOPlenty("rose_quartz_cluster"))
                .addOptional(CompatUtil.biomesOPlenty("large_rose_quartz_bud"))
                .addOptional(CompatUtil.biomesOPlenty("medium_rose_quartz_bud"))
                .addOptional(CompatUtil.biomesOPlenty("small_rose_quartz_bud"));
        tag(InvExpItemTags.SackType.FUNGUS)
                .addOptional(CompatUtil.biomesOPlenty("toadstool_block"))
                .addOptional(CompatUtil.biomesOPlenty("glowshroom_block"));
        tag(InvExpItemTags.SackType.PLANT)
                .addOptional(CompatUtil.biomesOPlenty("glowing_moss_block"))
                .addOptional(CompatUtil.biomesOPlenty("glowing_moss_carpet"))
                .addOptional(CompatUtil.biomesOPlenty("red_maple_leaf_pile"))
                .addOptional(CompatUtil.biomesOPlenty("orange_maple_leaf_pile"))
                .addOptional(CompatUtil.biomesOPlenty("yellow_maple_leaf_pile"))
                .addOptional(CompatUtil.biomesOPlenty("willow_vine"))
                .addOptional(CompatUtil.biomesOPlenty("spanish_moss"))
                .addOptional(CompatUtil.biomesOPlenty("sprout"))
                .addOptional(CompatUtil.biomesOPlenty("bush"))
                .addOptional(CompatUtil.biomesOPlenty("high_grass"))
                .addOptional(CompatUtil.biomesOPlenty("clover"))
                .addOptional(CompatUtil.biomesOPlenty("huge_clover_petal"))
                .addOptional(CompatUtil.biomesOPlenty("huge_lily_pad"))
                .addOptional(CompatUtil.biomesOPlenty("waterlily"))
                .addOptional(CompatUtil.biomesOPlenty("dune_grass"))
                .addOptional(CompatUtil.biomesOPlenty("desert_grass"))
                .addOptional(CompatUtil.biomesOPlenty("dead_grass"))
                .addOptional(CompatUtil.biomesOPlenty("tundra_shrub"))
                .addOptional(CompatUtil.biomesOPlenty("enderphyte"))
                .addOptional(CompatUtil.biomesOPlenty("lumaloop"))
                .addOptional(CompatUtil.biomesOPlenty("barley"))
                .addOptional(CompatUtil.biomesOPlenty("sea_oats"))
                .addOptional(CompatUtil.biomesOPlenty("cattail"))
                .addOptional(CompatUtil.biomesOPlenty("reed"))
                .addOptional(CompatUtil.biomesOPlenty("watergrass"))
                .addOptional(CompatUtil.biomesOPlenty("tiny_cactus"))
                .addOptional(CompatUtil.biomesOPlenty("bramble"))
                .addOptional(CompatUtil.biomesOPlenty("bramble_leaves"));
        tag(InvExpItemTags.SackType.EGG)
                .addOptional(CompatUtil.biomesOPlenty("spider_egg"));
        tag(InvExpItemTags.SackType.SEA_CREATURE)
                .addOptional(CompatUtil.biomesOPlenty("barnacles"));
        tag(InvExpItemTags.SackType.COBWEB)
                .addOptional(CompatUtil.biomesOPlenty("glowworm_silk"))
                .addOptional(CompatUtil.biomesOPlenty("hanging_cobweb"))
                .addOptional(CompatUtil.biomesOPlenty("webbing"))
                .addOptional(CompatUtil.biomesOPlenty("stringy_cobweb"));

        // Biomes O' Plenty Sack Types
        tag(InvExpItemTags.SackType.BODY_PART)
                .addOptional(CompatUtil.biomesOPlenty("flesh"))
                .addOptional(CompatUtil.biomesOPlenty("porous_flesh"))
                .addOptional(CompatUtil.biomesOPlenty("flesh_tendons"))
                .addOptional(CompatUtil.biomesOPlenty("eyebulb"))
                .addOptional(CompatUtil.biomesOPlenty("hair"))
                .addOptional(CompatUtil.biomesOPlenty("pus_bubble"));
        tag(InvExpItemTags.SackType.WISPJELLY)
                .addOptional(CompatUtil.biomesOPlenty("wispjelly"));
        tag(InvExpItemTags.SackType.NULL)
                .addOptional(CompatUtil.biomesOPlenty("unmapped_end_stone"))
                .addOptional(CompatUtil.biomesOPlenty("null_end_stone"))
                .addOptional(CompatUtil.biomesOPlenty("null_block"))
                .addOptional(CompatUtil.biomesOPlenty("null_leaves"))
                .addOptional(CompatUtil.biomesOPlenty("null_plant"))
                .addOptional(CompatUtil.biomesOPlenty("anomaly"));

        tag(InvExpItemTags.SackWeight.FOURTH)
                .addOptional(CompatUtil.biomesOPlenty("barnacles"))
                .addOptional(CompatUtil.biomesOPlenty("wildflowers"))
                .addOptional(CompatUtil.biomesOPlenty("white_petals"))
                .addOptional(CompatUtil.biomesOPlenty("clover"))
                .addOptional(CompatUtil.biomesOPlenty("barnacles"));
        tag(InvExpItemTags.SackWeight.HALF)
                .addOptional(CompatUtil.biomesOPlenty("barnacles"))
                .addOptional(CompatUtil.biomesOPlenty("glowing_moss_carpet"))
                .addOptional(CompatUtil.biomesOPlenty("webbing"));
    }
}

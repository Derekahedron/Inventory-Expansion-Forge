package derekahedron.invexp.datagen.compat;

import derekahedron.invexp.datagen.InvExpItemTagProvider;
import derekahedron.invexp.item.InvExpItemTags;
import derekahedron.invexp.util.CompatUtil;

public class GalosphereItemTagsProvider extends CompatibilityItemTagsProvider {

    public GalosphereItemTagsProvider(InvExpItemTagProvider provider) {
        super(provider);
    }

    @Override
    public void makeTags() {
        tag(InvExpItemTags.SackType.METAL_BLOCK)
                .addOptional(CompatUtil.galosphere("chandelier"))
                .addOptional(CompatUtil.galosphere("gilded_beads"))
                .addOptional(CompatUtil.galosphere("silver_tiles"))
                .addOptional(CompatUtil.galosphere("silver_tiles_stairs"))
                .addOptional(CompatUtil.galosphere("silver_tiles_slab"))
                .addOptional(CompatUtil.galosphere("silver_panel"))
                .addOptional(CompatUtil.galosphere("silver_panel_stairs"))
                .addOptional(CompatUtil.galosphere("silver_panel_slab"))
                .addOptional(CompatUtil.galosphere("silver_lattice"));
        tag(InvExpItemTags.SackType.CRYSTAL_BLOCK)
                .addOptional(CompatUtil.galosphere("allurite_block"))
                .addOptional(CompatUtil.galosphere("lumiere_block"))
                .addOptional(CompatUtil.galosphere("charged_lumiere_block"))
                .addOptional(CompatUtil.galosphere("amethyst_stairs"))
                .addOptional(CompatUtil.galosphere("amethyst_slab"))
                .addOptional(CompatUtil.galosphere("allurite_stairs"))
                .addOptional(CompatUtil.galosphere("allurite_slab"))
                .addOptional(CompatUtil.galosphere("lumiere_stairs"))
                .addOptional(CompatUtil.galosphere("lumiere_slab"))
                .addOptional(CompatUtil.galosphere("smooth_amethyst"))
                .addOptional(CompatUtil.galosphere("smooth_amethyst_stairs"))
                .addOptional(CompatUtil.galosphere("smooth_amethyst_slab"))
                .addOptional(CompatUtil.galosphere("smooth_allurite"))
                .addOptional(CompatUtil.galosphere("smooth_allurite_stairs"))
                .addOptional(CompatUtil.galosphere("smooth_allurite_slab"))
                .addOptional(CompatUtil.galosphere("smooth_lumiere"))
                .addOptional(CompatUtil.galosphere("smooth_lumiere_stairs"))
                .addOptional(CompatUtil.galosphere("smooth_lumiere_slab"))
                .addOptional(CompatUtil.galosphere("amethyst_bricks"))
                .addOptional(CompatUtil.galosphere("amethyst_brick_stairs"))
                .addOptional(CompatUtil.galosphere("amethyst_brick_slab"))
                .addOptional(CompatUtil.galosphere("allurite_bricks"))
                .addOptional(CompatUtil.galosphere("allurite_brick_stairs"))
                .addOptional(CompatUtil.galosphere("allurite_brick_slab"))
                .addOptional(CompatUtil.galosphere("lumiere_bricks"))
                .addOptional(CompatUtil.galosphere("lumiere_brick_stairs"))
                .addOptional(CompatUtil.galosphere("lumiere_brick_slab"))
                .addOptional(CompatUtil.galosphere("chiseled_amethyst"))
                .addOptional(CompatUtil.galosphere("chiseled_allurite"))
                .addOptional(CompatUtil.galosphere("chiseled_lumiere"))
                .addOptional(CompatUtil.galosphere("amethyst_lamp"))
                .addOptional(CompatUtil.galosphere("allurite_lamp"))
                .addOptional(CompatUtil.galosphere("lumiere_lamp"))
                .addOptional(CompatUtil.galosphere("pink_salt"))
                .addOptional(CompatUtil.galosphere("rose_pink_salt"))
                .addOptional(CompatUtil.galosphere("pastel_pink_salt"))
                .addOptional(CompatUtil.galosphere("pink_salt_stairs"))
                .addOptional(CompatUtil.galosphere("rose_pink_salt_stairs"))
                .addOptional(CompatUtil.galosphere("pastel_pink_salt_stairs"))
                .addOptional(CompatUtil.galosphere("pink_salt_slab"))
                .addOptional(CompatUtil.galosphere("rose_pink_salt_slab"))
                .addOptional(CompatUtil.galosphere("pastel_pink_salt_slab"))
                .addOptional(CompatUtil.galosphere("pink_salt_wall"))
                .addOptional(CompatUtil.galosphere("rose_pink_salt_wall"))
                .addOptional(CompatUtil.galosphere("pastel_pink_salt_wall"))
                .addOptional(CompatUtil.galosphere("polished_pink_salt"))
                .addOptional(CompatUtil.galosphere("polished_rose_pink_salt"))
                .addOptional(CompatUtil.galosphere("polished_pastel_pink_salt"))
                .addOptional(CompatUtil.galosphere("polished_pink_salt_stairs"))
                .addOptional(CompatUtil.galosphere("polished_rose_pink_salt_stairs"))
                .addOptional(CompatUtil.galosphere("polished_pastel_pink_salt_stairs"))
                .addOptional(CompatUtil.galosphere("polished_pink_salt_slab"))
                .addOptional(CompatUtil.galosphere("polished_rose_pink_salt_slab"))
                .addOptional(CompatUtil.galosphere("polished_pastel_pink_salt_slab"))
                .addOptional(CompatUtil.galosphere("polished_pink_salt_wall"))
                .addOptional(CompatUtil.galosphere("polished_rose_pink_salt_wall"))
                .addOptional(CompatUtil.galosphere("polished_pastel_pink_salt_wall"))
                .addOptional(CompatUtil.galosphere("pink_salt_bricks"))
                .addOptional(CompatUtil.galosphere("rose_pink_salt_bricks"))
                .addOptional(CompatUtil.galosphere("pastel_pink_salt_bricks"))
                .addOptional(CompatUtil.galosphere("pink_salt_brick_stairs"))
                .addOptional(CompatUtil.galosphere("rose_pink_salt_brick_stairs"))
                .addOptional(CompatUtil.galosphere("pastel_pink_salt_brick_stairs"))
                .addOptional(CompatUtil.galosphere("pink_salt_brick_slab"))
                .addOptional(CompatUtil.galosphere("rose_pink_salt_brick_slab"))
                .addOptional(CompatUtil.galosphere("pastel_pink_salt_brick_slab"))
                .addOptional(CompatUtil.galosphere("pink_salt_brick_wall"))
                .addOptional(CompatUtil.galosphere("rose_pink_salt_brick_wall"))
                .addOptional(CompatUtil.galosphere("pastel_pink_salt_brick_wall"))
                .addOptional(CompatUtil.galosphere("chiseled_pink_salt"))
                .addOptional(CompatUtil.galosphere("chiseled_rose_pink_salt"))
                .addOptional(CompatUtil.galosphere("chiseled_pastel_pink_salt"))
                .addOptional(CompatUtil.galosphere("pink_salt_chamber"))
                .addOptional(CompatUtil.galosphere("pink_salt_lamp"))
                .addOptional(CompatUtil.galosphere("pink_salt_straw"));
        tag(InvExpItemTags.SackType.ORE)
                .addOptional(CompatUtil.galosphere("allurite_shard"))
                .addOptional(CompatUtil.galosphere("lumiere_shard"))
                .addOptional(CompatUtil.galosphere("pink_salt_shard"))
                .addOptional(CompatUtil.galosphere("allurite_cluster"))
                .addOptional(CompatUtil.galosphere("lumiere_cluster"))
                .addOptional(CompatUtil.galosphere("glinted_allurite_cluster"))
                .addOptional(CompatUtil.galosphere("glinted_lumiere_cluster"))
                .addOptional(CompatUtil.galosphere("glinted_amethyst_cluster"))
                .addOptional(CompatUtil.galosphere("pink_salt_cluster"));
        tag(InvExpItemTags.SackType.PLANT)
                .addOptional(CompatUtil.galosphere("lichen_moss"))
                .addOptional(CompatUtil.galosphere("lichen_roots"))
                .addOptional(CompatUtil.galosphere("bowl_lichen"))
                .addOptional(CompatUtil.galosphere("lichen_shelf"));
        tag(InvExpItemTags.SackType.FOOD)
                .addOptional(CompatUtil.galosphere("salted_jerky"));
        tag(InvExpItemTags.SackType.BOTTLE)
                .addOptional(CompatUtil.galosphere("bottle_of_spectre"));
        tag(InvExpItemTags.SackType.CREATURE)
                .addOptional(CompatUtil.galosphere("preserved_flesh"));
        tag(InvExpItemTags.SackType.SMITHING_TEMPLATE)
                .addOptional(CompatUtil.galosphere("preserved_template"));

        // Galosphere Sack Types
        tag(InvExpItemTags.SackType.BAROMETER)
                .addOptional(CompatUtil.galosphere("barometer"));
        tag(InvExpItemTags.SackType.SILVER_BOMB)
                .addOptional(CompatUtil.galosphere("silver_bomb"));
        tag(InvExpItemTags.SackType.GLOW_FLARE)
                .addOptional(CompatUtil.galosphere("glow_flare"));
        tag(InvExpItemTags.SackType.SPECTRE_FLARE)
                .addOptional(CompatUtil.galosphere("spectre_flare"));
        tag(InvExpItemTags.SackType.GLOW_INK_CLUMPS)
                .addOptional(CompatUtil.galosphere("glow_ink_clumps"));

        tag(InvExpItemTags.SackWeight.FOURTH)
                .addOptional(CompatUtil.galosphere("allurite_shard"))
                .addOptional(CompatUtil.galosphere("lumiere_shard"))
                .addOptional(CompatUtil.galosphere("pink_salt_shard"))
                .addOptional(CompatUtil.galosphere("pink_salt_straw"));
        tag(InvExpItemTags.SackWeight.HALF)
                .addOptional(CompatUtil.galosphere("silver_lattice"))
                .addOptional(CompatUtil.galosphere("glow_ink_clumps"));
    }
}

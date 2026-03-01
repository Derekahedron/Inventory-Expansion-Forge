package derekahedron.invexp.datagen.compat;

import derekahedron.invexp.datagen.InvExpItemTagProvider;
import derekahedron.invexp.item.InvExpItemTags;
import derekahedron.invexp.util.CompatUtil;

public class EnderIOItemTagsProvider  extends CompatibilityItemTagsProvider {

    public EnderIOItemTagsProvider(InvExpItemTagProvider provider) {
        super(provider);
    }

    @Override
    public void makeTags() {
        tag(InvExpItemTags.SackType.BROKEN_SPAWNER)
                .addOptional(CompatUtil.enderIO("broken_spawner"));
        tag(InvExpItemTags.SackType.ORE)
                .addOptionalTag(CompatUtil.forge("ingots/copper_alloy"))
                .addOptionalTag(CompatUtil.forge("ingots/energetic_alloy"))
                .addOptionalTag(CompatUtil.forge("ingots/vibrant_alloy"))
                .addOptionalTag(CompatUtil.forge("ingots/redstone_alloy"))
                .addOptionalTag(CompatUtil.forge("ingots/conductive_alloy"))
                .addOptionalTag(CompatUtil.forge("ingots/pulsating_alloy"))
                .addOptionalTag(CompatUtil.forge("ingots/dark_steel"))
                .addOptionalTag(CompatUtil.forge("ingots/soularium"))
                .addOptionalTag(CompatUtil.forge("ingots/end_steel"))
                .addOptionalTag(CompatUtil.forge("nuggets/copper_alloy"))
                .addOptionalTag(CompatUtil.forge("nuggets/energetic_alloy"))
                .addOptionalTag(CompatUtil.forge("nuggets/vibrant_alloy"))
                .addOptionalTag(CompatUtil.forge("nuggets/redstone_alloy"))
                .addOptionalTag(CompatUtil.forge("nuggets/conductive_alloy"))
                .addOptionalTag(CompatUtil.forge("nuggets/pulsating_alloy"))
                .addOptionalTag(CompatUtil.forge("nuggets/dark_steel"))
                .addOptionalTag(CompatUtil.forge("nuggets/soularium"))
                .addOptionalTag(CompatUtil.forge("nuggets/end_steel"));
        tag(InvExpItemTags.SackType.METAL_BLOCK)
                .addOptionalTag(CompatUtil.forge("storage_blocks/copper_alloy"))
                .addOptionalTag(CompatUtil.forge("storage_blocks/energetic_alloy"))
                .addOptionalTag(CompatUtil.forge("storage_blocks/vibrant_alloy"))
                .addOptionalTag(CompatUtil.forge("storage_blocks/redstone_alloy"))
                .addOptionalTag(CompatUtil.forge("storage_blocks/conductive_alloy"))
                .addOptionalTag(CompatUtil.forge("storage_blocks/pulsating_alloy"))
                .addOptionalTag(CompatUtil.forge("storage_blocks/dark_steel"))
                .addOptionalTag(CompatUtil.forge("storage_blocks/soularium"))
                .addOptionalTag(CompatUtil.forge("storage_blocks/end_steel"));

        tag(InvExpItemTags.SackWeight.FIFTH)
                .addOptionalTag(CompatUtil.forge("nuggets/copper_alloy"))
                .addOptionalTag(CompatUtil.forge("nuggets/energetic_alloy"))
                .addOptionalTag(CompatUtil.forge("nuggets/vibrant_alloy"))
                .addOptionalTag(CompatUtil.forge("nuggets/redstone_alloy"))
                .addOptionalTag(CompatUtil.forge("nuggets/conductive_alloy"))
                .addOptionalTag(CompatUtil.forge("nuggets/pulsating_alloy"))
                .addOptionalTag(CompatUtil.forge("nuggets/dark_steel"))
                .addOptionalTag(CompatUtil.forge("nuggets/soularium"))
                .addOptionalTag(CompatUtil.forge("nuggets/end_steel"));
    }
}

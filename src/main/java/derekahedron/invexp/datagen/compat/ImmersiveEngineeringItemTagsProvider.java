package derekahedron.invexp.datagen.compat;

import derekahedron.invexp.datagen.InvExpItemTagProvider;
import derekahedron.invexp.item.InvExpItemTags;
import derekahedron.invexp.util.CompatUtil;

public class ImmersiveEngineeringItemTagsProvider extends CompatibilityItemTagsProvider {

    public ImmersiveEngineeringItemTagsProvider(InvExpItemTagProvider provider) {
        super(provider);
    }

    @Override
    public void makeTags() {
        // Galosphere Sack Types
        tag(InvExpItemTags.SackType.INDUSTRIAL_HEMP_FIBER)
                .addOptional(CompatUtil.immersiveEngineering("hemp_fiber"));
        tag(InvExpItemTags.SackType.INDUSTRIAL_HEMP_SEEDS)
                .addOptional(CompatUtil.immersiveEngineering("seed"));

        tag(InvExpItemTags.SackWeight.FOURTH)
                .addOptional(CompatUtil.immersiveEngineering("seed"));
        tag(InvExpItemTags.SackWeight.HALF)
                .addOptional(CompatUtil.immersiveEngineering("hemp_fiber"));
    }
}

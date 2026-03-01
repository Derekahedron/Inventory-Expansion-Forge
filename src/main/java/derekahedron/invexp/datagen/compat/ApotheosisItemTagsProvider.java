package derekahedron.invexp.datagen.compat;

import derekahedron.invexp.datagen.InvExpItemTagProvider;
import derekahedron.invexp.item.InvExpItemTags;
import derekahedron.invexp.util.CompatUtil;

public class ApotheosisItemTagsProvider extends CompatibilityItemTagsProvider {

    public ApotheosisItemTagsProvider(InvExpItemTagProvider provider) {
        super(provider);
    }

    @Override
    public void makeTags() {
        tag(InvExpItemTags.SackType.APOTHEOSIS_GEM)
                .addOptional(CompatUtil.apotheosis("gem"));
    }
}

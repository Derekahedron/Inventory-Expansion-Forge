package derekahedron.invexp.datagen.compat;

import derekahedron.invexp.datagen.InvExpItemTagProvider;
import derekahedron.invexp.item.InvExpItemTags;
import derekahedron.invexp.util.CompatUtil;

public class MekanismItemTagsProvider extends CompatibilityItemTagsProvider {

    public MekanismItemTagsProvider(InvExpItemTagProvider provider) {
        super(provider);
    }

    @Override
    public void makeTags() {
        tag(InvExpItemTags.SackWeight.FOURTH)
                .addOptional(CompatUtil.mekanism("fluorite_gem"));
    }
}

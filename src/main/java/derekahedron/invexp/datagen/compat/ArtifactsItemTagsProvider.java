package derekahedron.invexp.datagen.compat;

import derekahedron.invexp.datagen.InvExpItemTagProvider;
import derekahedron.invexp.item.InvExpItemTags;
import derekahedron.invexp.util.CompatUtil;

public class ArtifactsItemTagsProvider extends CompatibilityItemTagsProvider {

    public ArtifactsItemTagsProvider(InvExpItemTagProvider provider) {
        super(provider);
    }

    @Override
    public void makeTags() {
        tag(InvExpItemTags.SackType.FOOD)
                .addOptional(CompatUtil.artifacts("everlasting_beef"))
                .addOptional(CompatUtil.artifacts("eternal_steak"));
    }
}

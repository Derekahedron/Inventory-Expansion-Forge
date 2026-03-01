package derekahedron.invexp.datagen.compat;

import derekahedron.invexp.datagen.InvExpItemTagProvider;
import derekahedron.invexp.item.InvExpItemTags;
import derekahedron.invexp.util.CompatUtil;

public class ElevatorItemTagsProvider extends CompatibilityItemTagsProvider {

    public ElevatorItemTagsProvider(InvExpItemTagProvider provider) {
        super(provider);
    }

    @Override
    public void makeTags() {
        tag(InvExpItemTags.SackType.ELEVATOR)
                .addOptionalTag(CompatUtil.elevatorId("elevators"));
    }
}

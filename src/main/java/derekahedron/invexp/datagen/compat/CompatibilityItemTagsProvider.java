package derekahedron.invexp.datagen.compat;

import derekahedron.invexp.datagen.InvExpItemTagProvider;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public abstract class CompatibilityItemTagsProvider {
    public final InvExpItemTagProvider provider;

    public CompatibilityItemTagsProvider(InvExpItemTagProvider provider) {
        this.provider = provider;
    }

    abstract void makeTags();

    public IntrinsicHolderTagsProvider.IntrinsicTagAppender<Item> tag(TagKey<Item> tagKey) {
        return provider.tag(tagKey);
    }
}

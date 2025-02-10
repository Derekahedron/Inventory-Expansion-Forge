package derekahedron.invexp.registry;

import derekahedron.invexp.sack.SackData;
import derekahedron.invexp.sack.SackType;
import derekahedron.invexp.sack.TaggedSackData;
import derekahedron.invexp.util.InvExpUtil;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraftforge.registries.DataPackRegistryEvent;
import org.jetbrains.annotations.NotNull;

public class InvExpRegistryKeys {
    public static final ResourceKey<Registry<SackType>> SACK_TYPE;
    public static final ResourceKey<Registry<SackData>> SACK_INSERTABLE;
    public static final ResourceKey<Registry<TaggedSackData>> TAGGED_SACK_INSERTABLE;

    static {
        SACK_TYPE = ResourceKey.createRegistryKey(InvExpUtil.location("sack_type"));
        SACK_INSERTABLE = ResourceKey.createRegistryKey(InvExpUtil.location("sack_data"));
        TAGGED_SACK_INSERTABLE = ResourceKey.createRegistryKey(InvExpUtil.location("tagged_sack_data"));
    }

    public static void initialize(DataPackRegistryEvent.@NotNull NewRegistry event) {
        event.dataPackRegistry(InvExpRegistryKeys.SACK_TYPE, SackType.CODEC, SackType.CODEC);
        event.dataPackRegistry(InvExpRegistryKeys.SACK_INSERTABLE, SackData.CODEC, SackData.CODEC);
        event.dataPackRegistry(InvExpRegistryKeys.TAGGED_SACK_INSERTABLE, TaggedSackData.CODEC, TaggedSackData.CODEC);
    }
}

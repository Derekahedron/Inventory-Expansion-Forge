package derekahedron.invexp.registry;

import derekahedron.invexp.sack.*;
import derekahedron.invexp.util.InvExpUtil;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraftforge.registries.DataPackRegistryEvent;

public class InvExpRegistryKeys {
    public static final ResourceKey<Registry<SackType>> SACK_TYPE;
    public static final ResourceKey<Registry<SackTypeDefault>> SACK_TYPE_DEFAULT;
    public static final ResourceKey<Registry<SackWeightDefault>> SACK_WEIGHT_DEFAULT;

    static {
        SACK_TYPE = ResourceKey.createRegistryKey(InvExpUtil.location("sack_type"));
        SACK_TYPE_DEFAULT = ResourceKey.createRegistryKey(InvExpUtil.location("sack_type_default"));
        SACK_WEIGHT_DEFAULT = ResourceKey.createRegistryKey(InvExpUtil.location("sack_weight_default"));
    }

    public static void initialize(DataPackRegistryEvent.NewRegistry event) {
        event.dataPackRegistry(InvExpRegistryKeys.SACK_TYPE, SackType.CODEC, SackType.CODEC);
        event.dataPackRegistry(InvExpRegistryKeys.SACK_TYPE_DEFAULT, SackTypeDefault.CODEC, SackTypeDefault.CODEC);
        event.dataPackRegistry(InvExpRegistryKeys.SACK_WEIGHT_DEFAULT, SackWeightDefault.CODEC, SackWeightDefault.CODEC);
    }
}

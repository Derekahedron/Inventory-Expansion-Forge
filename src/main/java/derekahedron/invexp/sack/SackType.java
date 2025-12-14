package derekahedron.invexp.sack;

import com.mojang.serialization.Codec;
import derekahedron.invexp.registry.InvExpRegistryKeys;
import net.minecraft.core.Holder;
import net.minecraft.resources.RegistryFileCodec;

public record SackType() {
    public static final Codec<SackType> CODEC =
            Codec.unit(SackType::new);
    public static final Codec<Holder<SackType>> ENTRY_CODEC =
            RegistryFileCodec.create(
                    InvExpRegistryKeys.SACK_TYPE, CODEC);
}

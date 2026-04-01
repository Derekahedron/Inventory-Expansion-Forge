package derekahedron.invexp.sack;

import com.mojang.serialization.Codec;

public record SackType() {
    public static final Codec<SackType> CODEC =
            Codec.unit(SackType::new);
}

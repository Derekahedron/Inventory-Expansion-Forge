package derekahedron.invexp.sack;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.Optional;

/**
 * Defines default sack data for an item. This is dynamic and defined in
 * DataPacks.
 *
 * @param sackType sack type to be inserted under. If none, cannot be inserted
 * @param sackWeight weight that the item should take up in a sack
 */
public record SackData(Optional<String> sackType, Optional<Integer> sackWeight) {
    public static final Codec<SackData> CODEC;

    static {
        CODEC = RecordCodecBuilder.create(instance -> instance.group(
                Codec.STRING.optionalFieldOf("sack_type").forGetter(SackData::sackType),
                Codec.INT.optionalFieldOf("sack_weight").forGetter(SackData::sackWeight)
        ).apply(instance, SackData::new));
    }
}

package derekahedron.invexp.sack;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.Optional;

/**
 * Defines default sack data for items by tag. This is dynamic and defined in
 * DataPacks.
 *
 * @param priority determines if this data should have priority over other tagged data
 * @param sackType sack type to be inserted under. If none, cannot be inserted
 * @param sackWeight weight that the item should take up in a sack
 */
public record TaggedSackData(int priority, Optional<String> sackType, Optional<Integer> sackWeight) {
    public static final Codec<TaggedSackData> CODEC;

    static {
        CODEC = RecordCodecBuilder.create(instance -> instance.group(
                Codec.INT.optionalFieldOf("priority", 0).forGetter(TaggedSackData::priority),
                Codec.STRING.optionalFieldOf("sack_type").forGetter(TaggedSackData::sackType),
                Codec.INT.optionalFieldOf("sack_weight").forGetter(TaggedSackData::sackWeight)
        ).apply(instance, TaggedSackData::new));
    }
}

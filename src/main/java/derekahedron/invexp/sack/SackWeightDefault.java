package derekahedron.invexp.sack;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import derekahedron.invexp.util.InvExpCodecs;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.apache.commons.lang3.math.Fraction;

import java.util.Optional;

public record SackWeightDefault(
        Optional<Integer> priority,
        Optional<Ingredient> items,
        Optional<ItemPredicate> predicate,
        Optional<Fraction> sackWeight) {
    public static final Codec<SackWeightDefault> CODEC =
            RecordCodecBuilder.create(instance -> instance.group(
                    Codec.INT
                            .optionalFieldOf("priority")
                            .forGetter(SackWeightDefault::priority),
                    InvExpCodecs.INGREDIENT
                            .optionalFieldOf("items")
                            .forGetter(SackWeightDefault::items),
                    InvExpCodecs.ITEM_PREDICATE
                            .optionalFieldOf("predicate")
                            .forGetter(SackWeightDefault::predicate),
                    InvExpCodecs.FRACTION
                            .optionalFieldOf("sack_weight")
                            .forGetter(SackWeightDefault::sackWeight)
            ).apply(instance, SackWeightDefault::new));

    public SackWeightDefault(TagKey<Item> tag, Fraction sackWeight) {
        this(Optional.empty(), Optional.of(Ingredient.of(tag)), Optional.empty(), Optional.of(sackWeight));
    }

    public boolean test(ItemStack stack) {
        return predicate.isEmpty() || predicate.get().matches(stack);
    }

    public int compareTo(SackWeightDefault other) {
        return priority.orElse(0) - other.priority.orElse(0);
    }
}

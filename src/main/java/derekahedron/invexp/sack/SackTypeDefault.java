package derekahedron.invexp.sack;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import derekahedron.invexp.util.InvExpCodecs;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.Holder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.Optional;

public record SackTypeDefault(
        Optional<Integer> priority,
        Optional<Ingredient> items,
        Optional<ItemPredicate> predicate,
        Optional<Holder<SackType>> sackType) {
    public static final Codec<SackTypeDefault> CODEC =
            RecordCodecBuilder.create(instance -> instance.group(
                    Codec.INT
                            .optionalFieldOf("priority")
                            .forGetter(SackTypeDefault::priority),
                    InvExpCodecs.INGREDIENT
                            .optionalFieldOf("items")
                            .forGetter(SackTypeDefault::items),
                    InvExpCodecs.ITEM_PREDICATE
                            .optionalFieldOf("predicate")
                            .forGetter(SackTypeDefault::predicate),
                    SackType.ENTRY_CODEC
                            .optionalFieldOf("sack_type")
                            .forGetter(SackTypeDefault::sackType)
            ).apply(instance, SackTypeDefault::new));

    public SackTypeDefault(int priority, TagKey<Item> tag, Holder<SackType> sackType) {
        this(Optional.of(priority), Optional.of(Ingredient.of(tag)), Optional.empty(), Optional.of(sackType));
    }

    public SackTypeDefault(TagKey<Item> tag, Holder<SackType> sackType) {
        this(Optional.empty(), Optional.of(Ingredient.of(tag)), Optional.empty(), Optional.of(sackType));
    }

    public boolean test(ItemStack stack) {
        return predicate.isEmpty() || predicate.get().matches(stack);
    }

    public int compareTo(SackTypeDefault other) {
        return priority.orElse(0) - other.priority.orElse(0);
    }
}

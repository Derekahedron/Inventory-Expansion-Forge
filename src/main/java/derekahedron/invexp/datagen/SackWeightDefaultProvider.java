package derekahedron.invexp.datagen;

import derekahedron.invexp.item.InvExpItemTags;
import derekahedron.invexp.registry.InvExpRegistryKeys;
import derekahedron.invexp.sack.SackWeightDefault;
import derekahedron.invexp.util.InvExpUtil;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import org.apache.commons.lang3.math.Fraction;

import java.util.Optional;

public class SackWeightDefaultProvider {

    public static void bootstrap(BootstapContext<SackWeightDefault> context) {

        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_WEIGHT_DEFAULT, InvExpUtil.location("double")),
                new SackWeightDefault(
                        InvExpItemTags.SackWeight.DOUBLE,
                        Fraction.getFraction(2)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_WEIGHT_DEFAULT, InvExpUtil.location("half")),
                new SackWeightDefault(
                        InvExpItemTags.SackWeight.HALF,
                        Fraction.ONE_HALF));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_WEIGHT_DEFAULT, InvExpUtil.location("third")),
                new SackWeightDefault(
                        InvExpItemTags.SackWeight.THIRD,
                        Fraction.ONE_THIRD));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_WEIGHT_DEFAULT, InvExpUtil.location("fourth")),
                new SackWeightDefault(
                        InvExpItemTags.SackWeight.FOURTH,
                        Fraction.ONE_QUARTER));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_WEIGHT_DEFAULT, InvExpUtil.location("fifth")),
                new SackWeightDefault(
                        InvExpItemTags.SackWeight.FIFTH,
                        Fraction.ONE_FIFTH));

        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_WEIGHT_DEFAULT, InvExpUtil.location("bees")),
                new SackWeightDefault(
                        Optional.of(10),
                        Optional.of(Ingredient.of(Items.BEEHIVE, Items.BEE_NEST)),
                        Optional.of(ItemPredicate.Builder.item().hasNbt(getBeesTag()).build()),
                        Optional.of(Fraction.getFraction(64))));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_WEIGHT_DEFAULT, InvExpUtil.location("water_bottle")),
                new SackWeightDefault(
                        Optional.of(10),
                        Optional.of(Ingredient.of(Items.POTION)),
                        Optional.of(ItemPredicate.Builder.item().isPotion(Potions.WATER).build()),
                        Optional.of(Fraction.ONE_QUARTER)));
    }

    public static CompoundTag getBeesTag() {
        CompoundTag entityData = new CompoundTag();
        entityData.putString("id", "minecraft:bee");
        CompoundTag bee = new CompoundTag();
        bee.put("EntityData", entityData);
        ListTag bees = new ListTag();
        bees.add(bee);
        CompoundTag blockEntityTag = new CompoundTag();
        blockEntityTag.put("Bees", bees);
        CompoundTag tag = new CompoundTag();
        tag.put("BlockEntityTag", blockEntityTag);
        return tag;
    }
}

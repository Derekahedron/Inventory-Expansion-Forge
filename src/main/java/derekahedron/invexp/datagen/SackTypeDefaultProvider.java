package derekahedron.invexp.datagen;

import derekahedron.invexp.registry.InvExpRegistryKeys;
import derekahedron.invexp.sack.SackType;
import derekahedron.invexp.sack.SackTypeDefault;
import derekahedron.invexp.sack.SackTypes;
import derekahedron.invexp.util.InvExpUtil;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderGetter;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.Optional;

import static derekahedron.invexp.sack.SackTypes.getItemTag;

public class SackTypeDefaultProvider {
    public static void bootstrap(BootstapContext<SackTypeDefault> context) {
        HolderGetter<SackType> sackTypeLookup = context.lookup(InvExpRegistryKeys.SACK_TYPE);

        // Register all sack types
        for (ResourceKey<SackType> sackType : SackTypes.SACK_TYPES) {
            context.register(
                    ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, sackType.location()),
                    new SackTypeDefault(
                            getItemTag(sackType),
                            sackTypeLookup.getOrThrow(sackType).key()));
        }

        // Special Sack Types
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("bamboo_planks")),
                new SackTypeDefault(
                        Optional.of(10),
                        Optional.of(Ingredient.of(
                                Items.BAMBOO_PLANKS, Items.BAMBOO_SLAB, Items.BAMBOO_FENCE, Items.BAMBOO_FENCE_GATE)),
                        Optional.empty(),
                        Optional.of(sackTypeLookup.getOrThrow(SackTypes.BAMBOO).key())));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("raw_infested_stone")),
                new SackTypeDefault(
                        10,
                        Ingredient.of(
                                Items.INFESTED_STONE, Items.INFESTED_COBBLESTONE, Items.INFESTED_DEEPSLATE),
                        sackTypeLookup.getOrThrow(SackTypes.INFESTED_STONE).key()));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("moss_blocks")),
                new SackTypeDefault(
                        10,
                        Ingredient.of(Items.MOSS_BLOCK),
                        sackTypeLookup.getOrThrow(SackTypes.PLANT).key()));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("water_bottle")),
                new SackTypeDefault(
                        Optional.of(10),
                        Optional.of(Ingredient.of(Items.POTION)),
                        Optional.of(ItemPredicate.Builder.item().isPotion(Potions.WATER).build()),
                        Optional.of(sackTypeLookup.getOrThrow(SackTypes.BOTTLE).key())));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("cake")),
                new SackTypeDefault(
                        Optional.of(20),
                        Optional.of(Ingredient.of(Items.CAKE)),
                        Optional.empty(),
                        Optional.empty()));
    }
}

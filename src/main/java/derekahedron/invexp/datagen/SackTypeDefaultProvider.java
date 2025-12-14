package derekahedron.invexp.datagen;

import derekahedron.invexp.item.InvExpItemTags;
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

public class SackTypeDefaultProvider {
    public static void bootstrap(BootstapContext<SackTypeDefault> context) {
        HolderGetter<SackType> sackTypeLookup = context.lookup(InvExpRegistryKeys.SACK_TYPE);

        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("wood")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.WOOD,
                        sackTypeLookup.getOrThrow(SackTypes.WOOD)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("door")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.DOOR,
                        sackTypeLookup.getOrThrow(SackTypes.DOOR)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("pressure_plate")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.PRESSURE_PLATE,
                        sackTypeLookup.getOrThrow(SackTypes.PRESSURE_PLATE)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("button")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.BUTTON,
                        sackTypeLookup.getOrThrow(SackTypes.BUTTON)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("stone")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.STONE,
                        sackTypeLookup.getOrThrow(SackTypes.STONE)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("bricks")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.BRICKS,
                        sackTypeLookup.getOrThrow(SackTypes.BRICKS)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("mud_bricks")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.MUD_BRICKS,
                        sackTypeLookup.getOrThrow(SackTypes.MUD_BRICKS)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("sandstone")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.SANDSTONE,
                        sackTypeLookup.getOrThrow(SackTypes.SANDSTONE)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("prismarine")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.PRISMARINE,
                        sackTypeLookup.getOrThrow(SackTypes.PRISMARINE)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("nether_bricks")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.NETHER_BRICKS,
                        sackTypeLookup.getOrThrow(SackTypes.NETHER_BRICKS)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("purpur")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.PURPUR,
                        sackTypeLookup.getOrThrow(SackTypes.PURPUR)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("metal_block")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.METAL_BLOCK,
                        sackTypeLookup.getOrThrow(SackTypes.METAL_BLOCK)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("crystal_block")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.CRYSTAL_BLOCK,
                        sackTypeLookup.getOrThrow(SackTypes.CRYSTAL_BLOCK)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("chains")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.CHAINS,
                        sackTypeLookup.getOrThrow(SackTypes.CHAINS)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("wool")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.WOOL,
                        sackTypeLookup.getOrThrow(SackTypes.WOOL)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("terracotta")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.TERRACOTTA,
                        sackTypeLookup.getOrThrow(SackTypes.TERRACOTTA)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("concrete")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.CONCRETE,
                        sackTypeLookup.getOrThrow(SackTypes.CONCRETE)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("concrete_powder")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.CONCRETE_POWDER,
                        sackTypeLookup.getOrThrow(SackTypes.CONCRETE_POWDER)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("glass")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.GLASS,
                        sackTypeLookup.getOrThrow(SackTypes.GLASS)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("bed")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.BED,
                        sackTypeLookup.getOrThrow(SackTypes.BED)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("candle")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.CANDLE,
                        sackTypeLookup.getOrThrow(SackTypes.CANDLE)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("banner")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.BANNER,
                        sackTypeLookup.getOrThrow(SackTypes.BANNER)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("soil")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.SOIL,
                        sackTypeLookup.getOrThrow(SackTypes.SOIL)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("ice")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.ICE,
                        sackTypeLookup.getOrThrow(SackTypes.ICE)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("snow")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.SNOW,
                        sackTypeLookup.getOrThrow(SackTypes.SNOW)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("bone_block")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.BONE_BLOCK,
                        sackTypeLookup.getOrThrow(SackTypes.BONE_BLOCK)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("ore")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.ORE,
                        sackTypeLookup.getOrThrow(SackTypes.ORE)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("fungus")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.FUNGUS,
                        sackTypeLookup.getOrThrow(SackTypes.FUNGUS)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("plant")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.PLANT,
                        sackTypeLookup.getOrThrow(SackTypes.PLANT)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("bamboo")),
                new SackTypeDefault(
                        Optional.of(10),
                        Optional.of(Ingredient.of(InvExpItemTags.SackType.BAMBOO)),
                        Optional.empty(),
                        Optional.of(sackTypeLookup.getOrThrow(SackTypes.BAMBOO))));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("chorus_fruit")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.CHORUS_FRUIT,
                        sackTypeLookup.getOrThrow(SackTypes.CHORUS_FRUIT)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("egg")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.EGG,
                        sackTypeLookup.getOrThrow(SackTypes.EGG)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("wheat_seeds")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.WHEAT_SEEDS,
                        sackTypeLookup.getOrThrow(SackTypes.WHEAT_SEEDS)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("cocoa_beans")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.COCOA_BEANS,
                        sackTypeLookup.getOrThrow(SackTypes.COCOA_BEANS)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("pumpkin_seeds")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.PUMPKIN_SEEDS,
                        sackTypeLookup.getOrThrow(SackTypes.PUMPKIN_SEEDS)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("melon_seeds")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.MELON_SEEDS,
                        sackTypeLookup.getOrThrow(SackTypes.MELON_SEEDS)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("beetroot_seeds")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.BEETROOT_SEEDS,
                        sackTypeLookup.getOrThrow(SackTypes.BEETROOT_SEEDS)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("torchflower_seeds")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.TORCHFLOWER_SEEDS,
                        sackTypeLookup.getOrThrow(SackTypes.TORCHFLOWER_SEEDS)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("pitcher_pod")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.PITCHER_POD,
                        sackTypeLookup.getOrThrow(SackTypes.PITCHER_POD)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("glow_berries")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.GLOW_BERRIES,
                        sackTypeLookup.getOrThrow(SackTypes.GLOW_BERRIES)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("sweet_berries")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.SWEET_BERRIES,
                        sackTypeLookup.getOrThrow(SackTypes.SWEET_BERRIES)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("nether_wart")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.NETHER_WART,
                        sackTypeLookup.getOrThrow(SackTypes.NETHER_WART)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("sea_creature")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.SEA_CREATURE,
                        sackTypeLookup.getOrThrow(SackTypes.SEA_CREATURE)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("kelp")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.KELP,
                        sackTypeLookup.getOrThrow(SackTypes.KELP)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("coral")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.CORAL,
                        sackTypeLookup.getOrThrow(SackTypes.CORAL)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("sponge")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.SPONGE,
                        sackTypeLookup.getOrThrow(SackTypes.SPONGE)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("melon")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.MELON,
                        sackTypeLookup.getOrThrow(SackTypes.MELON)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("pumpkin")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.PUMPKIN,
                        sackTypeLookup.getOrThrow(SackTypes.PUMPKIN)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("hive")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.NEST,
                        sackTypeLookup.getOrThrow(SackTypes.NEST)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("honey")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.HONEY,
                        sackTypeLookup.getOrThrow(SackTypes.HONEY)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("froglight")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.FROGLIGHT,
                        sackTypeLookup.getOrThrow(SackTypes.FROGLIGHT)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("sculk")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.SCULK,
                        sackTypeLookup.getOrThrow(SackTypes.SCULK)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("cobweb")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.COBWEB,
                        sackTypeLookup.getOrThrow(SackTypes.COBWEB)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("bedrock")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.BEDROCK,
                        sackTypeLookup.getOrThrow(SackTypes.BEDROCK)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("torch")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.TORCH,
                        sackTypeLookup.getOrThrow(SackTypes.TORCH)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("lantern")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.LANTERN,
                        sackTypeLookup.getOrThrow(SackTypes.LANTERN)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("end_crystal")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.END_CRYSTAL,
                        sackTypeLookup.getOrThrow(SackTypes.END_CRYSTAL)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("bell")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.BELL,
                        sackTypeLookup.getOrThrow(SackTypes.BELL)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("scaffolding")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.SCAFFOLDING,
                        sackTypeLookup.getOrThrow(SackTypes.SCAFFOLDING)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("pot")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.POT,
                        sackTypeLookup.getOrThrow(SackTypes.POT)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("armor_stand")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.ARMOR_STAND,
                        sackTypeLookup.getOrThrow(SackTypes.ARMOR_STAND)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("item_frame")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.ITEM_FRAME,
                        sackTypeLookup.getOrThrow(SackTypes.ITEM_FRAME)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("painting")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.PAINTING,
                        sackTypeLookup.getOrThrow(SackTypes.PAINTING)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("sign")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.SIGN,
                        sackTypeLookup.getOrThrow(SackTypes.SIGN)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("head")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.HEAD,
                        sackTypeLookup.getOrThrow(SackTypes.HEAD)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("infested_stone")),
                new SackTypeDefault(
                        10,
                        InvExpItemTags.SackType.INFESTED_STONE,
                        sackTypeLookup.getOrThrow(SackTypes.INFESTED_STONE)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("redstone_component")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.REDSTONE_COMPONENT,
                        sackTypeLookup.getOrThrow(SackTypes.REDSTONE_COMPONENT)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("rail")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.RAIL,
                        sackTypeLookup.getOrThrow(SackTypes.RAIL)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("minecart")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.MINECART,
                        sackTypeLookup.getOrThrow(SackTypes.MINECART)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("tnt")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.TNT,
                        sackTypeLookup.getOrThrow(SackTypes.TNT)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("bucket")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.BUCKET,
                        sackTypeLookup.getOrThrow(SackTypes.BUCKET)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("fire_charge")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.FIRE_CHARGE,
                        sackTypeLookup.getOrThrow(SackTypes.FIRE_CHARGE)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("bone_meal")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.BONE_MEAL,
                        sackTypeLookup.getOrThrow(SackTypes.BONE_MEAL)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("name_tag")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.NAME_TAG,
                        sackTypeLookup.getOrThrow(SackTypes.NAME_TAG)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("lead")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.LEAD,
                        sackTypeLookup.getOrThrow(SackTypes.LEAD)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("compass")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.COMPASS,
                        sackTypeLookup.getOrThrow(SackTypes.COMPASS)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("clock")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.CLOCK,
                        sackTypeLookup.getOrThrow(SackTypes.CLOCK)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("map")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.MAP,
                        sackTypeLookup.getOrThrow(SackTypes.MAP)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("firework_rocket")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.FIREWORK_ROCKET,
                        sackTypeLookup.getOrThrow(SackTypes.FIREWORK_ROCKET)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("saddle")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.SADDLE,
                        sackTypeLookup.getOrThrow(SackTypes.SADDLE)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("boat")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.BOAT,
                        sackTypeLookup.getOrThrow(SackTypes.BOAT)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("goat_horn")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.GOAT_HORN,
                        sackTypeLookup.getOrThrow(SackTypes.GOAT_HORN)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("music_disc")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.MUSIC_DISC,
                        sackTypeLookup.getOrThrow(SackTypes.MUSIC_DISC)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("totem_of_undying")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.TOTEM_OF_UNDYING,
                        sackTypeLookup.getOrThrow(SackTypes.TOTEM_OF_UNDYING)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("arrow")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.ARROW,
                        sackTypeLookup.getOrThrow(SackTypes.ARROW)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("food")),
                new SackTypeDefault(
                        10,
                        InvExpItemTags.SackType.FOOD,
                        sackTypeLookup.getOrThrow(SackTypes.FOOD)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("carrot")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.CARROT,
                        sackTypeLookup.getOrThrow(SackTypes.CARROT)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("potato")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.POTATO,
                        sackTypeLookup.getOrThrow(SackTypes.POTATO)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("beetroot")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.BEETROOT,
                        sackTypeLookup.getOrThrow(SackTypes.BEETROOT)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("raw_fish")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.RAW_FISH,
                        sackTypeLookup.getOrThrow(SackTypes.RAW_FISH)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("bottle")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.BOTTLE,
                        sackTypeLookup.getOrThrow(SackTypes.BOTTLE)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("potion")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.POTION,
                        sackTypeLookup.getOrThrow(SackTypes.POTION)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("wheat")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.WHEAT,
                        sackTypeLookup.getOrThrow(SackTypes.WHEAT)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("creature")),
                new SackTypeDefault(
                        10,
                        InvExpItemTags.SackType.CREATURE,
                        sackTypeLookup.getOrThrow(SackTypes.CREATURE)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("heart_of_the_sea")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.HEART_OF_THE_SEA,
                        sackTypeLookup.getOrThrow(SackTypes.HEART_OF_THE_SEA)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("dye")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.DYE,
                        sackTypeLookup.getOrThrow(SackTypes.DYE)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("paper")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.PAPER,
                        sackTypeLookup.getOrThrow(SackTypes.PAPER)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("book")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.BOOK,
                        sackTypeLookup.getOrThrow(SackTypes.BOOK)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("firework_star")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.FIREWORK_STAR,
                        sackTypeLookup.getOrThrow(SackTypes.FIREWORK_STAR)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("sugar")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.SUGAR,
                        sackTypeLookup.getOrThrow(SackTypes.SUGAR)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("banner_pattern")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.BANNER_PATTERN,
                        sackTypeLookup.getOrThrow(SackTypes.BANNER_PATTERN)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("pottery_sherd")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.POTTERY_SHERD,
                        sackTypeLookup.getOrThrow(SackTypes.POTTERY_SHERD)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("key")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.KEY,
                        sackTypeLookup.getOrThrow(SackTypes.KEY)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("smithing_template")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.SMITHING_TEMPLATE,
                        sackTypeLookup.getOrThrow(SackTypes.SMITHING_TEMPLATE)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("spawn_egg")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.SPAWN_EGG,
                        sackTypeLookup.getOrThrow(SackTypes.SPAWN_EGG)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("command_block")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.COMMAND_BLOCK,
                        sackTypeLookup.getOrThrow(SackTypes.COMMAND_BLOCK)));

        // Special Sack Types
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("moss_blocks")),
                new SackTypeDefault(
                        Optional.of(10),
                        Optional.of(Ingredient.of(Items.MOSS_BLOCK)),
                        Optional.empty(),
                        Optional.of(sackTypeLookup.getOrThrow(SackTypes.PLANT))));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("water_bottle")),
                new SackTypeDefault(
                        Optional.of(10),
                        Optional.of(Ingredient.of(Items.POTION)),
                        Optional.of(ItemPredicate.Builder.item().isPotion(Potions.WATER).build()),
                        Optional.of(sackTypeLookup.getOrThrow(SackTypes.BOTTLE))));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("cake")),
                new SackTypeDefault(
                        Optional.of(20),
                        Optional.of(Ingredient.of(Items.CAKE)),
                        Optional.empty(),
                        Optional.empty()));

        // Extra Sack Types
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("cabbage")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.CABBAGE,
                        sackTypeLookup.getOrThrow(SackTypes.CABBAGE)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("cabbage_seeds")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.CABBAGE_SEEDS,
                        sackTypeLookup.getOrThrow(SackTypes.CABBAGE_SEEDS)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("tomato")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.TOMATO,
                        sackTypeLookup.getOrThrow(SackTypes.TOMATO)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("tomato_seeds")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.TOMATO_SEEDS,
                        sackTypeLookup.getOrThrow(SackTypes.TOMATO_SEEDS)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("onion")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.ONION,
                        sackTypeLookup.getOrThrow(SackTypes.ONION)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("rice")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.RICE,
                        sackTypeLookup.getOrThrow(SackTypes.RICE)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("straw")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.STRAW,
                        sackTypeLookup.getOrThrow(SackTypes.STRAW)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("rope")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.ROPE,
                        sackTypeLookup.getOrThrow(SackTypes.ROPE)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("metal_plate")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.METAL_PLATE,
                        sackTypeLookup.getOrThrow(SackTypes.METAL_PLATE)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("unknown")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.UNKNOWN,
                        sackTypeLookup.getOrThrow(SackTypes.UNKNOWN)));
        
        // Alex's Caves Sack Types
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("cave_tablet")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.CAVE_TABLET,
                        sackTypeLookup.getOrThrow(SackTypes.CAVE_TABLET)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("cave_codex")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.CAVE_CODEX,
                        sackTypeLookup.getOrThrow(SackTypes.CAVE_CODEX)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("tesla_bulb")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.TESLA_BULB,
                        sackTypeLookup.getOrThrow(SackTypes.TESLA_BULB)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("ominous_catalyst")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.OMINOUS_CATALYST,
                        sackTypeLookup.getOrThrow(SackTypes.OMINOUS_CATALYST)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("toxic_waste")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.TOXIC_WASTE,
                        sackTypeLookup.getOrThrow(SackTypes.TOXIC_WASTE)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("nuclear_bomb")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.NUCLEAR_BOMB,
                        sackTypeLookup.getOrThrow(SackTypes.NUCLEAR_BOMB)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("radon_lamp")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.RADON_LAMP,
                        sackTypeLookup.getOrThrow(SackTypes.RADON_LAMP)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("floater")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.FLOATER,
                        sackTypeLookup.getOrThrow(SackTypes.FLOATER)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("ink_bomb")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.INK_BOMB,
                        sackTypeLookup.getOrThrow(SackTypes.INK_BOMB)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("depth_charge")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.DEPTH_CHARGE,
                        sackTypeLookup.getOrThrow(SackTypes.DEPTH_CHARGE)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("guano")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.GUANO,
                        sackTypeLookup.getOrThrow(SackTypes.GUANO)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("fertilizer")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.FERTILIZER,
                        sackTypeLookup.getOrThrow(SackTypes.FERTILIZER)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("moth_ball")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.MOTH_BALL,
                        sackTypeLookup.getOrThrow(SackTypes.MOTH_BALL)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("sweets")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.SWEETS,
                        sackTypeLookup.getOrThrow(SackTypes.SWEETS)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("jelly_bean")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.JELLY_BEAN,
                        sackTypeLookup.getOrThrow(SackTypes.JELLY_BEAN)));

        // Biomes O' Plenty Sack Types
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("body_part")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.BODY_PART,
                        sackTypeLookup.getOrThrow(SackTypes.BODY_PART)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("wispjelly")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.WISPJELLY,
                        sackTypeLookup.getOrThrow(SackTypes.WISPJELLY)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("null")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.NULL,
                        sackTypeLookup.getOrThrow(SackTypes.NULL)));

        // Deeper and Darker Sack Types
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("sculk_grime_bricks")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.SCULK_GRIME_BRICKS,
                        sackTypeLookup.getOrThrow(SackTypes.SCULK_GRIME_BRICKS)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("bloom_berries")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.BLOOM_BERRIES,
                        sackTypeLookup.getOrThrow(SackTypes.BLOOM_BERRIES)));

        // Farmer's Delight Sack Types
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("rice_panicle")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.RICE_PANICLE,
                        sackTypeLookup.getOrThrow(SackTypes.RICE_PANICLE)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("animal_food")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.ANIMAL_FOOD,
                        sackTypeLookup.getOrThrow(SackTypes.ANIMAL_FOOD)));

        // Galosphere Sack Types
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("barometer")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.BAROMETER,
                        sackTypeLookup.getOrThrow(SackTypes.BAROMETER)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("silver_bomb")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.SILVER_BOMB,
                        sackTypeLookup.getOrThrow(SackTypes.SILVER_BOMB)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("glow_flare")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.GLOW_FLARE,
                        sackTypeLookup.getOrThrow(SackTypes.GLOW_FLARE)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("spectre_flare")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.SPECTRE_FLARE,
                        sackTypeLookup.getOrThrow(SackTypes.SPECTRE_FLARE)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("glow_ink_clumps")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.GLOW_INK_CLUMPS,
                        sackTypeLookup.getOrThrow(SackTypes.GLOW_INK_CLUMPS)));

        // Storage Drawers Sack Types
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("drawer_upgrade")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.DRAWER_UPGRADE,
                        sackTypeLookup.getOrThrow(SackTypes.DRAWER_UPGRADE)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("drawer_controller")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.DRAWER_CONTROLLER,
                        sackTypeLookup.getOrThrow(SackTypes.DRAWER_CONTROLLER)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("key_button")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.KEY_BUTTON,
                        sackTypeLookup.getOrThrow(SackTypes.KEY_BUTTON)));

        // Tinkers Construct Sack Types
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("glowball")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.GLOWBALL,
                        sackTypeLookup.getOrThrow(SackTypes.GLOWBALL)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("efln")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.EFLN,
                        sackTypeLookup.getOrThrow(SackTypes.EFLN)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("shuriken")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.SHURIKEN,
                        sackTypeLookup.getOrThrow(SackTypes.SHURIKEN)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("tinkers_reinforcement")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.TINKERS_REINFORCEMENT,
                        sackTypeLookup.getOrThrow(SackTypes.TINKERS_REINFORCEMENT)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("pattern")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.PATTERN,
                        sackTypeLookup.getOrThrow(SackTypes.PATTERN)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("repair_kit")),
                new SackTypeDefault(
                        10,
                        InvExpItemTags.SackType.REPAIR_KIT,
                        sackTypeLookup.getOrThrow(SackTypes.REPAIR_KIT)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("tool_part")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.TOOL_PART,
                        sackTypeLookup.getOrThrow(SackTypes.TOOL_PART)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("modifier_crystal")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.MODIFIER_CRYSTAL,
                        sackTypeLookup.getOrThrow(SackTypes.MODIFIER_CRYSTAL)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("copper_can")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.COPPER_CAN,
                        sackTypeLookup.getOrThrow(SackTypes.COPPER_CAN)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("seared_stone")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.SEARED_STONE,
                        sackTypeLookup.getOrThrow(SackTypes.SEARED_STONE)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("scorched_stone")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.SCORCHED_STONE,
                        sackTypeLookup.getOrThrow(SackTypes.SCORCHED_STONE)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("tank")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.TANK,
                        sackTypeLookup.getOrThrow(SackTypes.TANK)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("cast")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.CAST,
                        sackTypeLookup.getOrThrow(SackTypes.CAST)));

        // Waystones Sack Types
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("waystone")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.WAYSTONE,
                        sackTypeLookup.getOrThrow(SackTypes.WAYSTONE)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("sharestone")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.SHARESTONE,
                        sackTypeLookup.getOrThrow(SackTypes.SHARESTONE)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("portstone")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.PORTSTONE,
                        sackTypeLookup.getOrThrow(SackTypes.PORTSTONE)));
        context.register(
                ResourceKey.create(InvExpRegistryKeys.SACK_TYPE_DEFAULT, InvExpUtil.location("warp_plate")),
                new SackTypeDefault(
                        InvExpItemTags.SackType.WARP_PLATE,
                        sackTypeLookup.getOrThrow(SackTypes.WARP_PLATE)));
    }
}

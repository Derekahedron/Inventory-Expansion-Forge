package derekahedron.invexp.sack;

import derekahedron.invexp.registry.InvExpRegistryKeys;
import derekahedron.invexp.util.InvExpUtil;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.List;

public class SackTypes {
    // Store all types
    public static final List<ResourceKey<SackType>> SACK_TYPES = new ArrayList<>();

    // Vanilla Sack Types
    public static final ResourceKey<SackType> WOOD = of("wood");
    public static final ResourceKey<SackType> DOOR = of("door");
    public static final ResourceKey<SackType> PRESSURE_PLATE = of("pressure_plate");
    public static final ResourceKey<SackType> BUTTON = of("button");
    public static final ResourceKey<SackType> STONE = of("stone");
    public static final ResourceKey<SackType> BRICKS = of("bricks");
    public static final ResourceKey<SackType> MUD_BRICKS = of("mud_bricks");
    public static final ResourceKey<SackType> SANDSTONE = of("sandstone");
    public static final ResourceKey<SackType> PRISMARINE = of("prismarine");
    public static final ResourceKey<SackType> NETHER_BRICKS = of("nether_bricks");
    public static final ResourceKey<SackType> PURPUR = of("purpur");
    public static final ResourceKey<SackType> METAL_BLOCK = of("metal_block");
    public static final ResourceKey<SackType> CRYSTAL_BLOCK = of("crystal_block");
    public static final ResourceKey<SackType> CHAINS = of("chains");
    public static final ResourceKey<SackType> WOOL = of("wool");
    public static final ResourceKey<SackType> TERRACOTTA = of("terracotta");
    public static final ResourceKey<SackType> CONCRETE = of("concrete");
    public static final ResourceKey<SackType> CONCRETE_POWDER = of("concrete_powder");
    public static final ResourceKey<SackType> GLASS = of("glass");
    public static final ResourceKey<SackType> BED = of("bed");
    public static final ResourceKey<SackType> CANDLE = of("candle");
    public static final ResourceKey<SackType> BANNER = of("banner");
    public static final ResourceKey<SackType> SOIL = of("soil");
    public static final ResourceKey<SackType> ICE = of("ice");
    public static final ResourceKey<SackType> SNOW = of("snow");
    public static final ResourceKey<SackType> BONE_BLOCK = of("bone_block");
    public static final ResourceKey<SackType> ORE = of("ore");
    public static final ResourceKey<SackType> FUNGUS = of("fungus");
    public static final ResourceKey<SackType> PLANT = of("plant");
    public static final ResourceKey<SackType> BAMBOO = of("bamboo");
    public static final ResourceKey<SackType> CHORUS_FRUIT = of("chorus_fruit");
    public static final ResourceKey<SackType> EGG = of("egg");
    public static final ResourceKey<SackType> WHEAT_SEEDS = of("wheat_seeds");
    public static final ResourceKey<SackType> COCOA_BEANS = of("cocoa_beans");
    public static final ResourceKey<SackType> PUMPKIN_SEEDS = of("pumpkin_seeds");
    public static final ResourceKey<SackType> MELON_SEEDS = of("melon_seeds");
    public static final ResourceKey<SackType> BEETROOT_SEEDS = of("beetroot_seeds");
    public static final ResourceKey<SackType> TORCHFLOWER_SEEDS = of("torchflower_seeds");
    public static final ResourceKey<SackType> PITCHER_POD = of("pitcher_pod");
    public static final ResourceKey<SackType> GLOW_BERRIES = of("glow_berries");
    public static final ResourceKey<SackType> SWEET_BERRIES = of("sweet_berries");
    public static final ResourceKey<SackType> NETHER_WART = of("nether_wart");
    public static final ResourceKey<SackType> SEA_CREATURE = of("sea_creature");
    public static final ResourceKey<SackType> KELP = of("kelp");
    public static final ResourceKey<SackType> CORAL = of("coral");
    public static final ResourceKey<SackType> SPONGE = of("sponge");
    public static final ResourceKey<SackType> MELON = of("melon");
    public static final ResourceKey<SackType> PUMPKIN = of("pumpkin");
    public static final ResourceKey<SackType> NEST = of("nest");
    public static final ResourceKey<SackType> HONEY = of("honey");
    public static final ResourceKey<SackType> FROGLIGHT = of("froglight");
    public static final ResourceKey<SackType> SCULK = of("sculk");
    public static final ResourceKey<SackType> COBWEB = of("cobweb");
    public static final ResourceKey<SackType> BEDROCK = of("bedrock");
    public static final ResourceKey<SackType> TORCH = of("torch");
    public static final ResourceKey<SackType> LANTERN = of("lantern");
    public static final ResourceKey<SackType> END_CRYSTAL = of("end_crystal");
    public static final ResourceKey<SackType> BELL = of("bell");
    public static final ResourceKey<SackType> SCAFFOLDING = of("scaffolding");
    public static final ResourceKey<SackType> POT = of("pot");
    public static final ResourceKey<SackType> ARMOR_STAND = of("armor_stand");
    public static final ResourceKey<SackType> ITEM_FRAME = of("item_frame");
    public static final ResourceKey<SackType> PAINTING = of("painting");
    public static final ResourceKey<SackType> SIGN = of("sign");
    public static final ResourceKey<SackType> HEAD = of("head");
    public static final ResourceKey<SackType> INFESTED_STONE = of("infested_stone");
    public static final ResourceKey<SackType> REDSTONE_COMPONENT = of("redstone_component");
    public static final ResourceKey<SackType> RAIL = of("rail");
    public static final ResourceKey<SackType> MINECART = of("minecart");
    public static final ResourceKey<SackType> TNT = of("tnt");
    public static final ResourceKey<SackType> BUCKET = of("bucket");
    public static final ResourceKey<SackType> FIRE_CHARGE = of("fire_charge");
    public static final ResourceKey<SackType> BONE_MEAL = of("bone_meal");
    public static final ResourceKey<SackType> NAME_TAG = of("name_tag");
    public static final ResourceKey<SackType> LEAD = of("lead");
    public static final ResourceKey<SackType> COMPASS = of("compass");
    public static final ResourceKey<SackType> CLOCK = of("clock");
    public static final ResourceKey<SackType> MAP = of("map");
    public static final ResourceKey<SackType> FIREWORK_ROCKET = of("firework_rocket");
    public static final ResourceKey<SackType> SADDLE = of("saddle");
    public static final ResourceKey<SackType> BOAT = of("boat");
    public static final ResourceKey<SackType> GOAT_HORN = of("goat_horn");
    public static final ResourceKey<SackType> MUSIC_DISC = of("music_disc");
    public static final ResourceKey<SackType> TOTEM_OF_UNDYING = of("totem_of_undying");
    public static final ResourceKey<SackType> ARROW = of("arrow");
    public static final ResourceKey<SackType> FOOD = of("food");
    public static final ResourceKey<SackType> CARROT = of("carrot");
    public static final ResourceKey<SackType> POTATO = of("potato");
    public static final ResourceKey<SackType> BEETROOT = of("beetroot");
    public static final ResourceKey<SackType> RAW_FISH = of("raw_fish");
    public static final ResourceKey<SackType> BOTTLE = of("bottle");
    public static final ResourceKey<SackType> POTION = of("potion");
    public static final ResourceKey<SackType> WHEAT = of("wheat");
    public static final ResourceKey<SackType> CREATURE = of("creature");
    public static final ResourceKey<SackType> HEART_OF_THE_SEA = of("heart_of_the_sea");
    public static final ResourceKey<SackType> DYE = of("dye");
    public static final ResourceKey<SackType> PAPER = of("paper");
    public static final ResourceKey<SackType> BOOK = of("book");
    public static final ResourceKey<SackType> FIREWORK_STAR = of("firework_star");
    public static final ResourceKey<SackType> SUGAR = of("sugar");
    public static final ResourceKey<SackType> BANNER_PATTERN = of("banner_pattern");
    public static final ResourceKey<SackType> POTTERY_SHERD = of("pottery_sherd");
    public static final ResourceKey<SackType> SMITHING_TEMPLATE = of("smithing_template");
    public static final ResourceKey<SackType> KEY = of("key");
    public static final ResourceKey<SackType> SPAWN_EGG = of("spawn_egg");
    public static final ResourceKey<SackType> COMMAND_BLOCK = of("command_block");
    // Extra Sack Types
    public static final ResourceKey<SackType> CABBAGE = of("cabbage");
    public static final ResourceKey<SackType> CABBAGE_SEEDS = of("cabbage_seeds");
    public static final ResourceKey<SackType> TOMATO = of("tomato");
    public static final ResourceKey<SackType> TOMATO_SEEDS = of("tomato_seeds");
    public static final ResourceKey<SackType> CANTALOUPE = of("cantaloupe");
    public static final ResourceKey<SackType> CANTALOUPE_SEEDS = of("cantaloupe_seeds");
    public static final ResourceKey<SackType> SALMONBERRIES = of("salmonberries");
    public static final ResourceKey<SackType> SALMONBERRY_SEEDS = of("salmonberry_seeds");
    public static final ResourceKey<SackType> ONION = of("onion");
    public static final ResourceKey<SackType> RICE = of("rice");
    public static final ResourceKey<SackType> STRAW = of("straw");
    public static final ResourceKey<SackType> ROPE = of("rope");
    public static final ResourceKey<SackType> METAL_PLATE = of("metal_plate");
    public static final ResourceKey<SackType> TROPHY = of("trophy");
    public static final ResourceKey<SackType> UNKNOWN = of("unknown");
    // Alex's Caves Sack Types
    public static final ResourceKey<SackType> CAVE_TABLET = of("cave_tablet");
    public static final ResourceKey<SackType> CAVE_CODEX = of("cave_codex");
    public static final ResourceKey<SackType> TESLA_BULB = of("tesla_bulb");
    public static final ResourceKey<SackType> OMINOUS_CATALYST = of("ominous_catalyst");
    public static final ResourceKey<SackType> TOXIC_WASTE = of("toxic_waste");
    public static final ResourceKey<SackType> NUCLEAR_BOMB = of("nuclear_bomb");
    public static final ResourceKey<SackType> RADON_LAMP = of("radon_lamp");
    public static final ResourceKey<SackType> FLOATER = of("floater");
    public static final ResourceKey<SackType> INK_BOMB = of("ink_bomb");
    public static final ResourceKey<SackType> DEPTH_CHARGE = of("depth_charge");
    public static final ResourceKey<SackType> GUANO = of("guano");
    public static final ResourceKey<SackType> FERTILIZER = of("fertilizer");
    public static final ResourceKey<SackType> MOTH_BALL = of("moth_ball");
    public static final ResourceKey<SackType> SWEETS = of("sweets");
    public static final ResourceKey<SackType> JELLY_BEAN = of("jelly_bean");
    // Apothesis Sack Types
    public static final ResourceKey<SackType> APOTHEOSIS_GEM = of("apotheosis_gem");
    // Biomes O' Plenty Sack Types
    public static final ResourceKey<SackType> BODY_PART = of("body_part");
    public static final ResourceKey<SackType> WISPJELLY = of("wispjelly");
    public static final ResourceKey<SackType> NULL = of("null");
    // Blue Skies Sack Types
    public static final ResourceKey<SackType> PINE_FRUIT_SEEDS = of("pine_fruit_seeds");
    public static final ResourceKey<SackType> PINE_FRUIT = of("pine_fruit");
    public static final ResourceKey<SackType> WINTER_LEAF_SEEDS = of("winter_leaf_seeds");
    public static final ResourceKey<SackType> WINTER_LEAVES = of("winter_leaves");
    public static final ResourceKey<SackType> SCALEFRUIT_SEEDS = of("scalefruit_seeds");
    public static final ResourceKey<SackType> SCALEFRUIT = of("scalefruit");
    public static final ResourceKey<SackType> FIERY_BEAN_SEEDS = of("fiery_bean_seeds");
    public static final ResourceKey<SackType> FIERY_BEANS = of("fiery_beans");
    public static final ResourceKey<SackType> CRYO_ROOT = of("cryo_root");
    public static final ResourceKey<SackType> SOLNUT = of("solnut");
    public static final ResourceKey<SackType> WARDING_PEARL = of("warding_pearl");
    // Deeper and Darker Sack Types
    public static final ResourceKey<SackType> SCULK_GRIME_BRICKS = of("sculk_grime_bricks");
    public static final ResourceKey<SackType> BLOOM_BERRIES = of("bloom_berries");
    // Elevators Sack Types
    public static final ResourceKey<SackType> ELEVATOR = of("elevator");
    // EnderIO Sack Types
    public static final ResourceKey<SackType> BROKEN_SPAWNER = of("broken_spawner");
    // Farmer's Delight Sack Types
    public static final ResourceKey<SackType> RICE_PANICLE = of("rice_panicle");
    public static final ResourceKey<SackType> ANIMAL_FOOD = of("animal_food");
    // Galosphere Sack Types
    public static final ResourceKey<SackType> BAROMETER = of("barometer");
    public static final ResourceKey<SackType> SILVER_BOMB = of("silver_bomb");
    public static final ResourceKey<SackType> GLOW_FLARE = of("glow_flare");
    public static final ResourceKey<SackType> SPECTRE_FLARE = of("spectre_flare");
    public static final ResourceKey<SackType> GLOW_INK_CLUMPS = of("glow_ink_clumps");
    // Storage Drawers Sack Types
    public static final ResourceKey<SackType> DRAWER_UPGRADE = of("drawer_upgrade");
    public static final ResourceKey<SackType> DRAWER_CONTROLLER = of("drawer_controller");
    public static final ResourceKey<SackType> KEY_BUTTON = of("key_button");
    // Tinkers Construct Sack Types
    public static final ResourceKey<SackType> GLOWBALL = of("glowball");
    public static final ResourceKey<SackType> SHURIKEN = of("shuriken");
    public static final ResourceKey<SackType> THROWING_AXE = of("throwing_axe");
    public static final ResourceKey<SackType> TINKERS_REINFORCEMENT = of("tinkers_reinforcement");
    public static final ResourceKey<SackType> PATTERN = of("pattern");
    public static final ResourceKey<SackType> REPAIR_KIT = of("repair_kit");
    public static final ResourceKey<SackType> TOOL_PART = of("tool_part");
    public static final ResourceKey<SackType> MODIFIER_CRYSTAL = of("modifier_crystal");
    public static final ResourceKey<SackType> COPPER_CAN = of("copper_can");
    public static final ResourceKey<SackType> SEARED_STONE = of("seared_stone");
    public static final ResourceKey<SackType> SCORCHED_STONE = of("scorched_stone");
    public static final ResourceKey<SackType> TANK = of("tank");
    public static final ResourceKey<SackType> CAST = of("cast");
    // Waystones Sack Types
    public static final ResourceKey<SackType> WAYSTONE = of("waystone");
    public static final ResourceKey<SackType> SHARESTONE = of("sharestone");
    public static final ResourceKey<SackType> PORTSTONE = of("portstone");
    public static final ResourceKey<SackType> WARP_PLATE = of("warp_plate");

    public static ResourceKey<SackType> of(String id) {
        ResourceKey<SackType> sackType = ResourceKey.create(InvExpRegistryKeys.SACK_TYPE, InvExpUtil.location(id));
        SACK_TYPES.add(sackType);
        return sackType;
    }

    public static TagKey<Item> getItemTag(ResourceKey<SackType> sackType) {
        return ItemTags.create(sackType.location().withPrefix("sack_type/"));
    }

    public static void bootstrap(BootstapContext<SackType> context) {
        for (ResourceKey<SackType> sackTypeKey : SACK_TYPES) {
            context.register(sackTypeKey, new SackType());
        }
    }
}

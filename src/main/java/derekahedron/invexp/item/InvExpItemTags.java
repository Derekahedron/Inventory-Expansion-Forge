package derekahedron.invexp.item;

import derekahedron.invexp.util.InvExpUtil;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class InvExpItemTags {
    public static final TagKey<Item> SACKS = of("sacks");
    public static final TagKey<Item> QUIVERS = of("quivers");
    public static final TagKey<Item> DYEABLE_BUNDLES = of("dyeable_bundles");

    /**
     * Creates a new <code>TagKey</code> for Inventory Expansion items.
     *
     * @param id a <code>String</code> to use as the tag name
     * @return the <code>TagKey</code> that was created
     */
    public static TagKey<Item> of(String id) {
        return ItemTags.create(InvExpUtil.location(id));
    }

    public static class SackType {
        // Vanilla Sack Types
        public static final TagKey<Item> WOOD = of("wood");
        public static final TagKey<Item> DOOR = of("door");
        public static final TagKey<Item> PRESSURE_PLATE = of("pressure_plate");
        public static final TagKey<Item> BUTTON = of("button");
        public static final TagKey<Item> STONE = of("stone");
        public static final TagKey<Item> BRICKS = of("bricks");
        public static final TagKey<Item> MUD_BRICKS = of("mud_bricks");
        public static final TagKey<Item> SANDSTONE = of("sandstone");
        public static final TagKey<Item> PRISMARINE = of("prismarine");
        public static final TagKey<Item> NETHER_BRICKS = of("nether_bricks");
        public static final TagKey<Item> PURPUR = of("purpur");
        public static final TagKey<Item> METAL_BLOCK = of("metal_block");
        public static final TagKey<Item> CRYSTAL_BLOCK = of("crystal_block");
        public static final TagKey<Item> CHAINS = of("chains");
        public static final TagKey<Item> WOOL = of("wool");
        public static final TagKey<Item> TERRACOTTA = of("terracotta");
        public static final TagKey<Item> CONCRETE = of("concrete");
        public static final TagKey<Item> CONCRETE_POWDER = of("concrete_powder");
        public static final TagKey<Item> GLASS = of("glass");
        public static final TagKey<Item> BED = of("bed");
        public static final TagKey<Item> CANDLE = of("candle");
        public static final TagKey<Item> BANNER = of("banner");
        public static final TagKey<Item> SOIL = of("soil");
        public static final TagKey<Item> ICE = of("ice");
        public static final TagKey<Item> SNOW = of("snow");
        public static final TagKey<Item> BONE_BLOCK = of("bone_block");
        public static final TagKey<Item> ORE = of("ore");
        public static final TagKey<Item> FUNGUS = of("fungus");
        public static final TagKey<Item> PLANT = of("plant");
        public static final TagKey<Item> BAMBOO = of("bamboo");
        public static final TagKey<Item> CHORUS_FRUIT = of("chorus_fruit");
        public static final TagKey<Item> EGG = of("egg");
        public static final TagKey<Item> WHEAT_SEEDS = of("wheat_seeds");
        public static final TagKey<Item> COCOA_BEANS = of("cocoa_beans");
        public static final TagKey<Item> PUMPKIN_SEEDS = of("pumpkin_seeds");
        public static final TagKey<Item> MELON_SEEDS = of("melon_seeds");
        public static final TagKey<Item> BEETROOT_SEEDS = of("beetroot_seeds");
        public static final TagKey<Item> TORCHFLOWER_SEEDS = of("torchflower_seeds");
        public static final TagKey<Item> PITCHER_POD = of("pitcher_pod");
        public static final TagKey<Item> GLOW_BERRIES = of("glow_berries");
        public static final TagKey<Item> SWEET_BERRIES = of("sweet_berries");
        public static final TagKey<Item> NETHER_WART = of("nether_wart");
        public static final TagKey<Item> SEA_CREATURE = of("sea_creature");
        public static final TagKey<Item> KELP = of("kelp");
        public static final TagKey<Item> CORAL = of("coral");
        public static final TagKey<Item> SPONGE = of("sponge");
        public static final TagKey<Item> MELON = of("melon");
        public static final TagKey<Item> PUMPKIN = of("pumpkin");
        public static final TagKey<Item> NEST = of("nest");
        public static final TagKey<Item> HONEY = of("honey");
        public static final TagKey<Item> FROGLIGHT = of("froglight");
        public static final TagKey<Item> SCULK = of("sculk");
        public static final TagKey<Item> COBWEB = of("cobweb");
        public static final TagKey<Item> BEDROCK = of("bedrock");
        public static final TagKey<Item> TORCH = of("torch");
        public static final TagKey<Item> LANTERN = of("lantern");
        public static final TagKey<Item> END_CRYSTAL = of("end_crystal");
        public static final TagKey<Item> BELL = of("bell");
        public static final TagKey<Item> SCAFFOLDING = of("scaffolding");
        public static final TagKey<Item> POT = of("pot");
        public static final TagKey<Item> ARMOR_STAND = of("armor_stand");
        public static final TagKey<Item> ITEM_FRAME = of("item_frame");
        public static final TagKey<Item> PAINTING = of("painting");
        public static final TagKey<Item> SIGN = of("sign");
        public static final TagKey<Item> HEAD = of("head");
        public static final TagKey<Item> INFESTED_STONE = of("infested_stone");
        public static final TagKey<Item> REDSTONE_COMPONENT = of("redstone_component");
        public static final TagKey<Item> RAIL = of("rail");
        public static final TagKey<Item> MINECART = of("minecart");
        public static final TagKey<Item> TNT = of("tnt");
        public static final TagKey<Item> BUCKET = of("bucket");
        public static final TagKey<Item> FIRE_CHARGE = of("fire_charge");
        public static final TagKey<Item> BONE_MEAL = of("bone_meal");
        public static final TagKey<Item> NAME_TAG = of("name_tag");
        public static final TagKey<Item> LEAD = of("lead");
        public static final TagKey<Item> COMPASS = of("compass");
        public static final TagKey<Item> CLOCK = of("clock");
        public static final TagKey<Item> MAP = of("map");
        public static final TagKey<Item> FIREWORK_ROCKET = of("firework_rocket");
        public static final TagKey<Item> SADDLE = of("saddle");
        public static final TagKey<Item> BOAT = of("boat");
        public static final TagKey<Item> GOAT_HORN = of("goat_horn");
        public static final TagKey<Item> MUSIC_DISC = of("music_disc");
        public static final TagKey<Item> TOTEM_OF_UNDYING = of("totem_of_undying");
        public static final TagKey<Item> ARROW = of("arrow");
        public static final TagKey<Item> FOOD = of("food");
        public static final TagKey<Item> CARROT = of("carrot");
        public static final TagKey<Item> POTATO = of("potato");
        public static final TagKey<Item> BEETROOT = of("beetroot");
        public static final TagKey<Item> RAW_FISH = of("raw_fish");
        public static final TagKey<Item> BOTTLE = of("bottle");
        public static final TagKey<Item> POTION = of("potion");
        public static final TagKey<Item> WHEAT = of("wheat");
        public static final TagKey<Item> CREATURE = of("creature");
        public static final TagKey<Item> HEART_OF_THE_SEA = of("heart_of_the_sea");
        public static final TagKey<Item> DYE = of("dye");
        public static final TagKey<Item> PAPER = of("paper");
        public static final TagKey<Item> BOOK = of("book");
        public static final TagKey<Item> FIREWORK_STAR = of("firework_star");
        public static final TagKey<Item> SUGAR = of("sugar");
        public static final TagKey<Item> BANNER_PATTERN = of("banner_pattern");
        public static final TagKey<Item> POTTERY_SHERD = of("pottery_sherd");
        public static final TagKey<Item> KEY = of("key");
        public static final TagKey<Item> SMITHING_TEMPLATE = of("smithing_template");
        public static final TagKey<Item> SPAWN_EGG = of("spawn_egg");
        public static final TagKey<Item> COMMAND_BLOCK = of("command_block");
        // Extra Sack Types
        public static final TagKey<Item> CABBAGE = of("cabbage");
        public static final TagKey<Item> CABBAGE_SEEDS = of("cabbage_seeds");
        public static final TagKey<Item> TOMATO = of("tomato");
        public static final TagKey<Item> TOMATO_SEEDS = of("tomato_seeds");
        public static final TagKey<Item> ONION = of("onion");
        public static final TagKey<Item> RICE = of("rice");
        public static final TagKey<Item> STRAW = of("straw");
        public static final TagKey<Item> ROPE = of("rope");
        public static final TagKey<Item> METAL_PLATE = of("metal_plate");
        public static final TagKey<Item> UNKNOWN = of("unknown");
        // Alex's Caves Sack Types
        public static final TagKey<Item> CAVE_TABLET = of("cave_tablet");
        public static final TagKey<Item> CAVE_CODEX = of("cave_codex");
        public static final TagKey<Item> TESLA_BULB = of("tesla_bulb");
        public static final TagKey<Item> OMINOUS_CATALYST = of("ominous_catalyst");
        public static final TagKey<Item> TOXIC_WASTE = of("toxic_waste");
        public static final TagKey<Item> NUCLEAR_BOMB = of("nuclear_bomb");
        public static final TagKey<Item> RADON_LAMP = of("radon_lamp");
        public static final TagKey<Item> FLOATER = of("floater");
        public static final TagKey<Item> INK_BOMB = of("ink_bomb");
        public static final TagKey<Item> DEPTH_CHARGE = of("depth_charge");
        public static final TagKey<Item> GUANO = of("guano");
        public static final TagKey<Item> FERTILIZER = of("fertilizer");
        public static final TagKey<Item> MOTH_BALL = of("moth_ball");
        public static final TagKey<Item> SWEETS = of("sweets");
        public static final TagKey<Item> JELLY_BEAN = of("jelly_bean");
        // Biomes O' Plenty Sack Types
        public static final TagKey<Item> BODY_PART = of("body_part");
        public static final TagKey<Item> WISPJELLY = of("wispjelly");
        public static final TagKey<Item> NULL = of("null");
        // Deeper and Darker Sack Types
        public static final TagKey<Item> SCULK_GRIME_BRICKS = of("sculk_grime_bricks");
        public static final TagKey<Item> BLOOM_BERRIES = of("bloom_berries");
        // Farmer's Delight Sack Types
        public static final TagKey<Item> RICE_PANICLE = of("rice_panicle");
        public static final TagKey<Item> ANIMAL_FOOD = of("animal_food");
        // Galosphere Sack Types
        public static final TagKey<Item> BAROMETER = of("barometer");
        public static final TagKey<Item> SILVER_BOMB = of("silver_bomb");
        public static final TagKey<Item> GLOW_FLARE = of("glow_flare");
        public static final TagKey<Item> SPECTRE_FLARE = of("spectre_flare");
        public static final TagKey<Item> GLOW_INK_CLUMPS = of("glow_ink_clumps");
        // Storage Drawers Sack Types
        public static final TagKey<Item> DRAWER_UPGRADE = of("drawer_upgrade");
        public static final TagKey<Item> DRAWER_CONTROLLER = of("drawer_controller");
        public static final TagKey<Item> KEY_BUTTON = of("key_button");
        // Tinkers Construct Sack Types
        public static final TagKey<Item> GLOWBALL = of("glowball");
        public static final TagKey<Item> EFLN = of("efln");
        public static final TagKey<Item> SHURIKEN = of("shuriken");
        public static final TagKey<Item> TINKERS_REINFORCEMENT = of("tinkers_reinforcement");
        public static final TagKey<Item> PATTERN = of("pattern");
        public static final TagKey<Item> REPAIR_KIT = of("repair_kit");
        public static final TagKey<Item> TOOL_PART = of("tool_part");
        public static final TagKey<Item> MODIFIER_CRYSTAL = of("modifier_crystal");
        public static final TagKey<Item> COPPER_CAN = of("copper_can");
        public static final TagKey<Item> SEARED_STONE = of("seared_stone");
        public static final TagKey<Item> SCORCHED_STONE = of("scorched_stone");
        public static final TagKey<Item> TANK = of("tank");
        public static final TagKey<Item> CAST = of("cast");
        // Waystones Sack Types
        public static final TagKey<Item> WAYSTONE = of("waystone");
        public static final TagKey<Item> SHARESTONE = of("sharestone");
        public static final TagKey<Item> PORTSTONE = of("portstone");
        public static final TagKey<Item> WARP_PLATE = of("warp_plate");

        /**
         * Creates a new <code>TagKey</code> for item tags that represent sack types.
         *
         * @param id a <code>String</code> to use as the tag name
         * @return the <code>TagKey</code> that was created
         */
        public static TagKey<Item> of(String id) {
            return ItemTags.create(InvExpUtil.location(id).withPrefix("sack_type/"));
        }
    }

    public static class SackWeight {
        public static final TagKey<Item> DOUBLE = of("double");
        public static final TagKey<Item> HALF = of("half");
        public static final TagKey<Item> THIRD = of("third");
        public static final TagKey<Item> FOURTH = of("fourth");
        public static final TagKey<Item> FIFTH = of("fifth");

        /**
         * Creates a new <code>TagKey</code> for item tags that represent sack weights.
         *
         * @param id a <code>String</code> to use as the tag name
         * @return the <code>TagKey</code> that was created
         */
        public static TagKey<Item> of(String id) {
            return ItemTags.create(InvExpUtil.location(id).withPrefix("sack_weight/"));
        }
    }
}

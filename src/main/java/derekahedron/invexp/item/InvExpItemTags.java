package derekahedron.invexp.item;

import derekahedron.invexp.sack.SackTypes;
import derekahedron.invexp.util.InvExpUtil;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class InvExpItemTags {
    public static final TagKey<Item> SACKS = of("sacks");
    public static final TagKey<Item> QUIVERS = of("quivers");
    public static final TagKey<Item> BUNDLES = of("bundles");
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
        public static final TagKey<Item> WOOD = SackTypes.getItemTag(SackTypes.WOOD);
        public static final TagKey<Item> DOOR = SackTypes.getItemTag(SackTypes.DOOR);
        public static final TagKey<Item> PRESSURE_PLATE = SackTypes.getItemTag(SackTypes.PRESSURE_PLATE);
        public static final TagKey<Item> BUTTON = SackTypes.getItemTag(SackTypes.BUTTON);
        public static final TagKey<Item> STONE = SackTypes.getItemTag(SackTypes.STONE);
        public static final TagKey<Item> BRICKS = SackTypes.getItemTag(SackTypes.BRICKS);
        public static final TagKey<Item> MUD_BRICKS = SackTypes.getItemTag(SackTypes.MUD_BRICKS);
        public static final TagKey<Item> SANDSTONE = SackTypes.getItemTag(SackTypes.SANDSTONE);
        public static final TagKey<Item> PRISMARINE = SackTypes.getItemTag(SackTypes.PRISMARINE);
        public static final TagKey<Item> NETHER_BRICKS = SackTypes.getItemTag(SackTypes.NETHER_BRICKS);
        public static final TagKey<Item> PURPUR = SackTypes.getItemTag(SackTypes.PURPUR);
        public static final TagKey<Item> METAL_BLOCK = SackTypes.getItemTag(SackTypes.METAL_BLOCK);
        public static final TagKey<Item> CRYSTAL_BLOCK = SackTypes.getItemTag(SackTypes.CRYSTAL_BLOCK);
        public static final TagKey<Item> CHAINS = SackTypes.getItemTag(SackTypes.CHAINS);
        public static final TagKey<Item> WOOL = SackTypes.getItemTag(SackTypes.WOOL);
        public static final TagKey<Item> TERRACOTTA = SackTypes.getItemTag(SackTypes.TERRACOTTA);
        public static final TagKey<Item> CONCRETE = SackTypes.getItemTag(SackTypes.CONCRETE);
        public static final TagKey<Item> CONCRETE_POWDER = SackTypes.getItemTag(SackTypes.CONCRETE_POWDER);
        public static final TagKey<Item> GLASS = SackTypes.getItemTag(SackTypes.GLASS);
        public static final TagKey<Item> BED = SackTypes.getItemTag(SackTypes.BED);
        public static final TagKey<Item> CANDLE = SackTypes.getItemTag(SackTypes.CANDLE);
        public static final TagKey<Item> BANNER = SackTypes.getItemTag(SackTypes.BANNER);
        public static final TagKey<Item> SOIL = SackTypes.getItemTag(SackTypes.SOIL);
        public static final TagKey<Item> ICE = SackTypes.getItemTag(SackTypes.ICE);
        public static final TagKey<Item> SNOW = SackTypes.getItemTag(SackTypes.SNOW);
        public static final TagKey<Item> BONE_BLOCK = SackTypes.getItemTag(SackTypes.BONE_BLOCK);
        public static final TagKey<Item> ORE = SackTypes.getItemTag(SackTypes.ORE);
        public static final TagKey<Item> FUNGUS = SackTypes.getItemTag(SackTypes.FUNGUS);
        public static final TagKey<Item> PLANT = SackTypes.getItemTag(SackTypes.PLANT);
        public static final TagKey<Item> BAMBOO = SackTypes.getItemTag(SackTypes.BAMBOO);
        public static final TagKey<Item> CHORUS_FRUIT = SackTypes.getItemTag(SackTypes.CHORUS_FRUIT);
        public static final TagKey<Item> EGG = SackTypes.getItemTag(SackTypes.EGG);
        public static final TagKey<Item> WHEAT_SEEDS = SackTypes.getItemTag(SackTypes.WHEAT_SEEDS);
        public static final TagKey<Item> COCOA_BEANS = SackTypes.getItemTag(SackTypes.COCOA_BEANS);
        public static final TagKey<Item> PUMPKIN_SEEDS = SackTypes.getItemTag(SackTypes.PUMPKIN_SEEDS);
        public static final TagKey<Item> MELON_SEEDS = SackTypes.getItemTag(SackTypes.MELON_SEEDS);
        public static final TagKey<Item> BEETROOT_SEEDS = SackTypes.getItemTag(SackTypes.BEETROOT_SEEDS);
        public static final TagKey<Item> TORCHFLOWER_SEEDS = SackTypes.getItemTag(SackTypes.TORCHFLOWER_SEEDS);
        public static final TagKey<Item> PITCHER_POD = SackTypes.getItemTag(SackTypes.PITCHER_POD);
        public static final TagKey<Item> GLOW_BERRIES = SackTypes.getItemTag(SackTypes.GLOW_BERRIES);
        public static final TagKey<Item> SWEET_BERRIES = SackTypes.getItemTag(SackTypes.SWEET_BERRIES);
        public static final TagKey<Item> NETHER_WART = SackTypes.getItemTag(SackTypes.NETHER_WART);
        public static final TagKey<Item> SEA_CREATURE = SackTypes.getItemTag(SackTypes.SEA_CREATURE);
        public static final TagKey<Item> KELP = SackTypes.getItemTag(SackTypes.KELP);
        public static final TagKey<Item> CORAL = SackTypes.getItemTag(SackTypes.CORAL);
        public static final TagKey<Item> SPONGE = SackTypes.getItemTag(SackTypes.SPONGE);
        public static final TagKey<Item> MELON = SackTypes.getItemTag(SackTypes.MELON);
        public static final TagKey<Item> PUMPKIN = SackTypes.getItemTag(SackTypes.PUMPKIN);
        public static final TagKey<Item> NEST = SackTypes.getItemTag(SackTypes.NEST);
        public static final TagKey<Item> HONEY = SackTypes.getItemTag(SackTypes.HONEY);
        public static final TagKey<Item> FROGLIGHT = SackTypes.getItemTag(SackTypes.FROGLIGHT);
        public static final TagKey<Item> SCULK = SackTypes.getItemTag(SackTypes.SCULK);
        public static final TagKey<Item> COBWEB = SackTypes.getItemTag(SackTypes.COBWEB);
        public static final TagKey<Item> BEDROCK = SackTypes.getItemTag(SackTypes.BEDROCK);
        public static final TagKey<Item> TORCH = SackTypes.getItemTag(SackTypes.TORCH);
        public static final TagKey<Item> LANTERN = SackTypes.getItemTag(SackTypes.LANTERN);
        public static final TagKey<Item> END_CRYSTAL = SackTypes.getItemTag(SackTypes.END_CRYSTAL);
        public static final TagKey<Item> BELL = SackTypes.getItemTag(SackTypes.BELL);
        public static final TagKey<Item> SCAFFOLDING = SackTypes.getItemTag(SackTypes.SCAFFOLDING);
        public static final TagKey<Item> POT = SackTypes.getItemTag(SackTypes.POT);
        public static final TagKey<Item> ARMOR_STAND = SackTypes.getItemTag(SackTypes.ARMOR_STAND);
        public static final TagKey<Item> ITEM_FRAME = SackTypes.getItemTag(SackTypes.ITEM_FRAME);
        public static final TagKey<Item> PAINTING = SackTypes.getItemTag(SackTypes.PAINTING);
        public static final TagKey<Item> SIGN = SackTypes.getItemTag(SackTypes.SIGN);
        public static final TagKey<Item> HEAD = SackTypes.getItemTag(SackTypes.HEAD);
        public static final TagKey<Item> INFESTED_STONE = SackTypes.getItemTag(SackTypes.INFESTED_STONE);
        public static final TagKey<Item> REDSTONE_COMPONENT = SackTypes.getItemTag(SackTypes.REDSTONE_COMPONENT);
        public static final TagKey<Item> RAIL = SackTypes.getItemTag(SackTypes.RAIL);
        public static final TagKey<Item> MINECART = SackTypes.getItemTag(SackTypes.MINECART);
        public static final TagKey<Item> TNT = SackTypes.getItemTag(SackTypes.TNT);
        public static final TagKey<Item> BUCKET = SackTypes.getItemTag(SackTypes.BUCKET);
        public static final TagKey<Item> FIRE_CHARGE = SackTypes.getItemTag(SackTypes.FIRE_CHARGE);
        public static final TagKey<Item> BONE_MEAL = SackTypes.getItemTag(SackTypes.BONE_MEAL);
        public static final TagKey<Item> NAME_TAG = SackTypes.getItemTag(SackTypes.NAME_TAG);
        public static final TagKey<Item> LEAD = SackTypes.getItemTag(SackTypes.LEAD);
        public static final TagKey<Item> COMPASS = SackTypes.getItemTag(SackTypes.COMPASS);
        public static final TagKey<Item> CLOCK = SackTypes.getItemTag(SackTypes.CLOCK);
        public static final TagKey<Item> MAP = SackTypes.getItemTag(SackTypes.MAP);
        public static final TagKey<Item> FIREWORK_ROCKET = SackTypes.getItemTag(SackTypes.FIREWORK_ROCKET);
        public static final TagKey<Item> SADDLE = SackTypes.getItemTag(SackTypes.SADDLE);
        public static final TagKey<Item> BOAT = SackTypes.getItemTag(SackTypes.BOAT);
        public static final TagKey<Item> GOAT_HORN = SackTypes.getItemTag(SackTypes.GOAT_HORN);
        public static final TagKey<Item> MUSIC_DISC = SackTypes.getItemTag(SackTypes.MUSIC_DISC);
        public static final TagKey<Item> TOTEM_OF_UNDYING = SackTypes.getItemTag(SackTypes.TOTEM_OF_UNDYING);
        public static final TagKey<Item> ARROW = SackTypes.getItemTag(SackTypes.ARROW);
        public static final TagKey<Item> FOOD = SackTypes.getItemTag(SackTypes.FOOD);
        public static final TagKey<Item> CARROT = SackTypes.getItemTag(SackTypes.CARROT);
        public static final TagKey<Item> POTATO = SackTypes.getItemTag(SackTypes.POTATO);
        public static final TagKey<Item> BEETROOT = SackTypes.getItemTag(SackTypes.BEETROOT);
        public static final TagKey<Item> RAW_FISH = SackTypes.getItemTag(SackTypes.RAW_FISH);
        public static final TagKey<Item> BOTTLE = SackTypes.getItemTag(SackTypes.BOTTLE);
        public static final TagKey<Item> POTION = SackTypes.getItemTag(SackTypes.POTION);
        public static final TagKey<Item> WHEAT = SackTypes.getItemTag(SackTypes.WHEAT);
        public static final TagKey<Item> CREATURE = SackTypes.getItemTag(SackTypes.CREATURE);
        public static final TagKey<Item> HEART_OF_THE_SEA = SackTypes.getItemTag(SackTypes.HEART_OF_THE_SEA);
        public static final TagKey<Item> DYE = SackTypes.getItemTag(SackTypes.DYE);
        public static final TagKey<Item> PAPER = SackTypes.getItemTag(SackTypes.PAPER);
        public static final TagKey<Item> BOOK = SackTypes.getItemTag(SackTypes.BOOK);
        public static final TagKey<Item> FIREWORK_STAR = SackTypes.getItemTag(SackTypes.FIREWORK_STAR);
        public static final TagKey<Item> SUGAR = SackTypes.getItemTag(SackTypes.SUGAR);
        public static final TagKey<Item> BANNER_PATTERN = SackTypes.getItemTag(SackTypes.BANNER_PATTERN);
        public static final TagKey<Item> POTTERY_SHERD = SackTypes.getItemTag(SackTypes.POTTERY_SHERD);
        public static final TagKey<Item> KEY = SackTypes.getItemTag(SackTypes.KEY);
        public static final TagKey<Item> SMITHING_TEMPLATE = SackTypes.getItemTag(SackTypes.SMITHING_TEMPLATE);
        public static final TagKey<Item> SPAWN_EGG = SackTypes.getItemTag(SackTypes.SPAWN_EGG);
        public static final TagKey<Item> COMMAND_BLOCK = SackTypes.getItemTag(SackTypes.COMMAND_BLOCK);
        // Extra Sack Types
        public static final TagKey<Item> CABBAGE = SackTypes.getItemTag(SackTypes.CABBAGE);
        public static final TagKey<Item> CABBAGE_SEEDS = SackTypes.getItemTag(SackTypes.CABBAGE_SEEDS);
        public static final TagKey<Item> TOMATO = SackTypes.getItemTag(SackTypes.TOMATO);
        public static final TagKey<Item> TOMATO_SEEDS = SackTypes.getItemTag(SackTypes.TOMATO_SEEDS);
        public static final TagKey<Item> CANTALOUPE = SackTypes.getItemTag(SackTypes.CANTALOUPE);
        public static final TagKey<Item> CANTALOUPE_SEEDS = SackTypes.getItemTag(SackTypes.CANTALOUPE_SEEDS);
        public static final TagKey<Item> SALMONBERRIES = SackTypes.getItemTag(SackTypes.SALMONBERRIES);
        public static final TagKey<Item> SALMONBERRY_SEEDS = SackTypes.getItemTag(SackTypes.SALMONBERRY_SEEDS);
        public static final TagKey<Item> ONION = SackTypes.getItemTag(SackTypes.ONION);
        public static final TagKey<Item> RICE = SackTypes.getItemTag(SackTypes.RICE);
        public static final TagKey<Item> STRAW = SackTypes.getItemTag(SackTypes.STRAW);
        public static final TagKey<Item> ROPE = SackTypes.getItemTag(SackTypes.ROPE);
        public static final TagKey<Item> METAL_PLATE = SackTypes.getItemTag(SackTypes.METAL_PLATE);
        public static final TagKey<Item> TROPHY = SackTypes.getItemTag(SackTypes.TROPHY);
        public static final TagKey<Item> UNKNOWN = SackTypes.getItemTag(SackTypes.UNKNOWN);
        // Alex's Caves Sack Types
        public static final TagKey<Item> CAVE_TABLET = SackTypes.getItemTag(SackTypes.CAVE_TABLET);
        public static final TagKey<Item> CAVE_CODEX = SackTypes.getItemTag(SackTypes.CAVE_CODEX);
        public static final TagKey<Item> TESLA_BULB = SackTypes.getItemTag(SackTypes.TESLA_BULB);
        public static final TagKey<Item> OMINOUS_CATALYST = SackTypes.getItemTag(SackTypes.OMINOUS_CATALYST);
        public static final TagKey<Item> TOXIC_WASTE = SackTypes.getItemTag(SackTypes.TOXIC_WASTE);
        public static final TagKey<Item> NUCLEAR_BOMB = SackTypes.getItemTag(SackTypes.NUCLEAR_BOMB);
        public static final TagKey<Item> RADON_LAMP = SackTypes.getItemTag(SackTypes.RADON_LAMP);
        public static final TagKey<Item> FLOATER = SackTypes.getItemTag(SackTypes.FLOATER);
        public static final TagKey<Item> INK_BOMB = SackTypes.getItemTag(SackTypes.INK_BOMB);
        public static final TagKey<Item> DEPTH_CHARGE = SackTypes.getItemTag(SackTypes.DEPTH_CHARGE);
        public static final TagKey<Item> GUANO = SackTypes.getItemTag(SackTypes.GUANO);
        public static final TagKey<Item> FERTILIZER = SackTypes.getItemTag(SackTypes.FERTILIZER);
        public static final TagKey<Item> MOTH_BALL = SackTypes.getItemTag(SackTypes.MOTH_BALL);
        public static final TagKey<Item> SWEETS = SackTypes.getItemTag(SackTypes.SWEETS);
        public static final TagKey<Item> JELLY_BEAN = SackTypes.getItemTag(SackTypes.JELLY_BEAN);
        // Apothesis Sack Types
        public static final TagKey<Item> APOTHEOSIS_GEM = SackTypes.getItemTag(SackTypes.APOTHEOSIS_GEM);
        // Biomes O' Plenty Sack Types
        public static final TagKey<Item> BODY_PART = SackTypes.getItemTag(SackTypes.BODY_PART);
        public static final TagKey<Item> WISPJELLY = SackTypes.getItemTag(SackTypes.WISPJELLY);
        public static final TagKey<Item> NULL = SackTypes.getItemTag(SackTypes.NULL);
        // Blue Skies Sack Types
        public static final TagKey<Item> PINE_FRUIT_SEEDS = SackTypes.getItemTag(SackTypes.PINE_FRUIT_SEEDS);
        public static final TagKey<Item> PINE_FRUIT = SackTypes.getItemTag(SackTypes.PINE_FRUIT);
        public static final TagKey<Item> WINTER_LEAF_SEEDS = SackTypes.getItemTag(SackTypes.WINTER_LEAF_SEEDS);
        public static final TagKey<Item> WINTER_LEAVES = SackTypes.getItemTag(SackTypes.WINTER_LEAVES);
        public static final TagKey<Item> SCALEFRUIT_SEEDS = SackTypes.getItemTag(SackTypes.SCALEFRUIT_SEEDS);
        public static final TagKey<Item> SCALEFRUIT = SackTypes.getItemTag(SackTypes.SCALEFRUIT);
        public static final TagKey<Item> FIERY_BEAN_SEEDS = SackTypes.getItemTag(SackTypes.FIERY_BEAN_SEEDS);
        public static final TagKey<Item> FIERY_BEANS = SackTypes.getItemTag(SackTypes.FIERY_BEANS);
        public static final TagKey<Item> CRYO_ROOT = SackTypes.getItemTag(SackTypes.CRYO_ROOT);
        public static final TagKey<Item> SOLNUT = SackTypes.getItemTag(SackTypes.SOLNUT);
        public static final TagKey<Item> WARDING_PEARL = SackTypes.getItemTag(SackTypes.WARDING_PEARL);
        // Deeper and Darker Sack Types
        public static final TagKey<Item> SCULK_GRIME_BRICKS = SackTypes.getItemTag(SackTypes.SCULK_GRIME_BRICKS);
        public static final TagKey<Item> BLOOM_BERRIES = SackTypes.getItemTag(SackTypes.BLOOM_BERRIES);
        // Elevators Sack Types
        public static final TagKey<Item> ELEVATOR = SackTypes.getItemTag(SackTypes.ELEVATOR);
        // EnderIO Sack Types
        public static final TagKey<Item> BROKEN_SPAWNER = SackTypes.getItemTag(SackTypes.BROKEN_SPAWNER);
        // Farmer's Delight Sack Types
        public static final TagKey<Item> RICE_PANICLE = SackTypes.getItemTag(SackTypes.RICE_PANICLE);
        public static final TagKey<Item> ANIMAL_FOOD = SackTypes.getItemTag(SackTypes.ANIMAL_FOOD);
        // Galosphere Sack Types
        public static final TagKey<Item> BAROMETER = SackTypes.getItemTag(SackTypes.BAROMETER);
        public static final TagKey<Item> SILVER_BOMB = SackTypes.getItemTag(SackTypes.SILVER_BOMB);
        public static final TagKey<Item> GLOW_FLARE = SackTypes.getItemTag(SackTypes.GLOW_FLARE);
        public static final TagKey<Item> SPECTRE_FLARE = SackTypes.getItemTag(SackTypes.SPECTRE_FLARE);
        public static final TagKey<Item> GLOW_INK_CLUMPS = SackTypes.getItemTag(SackTypes.GLOW_INK_CLUMPS);
        // Storage Drawers Sack Types
        public static final TagKey<Item> DRAWER_UPGRADE = SackTypes.getItemTag(SackTypes.DRAWER_UPGRADE);
        public static final TagKey<Item> DRAWER_CONTROLLER = SackTypes.getItemTag(SackTypes.DRAWER_CONTROLLER);
        public static final TagKey<Item> KEY_BUTTON = SackTypes.getItemTag(SackTypes.KEY_BUTTON);
        // Tinkers Construct Sack Types
        public static final TagKey<Item> GLOWBALL = SackTypes.getItemTag(SackTypes.GLOWBALL);
        public static final TagKey<Item> SHURIKEN = SackTypes.getItemTag(SackTypes.SHURIKEN);
        public static final TagKey<Item> THROWING_AXE = SackTypes.getItemTag(SackTypes.THROWING_AXE);
        public static final TagKey<Item> TINKERS_REINFORCEMENT = SackTypes.getItemTag(SackTypes.TINKERS_REINFORCEMENT);
        public static final TagKey<Item> PATTERN = SackTypes.getItemTag(SackTypes.PATTERN);
        public static final TagKey<Item> REPAIR_KIT = SackTypes.getItemTag(SackTypes.REPAIR_KIT);
        public static final TagKey<Item> TOOL_PART = SackTypes.getItemTag(SackTypes.TOOL_PART);
        public static final TagKey<Item> MODIFIER_CRYSTAL = SackTypes.getItemTag(SackTypes.MODIFIER_CRYSTAL);
        public static final TagKey<Item> COPPER_CAN = SackTypes.getItemTag(SackTypes.COPPER_CAN);
        public static final TagKey<Item> SEARED_STONE = SackTypes.getItemTag(SackTypes.SEARED_STONE);
        public static final TagKey<Item> SCORCHED_STONE = SackTypes.getItemTag(SackTypes.SCORCHED_STONE);
        public static final TagKey<Item> TANK = SackTypes.getItemTag(SackTypes.TANK);
        public static final TagKey<Item> CAST = SackTypes.getItemTag(SackTypes.CAST);
        // Waystones Sack Types
        public static final TagKey<Item> WAYSTONE = SackTypes.getItemTag(SackTypes.WAYSTONE);
        public static final TagKey<Item> SHARESTONE = SackTypes.getItemTag(SackTypes.SHARESTONE);
        public static final TagKey<Item> PORTSTONE = SackTypes.getItemTag(SackTypes.PORTSTONE);
        public static final TagKey<Item> WARP_PLATE = SackTypes.getItemTag(SackTypes.WARP_PLATE);
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

package derekahedron.invexp;

import derekahedron.invexp.sack.SacksHelper;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = InventoryExpansion.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec.IntValue MAX_SACK_TYPES = SERVER_BUILDER
            .comment("How many different sack types can one sack hold")
            .defineInRange("maxSackTypes", 1, 0, Integer.MAX_VALUE);

    public static final ForgeConfigSpec.IntValue MAX_SACK_WEIGHT_STACKS = SERVER_BUILDER
            .comment("How many stacks a sack can hold by weight")
            .defineInRange("maxSackWeightStacks", 4, 0, Integer.MAX_VALUE);

    public static final ForgeConfigSpec.IntValue MAX_SACK_STACKS = SERVER_BUILDER
            .comment("How many separate stacks can be held in the sack")
            .defineInRange("maxSackStacks", 64, 0, Integer.MAX_VALUE);

    public static final ForgeConfigSpec.IntValue MAX_QUIVER_OCCUPANCY_STACKS = SERVER_BUILDER
            .comment("How many mixed stacks of arrows the quiver can hold")
            .defineInRange("maxQuiverOccupancyStacks", 8, 0, Integer.MAX_VALUE);

    public static final ForgeConfigSpec.IntValue MAX_QUIVER_STACKS = SERVER_BUILDER
            .comment("How many separate stacks can be held in the quiver")
            .defineInRange("maxQuiverStacks", 64, 0, Integer.MAX_VALUE);

    static final ForgeConfigSpec SERVER_SPEC = SERVER_BUILDER.build();

}

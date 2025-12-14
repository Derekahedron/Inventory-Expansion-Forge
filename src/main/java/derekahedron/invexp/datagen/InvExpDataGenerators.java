package derekahedron.invexp.datagen;

import derekahedron.invexp.InventoryExpansion;
import derekahedron.invexp.registry.InvExpRegistryKeys;
import derekahedron.invexp.sack.SackTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = InventoryExpansion.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class InvExpDataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        generator.addProvider(event.includeServer(), new InvExpRecipeProvider(
                output));

        generator.addProvider(event.includeServer(), new InvExpItemModelProvider(
                output,
                existingFileHelper));

        generator.addProvider(event.includeServer(), new InvExpItemTagProvider(
                output,
                lookupProvider,
                existingFileHelper));

        RegistrySetBuilder registrySetBuilder = new RegistrySetBuilder();
        registrySetBuilder.add(InvExpRegistryKeys.SACK_TYPE, SackTypes::bootstrap);
        registrySetBuilder.add(InvExpRegistryKeys.SACK_TYPE_DEFAULT, SackTypeDefaultProvider::bootstrap);
        registrySetBuilder.add(InvExpRegistryKeys.SACK_WEIGHT_DEFAULT, SackWeightDefaultProvider::bootstrap);
        generator.addProvider(event.includeServer(), new DatapackBuiltinEntriesProvider(
                output,
                lookupProvider,
                registrySetBuilder,
                Set.of(InventoryExpansion.MOD_ID)));
    }
}

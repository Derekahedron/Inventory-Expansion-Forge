package derekahedron.invexp.sack;

import derekahedron.invexp.InventoryExpansion;
import derekahedron.invexp.item.ItemDuck;
import derekahedron.invexp.registry.InvExpRegistryKeys;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.material.Fluids;
import org.apache.commons.lang3.math.Fraction;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SackDefaultManager {
    @Nullable
    private static SackDefaultManager INSTANCE;
    private int syncId;
    private final List<Holder.Reference<SackTypeDefault>> typeDefaults;
    private final List<Holder.Reference<SackTypeDefault>> globalTypeDefaults;
    private final List<Holder.Reference<SackWeightDefault>> weightDefaults;
    private final List<Holder.Reference<SackWeightDefault>> globalWeightDefaults;

    public SackDefaultManager(RegistryAccess registryAccess) {
        syncId = INSTANCE != null ? INSTANCE.syncId: 0;

        ArrayList<Holder.Reference<SackTypeDefault>> types =
                new ArrayList<>(registryAccess.registryOrThrow(InvExpRegistryKeys.SACK_TYPE_DEFAULT).holders()
                        .sorted(SackDefaultManager::compareTypes)
                        .toList());
        Collections.reverse(types);

        ArrayList<Holder.Reference<SackWeightDefault>> weights =
                new ArrayList<>(registryAccess.registryOrThrow(InvExpRegistryKeys.SACK_WEIGHT_DEFAULT).holders()
                        .sorted(SackDefaultManager::compareWeights)
                        .toList());
        Collections.reverse(weights);

        typeDefaults = types.stream().filter((type) -> type.value().items().isPresent()).toList();
        globalTypeDefaults = types.stream().filter((type) -> type.value().items().isEmpty()).toList();
        weightDefaults = weights.stream().filter((weight) -> weight.value().items().isPresent()).toList();
        globalWeightDefaults = weights.stream().filter((weight) -> weight.value().items().isEmpty()).toList();

        updateSackDefaults();
    }

    public void updateSackDefaults() {
        syncId++;

        for (Holder.Reference<SackTypeDefault> typeDefault : typeDefaults) {
            typeDefault.value().items().ifPresent((items) -> {
                for (ItemStack itemStack : items.getItems()) {
                    getOrCreateSackDefaults(itemStack.getItem()).typeDefaults.add(typeDefault);
                }
            });
        }

        for (Holder.Reference<SackWeightDefault> weightDefault : weightDefaults) {
            weightDefault.value().items().ifPresent((items) -> {
                for (ItemStack itemStack : items.getItems()) {
                    getOrCreateSackDefaults(itemStack.getItem()).weightDefaults.add(weightDefault);
                }
            });
        }
    }

    @Nullable
    public ResourceKey<SackType> getType(ItemStack stack) {
        SackDefaults defaults = getSackDefaults(stack.getItem());
        List<Holder.Reference<SackTypeDefault>> itemTypeDefaults = defaults != null
                ? defaults.typeDefaults : List.of();

        int i = 0;
        int j = 0;
        while (i < globalTypeDefaults.size() || j < itemTypeDefaults.size()) {
            SackTypeDefault typeDefault;
            if (i < globalTypeDefaults.size()
                    && (j >= itemTypeDefaults.size()
                    || compareTypes(globalTypeDefaults.get(i), itemTypeDefaults.get(j)) > 0)) {
                typeDefault = globalTypeDefaults.get(i).value();
                i++;
            } else {
                typeDefault = itemTypeDefaults.get(j).value();
                j++;
            }
            if (typeDefault.test(stack)) {
                return typeDefault.sackType().isPresent()
                        ? typeDefault.sackType().get().unwrapKey().orElse(null)
                        : null;
            }
        }

        if (stack.getItem() instanceof SpawnEggItem) {
            return SackTypes.SPAWN_EGG;
        } else if (stack.getItem() instanceof BucketItem) {
            return SackTypes.BUCKET;
        }
        return null;
    }

    public Fraction getWeight(ItemStack stack) {
        SackDefaults defaults = getSackDefaults(stack.getItem());
        List<Holder.Reference<SackWeightDefault>> itemWeightDefaults = defaults != null
                ? defaults.weightDefaults : List.of();

        int i = 0;
        int j = 0;
        while (i < globalWeightDefaults.size() || j < itemWeightDefaults.size()) {
            SackWeightDefault weightDefault;
            if (i < globalWeightDefaults.size()
                    && (j >= itemWeightDefaults.size()
                    || compareWeights(globalWeightDefaults.get(i), itemWeightDefaults.get(j)) > 0)) {
                weightDefault = globalWeightDefaults.get(i).value();
                i++;
            } else {
                weightDefault = itemWeightDefaults.get(j).value();
                j++;
            }
            if (weightDefault.test(stack)) {
                return weightDefault.sackWeight().orElse(SacksHelper.DEFAULT_SACK_WEIGHT);
            }
        }

        if (stack.getItem() instanceof BucketItem bucketItem && !bucketItem.getFluid().isSame(Fluids.EMPTY)) {
            return Fraction.ONE_QUARTER;
        }
        return SacksHelper.DEFAULT_SACK_WEIGHT;
    }

    @Nullable
    public SackDefaults getSackDefaults(Item item) {
        SackDefaults defaults = ((ItemDuck) item).invexp$getSackDefaults();
        return defaults != null && defaults.syncId == syncId ? defaults : null;
    }

    public SackDefaults getOrCreateSackDefaults(Item item) {
        SackDefaults defaults = getSackDefaults(item);
        if (defaults == null) {
            defaults = new SackDefaults(new ArrayList<>(), new ArrayList<>(), syncId);
            ((ItemDuck) item).invexp$setSackDefaults(defaults);
        }
        return defaults;
    }

    /**
     * Gets the static manager instance.
     *
     * @return  static instance of the SackInsertableManager
     */
    @Nullable
    public static SackDefaultManager getInstance() {
        return INSTANCE;
    }

    public static void updateInstanceSackDefaults() {
        if (INSTANCE != null) {
            INSTANCE.updateSackDefaults();
        } else {
            InventoryExpansion.LOGGER.warn("Sack Defaults were updated before insertable manager instance created!");
        }
    }

    /**
     * Sets the static manager instance.
     *
     * @param instance  instance to set the static manager to
     */
    private static void setInstance(@Nullable SackDefaultManager instance) {
        INSTANCE = instance;
    }

    public static void createNewInstance(RegistryAccess registryAccess) {
        setInstance(new SackDefaultManager(registryAccess));
    }

    public record SackDefaults(
            List<Holder.Reference<SackTypeDefault>> typeDefaults,
            List<Holder.Reference<SackWeightDefault>> weightDefaults,
            int syncId) {}

    private static int compareTypes(
            Holder.Reference<SackTypeDefault> left,
            Holder.Reference<SackTypeDefault> right) {
        int compareResult = left.value().compareTo(right.value());
        return compareResult != 0
                ? compareResult
                : left.key().location().compareTo(right.key().location());
    }

    private static int compareWeights(
            Holder.Reference<SackWeightDefault> left,
            Holder.Reference<SackWeightDefault> right) {
        int compareResult = left.value().compareTo(right.value());
        return compareResult != 0
                ? compareResult
                : left.key().location().compareTo(right.key().location());
    }
}

package derekahedron.invexp.sack;

import derekahedron.invexp.InventoryExpansion;
import derekahedron.invexp.item.ItemDuck;
import derekahedron.invexp.registry.InvExpRegistryKeys;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITagManager;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Static manager for getting sack types and sack weights related to items. This is static so
 * we can easily grab the sack insertable without needing a world. The manager instance is created
 * when DataPacks are initialized. It uses Decentralized References that can be used on both client and
 * server instances.
 */
public class SackDataManager {
    private static SackDataManager INSTANCE;
    private final Map<String, SackType> sackTypes;
    private final Map<Item, SackData> explicitSackData;
    private final Map<TagKey<Item>, TaggedSackData> taggedSackData;
    private int syncId;

    /**
     * Class Constructor.
     *
     * @param registryAccess RegistryAccess to create new manager from
     */
    private SackDataManager(@NotNull RegistryAccess registryAccess) {
        syncId = INSTANCE != null ? INSTANCE.syncId: 0;
        explicitSackData = new HashMap<>();
        taggedSackData = new HashMap<>();
        sackTypes = new HashMap<>();

        HolderLookup.RegistryLookup<SackType> typeRegistry = registryAccess.lookupOrThrow(InvExpRegistryKeys.SACK_TYPE);
        HolderLookup.RegistryLookup<SackData> explicitRegistry = registryAccess.lookupOrThrow(InvExpRegistryKeys.SACK_INSERTABLE);
        HolderLookup.RegistryLookup<TaggedSackData> taggedRegistry = registryAccess.lookupOrThrow(InvExpRegistryKeys.TAGGED_SACK_INSERTABLE);

        // Store sack types
        for (Holder.Reference<SackType> entry : typeRegistry.listElements().toList()) {
            sackTypes.put(entry.key().location().toString(), entry.value());
        }

        // Create map with explicitly defined sack data
        for (Holder.Reference<SackData> entry : explicitRegistry.listElements().toList()) {
            ResourceLocation location = entry.key().location();
            SackData data = entry.value();

            // Check if sack data is valid
            if (data.sackType().isPresent() && !sackTypes.containsKey(data.sackType().get())) {
                InventoryExpansion.LOGGER.warn(
                        "SackData at {} has invalid sack type: {}", location, data.sackType().get()
                );
                continue;
            } else if (data.sackWeight().orElse(SacksHelper.DEFAULT_SACK_WEIGHT) < 0) {
                InventoryExpansion.LOGGER.warn(
                        "SackData at {} has invalid sack weight: {}", location, data.sackType().get()
                );
                continue;
            }

            ForgeRegistries.ITEMS.getHolder(location).ifPresent((holder) ->
                    explicitSackData.put(holder.get(), data));
        }

        // Create map with tagged sack data
        for (Holder.Reference<TaggedSackData> entry : taggedRegistry.listElements().toList()) {
            ResourceLocation location = entry.key().location();
            TaggedSackData data = entry.value();

            // Check if sack data is valid
            if (data.sackType().isPresent() && !sackTypes.containsKey(data.sackType().get())) {
                InventoryExpansion.LOGGER.warn(
                        "TaggedSackData at {} has invalid sack type: {}", location, data.sackType().get()
                );
                continue;
            } else if (data.sackWeight().orElse(SacksHelper.DEFAULT_SACK_WEIGHT) < 0) {
                InventoryExpansion.LOGGER.warn(
                        "TaggedSackData at {} has invalid sack weight: {}", location, data.sackType().get()
                );
                continue;
            }

            taggedSackData.put(TagKey.create(Registries.ITEM, location), entry.value());
        }

        // Initial calculation of sack data
        updateDefaultSackData();
    }

    /**
     * Resets and recomputes default sack data for all items defined when this was created.
     */
    public void updateDefaultSackData() {
        syncId++;

        // Create default data for each explicitly defined item
        explicitSackData.forEach((item, data) -> ((ItemDuck) item).invexp$setDefaultSackData(
                new DefaultSackData(
                        data.sackType().orElse(null),
                        data.sackWeight().orElse(SacksHelper.DEFAULT_SACK_WEIGHT),
                        syncId
                )
        ));

        ITagManager<Item> tags = ForgeRegistries.ITEMS.tags();

        if (tags != null) {
            // Create temporary maps for tracking the type and weight with the highest priority
            HashMap<Item, PrioritizedSackType> typeMap = new HashMap<>();
            HashMap<Item, PrioritizedSackWeight> weightMap = new HashMap<>();

            taggedSackData.forEach((tagKey, data) -> {

                if (!tags.isKnownTagName(tagKey)) {
                    return;
                }
                tags.getTag(tagKey).forEach((item -> {

                    // Early return if the item is already defined explicitly
                    if (getDefaultSackData(item) != null) {
                        return;
                    }

                    // Track type and weight, only overriding if the new type and weight have a higher priority
                    data.sackType().ifPresent(sackType -> {
                        if (!typeMap.containsKey(item)
                                || typeMap.get(item).overriddenBy(tagKey.location(), data)) {
                            typeMap.put(item, new PrioritizedSackType(
                                    tagKey.location(), data.priority(), sackType
                            ));
                        }
                    });

                    data.sackWeight().ifPresent(sackWeight -> {
                        if (!weightMap.containsKey(item)
                                || weightMap.get(item).overriddenBy(tagKey.location(), data)) {
                            weightMap.put(item, new PrioritizedSackWeight(
                                    tagKey.location(), data.priority(), sackWeight
                            ));
                        }
                    });
                }));
            });

            // Create data for all items that have a type defined
            typeMap.forEach((item, sackType) -> {
                // If a weight was defined, use that too
                int sackWeight;
                if (weightMap.containsKey(item)) {
                    sackWeight = weightMap.get(item).sackWeight;
                } else {
                    sackWeight = SacksHelper.DEFAULT_SACK_WEIGHT;
                }
                ((ItemDuck) item).invexp$setDefaultSackData(new DefaultSackData(
                        sackType.sackType, sackWeight, syncId
                ));
            });
        }
    }

    /**
     * Gets the default sack data stored on the item. If the data isn't in sync,
     * pretend it does not exist.
     *
     * @param item item to get the default data of
     * @return default sack data for the item; <code>null</code> if it does not exist
     */
    public DefaultSackData getDefaultSackData(@NotNull Item item) {
        DefaultSackData data = ((ItemDuck) item).invexp$getDefaultSackData();
        if (data != null && data.syncId == syncId) {
            return data;
        } else {
            return null;
        }
    }

    /**
     * Gets the translatable component for the name of a given sack type.
     *
     * @param sackType String representing the sack type
     * @return Component for the display name of the sack; <code>null</code> if the sack does not exist
     */
    public Component getSackName(String sackType) {
        if (!sackTypes.containsKey(sackType)) {
            return null;
        }
        Optional<String> name = sackTypes.get(sackType).name();
        if (name.isPresent()) {
            return Component.translatable(name.get());
        }
        ResourceLocation location = ResourceLocation.parse(sackType);
        return Component.translatable("sack_type." + location.getNamespace() + "." + location.getPath());
    }

    /**
     * Gets the static manager instance.
     *
     * @return static instance of the SackDataManager
     */
    public static SackDataManager getInstance() {
        return INSTANCE;
    }

    /**
     * Recomputes all default sack data using data defined in the current instance.
     */
    public static void updateInstanceTaggedData() {
        if (INSTANCE != null) {
            INSTANCE.updateDefaultSackData();
        }
        else {
            InventoryExpansion.LOGGER.warn("Tagged sack data was updated before insertable manager instance created!");
        }
    }

    /**
     * Creates a new static manager instance from the given registries.
     *
     * @param registryAccess RegistryAccess to use to compute default sack data from
     */
    public static void createNewInstance(@NotNull RegistryAccess registryAccess) {
        INSTANCE = new SackDataManager(registryAccess);
    }

    /**
     * Record holding information to track the sack type when constructing default sack data from tags.
     *
     * @param tag location of the tag
     * @param priority priority of this sack type
     * @param sackType String representing the tracked sack type
     */
    private record PrioritizedSackType(ResourceLocation tag, int priority, String sackType) {

        /**
         * Checks if another TaggedSackData should override this sack type.
         * If priorities are the same, check alphabetically to ensure the same result when re-comparing.
         *
         * @param tag ResourceLocation of the other tag to compare to
         * @param data TaggedSackData to compare
         * @return <code>true</code> if the other data has priority over this; <code>false</code> otherwise
         */
        public boolean overriddenBy(ResourceLocation tag, TaggedSackData data) {
            return priority < data.priority() || this.tag.compareTo(tag) < 0;
        }
    }

    /**
     * Record holding information to track the sack weight when constructing default sack data from tags.
     *
     * @param tag location of the tag
     * @param priority priority of this sack weight
     * @param sackWeight tracked sack weight
     */
    private record PrioritizedSackWeight(ResourceLocation tag, int priority, int sackWeight) {

        /**
         * Checks if another TaggedSackData should override this sack weight.
         * If priorities are the same, check alphabetically to ensure the same result when re-comparing.
         *
         * @param tag ResourceLocation of the other tag to compare to
         * @param data TaggedSackData to compare
         * @return <code>true</code> if the other data has priority over this; <code>false</code> otherwise
         */
        public boolean overriddenBy(ResourceLocation tag, TaggedSackData data) {
            return priority < data.priority() || (priority == data.priority() && this.tag.compareTo(tag) < 0);
        }
    }

    /**
     * Record holding the default sack type and weight, as well as a syncId associated with a SackDataManager.
     * Stored on items and allows for instant fetching of sack data.
     *
     * @param sackType String representing the sack type of the Item
     * @param sackWeight int representing the sack weight of the Item
     * @param syncId syncId of the SackDataManager this belongs to
     */
    public record DefaultSackData(String sackType, int sackWeight, int syncId) {}
}

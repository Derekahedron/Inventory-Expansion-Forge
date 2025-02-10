package derekahedron.invexp.recipe;

import derekahedron.invexp.bundle.BundleContents;
import derekahedron.invexp.bundle.BundleContentsComponent;
import derekahedron.invexp.item.InvExpItems;
import derekahedron.invexp.tags.InvExpItemTags;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class DyeBundleRecipe extends CustomRecipe {
    public static final Map<Item, Supplier<Item>> DYED_BUNDLES;

    public DyeBundleRecipe(ResourceLocation location, CraftingBookCategory category) {
        super(location, category);
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean matches(CraftingContainer craftingContainer, Level level) {
        ItemStack baseBundle = null;
        Item resultItem = null;
        ItemStack dye = null;

        for (int i = 0; i < craftingContainer.getContainerSize(); i++) {
            ItemStack stack = craftingContainer.getItem(i);
            if (stack.isEmpty()) {
                continue;
            }

            if (stack.is(InvExpItemTags.DYEABLE_BUNDLES)) {
                if (baseBundle != null) {
                    return false;
                }
                baseBundle = stack;
                if (dye != null && baseBundle.is(resultItem)) {
                    return false;
                }
            }
            else if (DYED_BUNDLES.containsKey(stack.getItem())) {
                if (dye != null) {
                    return false;
                }
                dye = stack;
                resultItem = DYED_BUNDLES.get(dye.getItem()).get();
                if (baseBundle != null && baseBundle.is(resultItem)) {
                    return false;
                }
            }
            else {
                return false;
            }
        }
        return baseBundle != null && dye != null;
    }

    @Override
    @ParametersAreNonnullByDefault
    public @NotNull ItemStack assemble(CraftingContainer craftingContainer, RegistryAccess registryAccess) {
        ItemStack baseBundle = null;
        Item resultItem = null;

        for (int i = 0; i < craftingContainer.getContainerSize(); i++) {
            ItemStack stack = craftingContainer.getItem(i);
            if (stack.isEmpty()) {
                continue;
            }

            if (DYED_BUNDLES.containsKey(stack.getItem())) {
                resultItem = DYED_BUNDLES.get(stack.getItem()).get();
            }
            else {
                baseBundle = stack;
            }
        }
        BundleContentsComponent component = BundleContentsComponent.getComponent(baseBundle);
        if (component == null || resultItem == null) {
            return ItemStack.EMPTY;
        }
        ItemStack result = new ItemStack(resultItem);
        component.setComponent(result);
        if (component.selectedIndex != -1) {
            BundleContents contents = BundleContents.of(result);
            if (contents != null && !contents.isEmpty()) {
                contents.setSelectedIndex(-1);
            }
        }
        return result;
    }

    @Override
    public boolean canCraftInDimensions(int x, int y) {
        return x * y >= 2;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return InvExpRecipes.DYE_BUNDLE_RECIPE.get();
    }

    static {
        DYED_BUNDLES = new HashMap<>();
        DYED_BUNDLES.put(Items.WHITE_DYE, InvExpItems.WHITE_BUNDLE);
        DYED_BUNDLES.put(Items.ORANGE_DYE, InvExpItems.ORANGE_BUNDLE);
        DYED_BUNDLES.put(Items.MAGENTA_DYE, InvExpItems.MAGENTA_BUNDLE);
        DYED_BUNDLES.put(Items.LIGHT_BLUE_DYE, InvExpItems.LIGHT_BLUE_BUNDLE);
        DYED_BUNDLES.put(Items.YELLOW_DYE, InvExpItems.YELLOW_BUNDLE);
        DYED_BUNDLES.put(Items.LIME_DYE, InvExpItems.LIME_BUNDLE);
        DYED_BUNDLES.put(Items.PINK_DYE, InvExpItems.PINK_BUNDLE);
        DYED_BUNDLES.put(Items.GRAY_DYE, InvExpItems.GRAY_BUNDLE);
        DYED_BUNDLES.put(Items.LIGHT_GRAY_DYE, InvExpItems.LIGHT_GRAY_BUNDLE);
        DYED_BUNDLES.put(Items.PURPLE_DYE, InvExpItems.PURPLE_BUNDLE);
        DYED_BUNDLES.put(Items.CYAN_DYE, InvExpItems.CYAN_BUNDLE);
        DYED_BUNDLES.put(Items.BLUE_DYE, InvExpItems.BLUE_BUNDLE);
        DYED_BUNDLES.put(Items.BROWN_DYE, InvExpItems.BROWN_BUNDLE);
        DYED_BUNDLES.put(Items.GREEN_DYE, InvExpItems.GREEN_BUNDLE);
        DYED_BUNDLES.put(Items.RED_DYE, InvExpItems.RED_BUNDLE);
        DYED_BUNDLES.put(Items.BLACK_DYE, InvExpItems.BLACK_BUNDLE);
    }
}

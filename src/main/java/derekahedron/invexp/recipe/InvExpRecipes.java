package derekahedron.invexp.recipe;

import derekahedron.invexp.InventoryExpansion;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class InvExpRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS;

    public static final RegistryObject<RecipeSerializer<DyeBundleRecipe>> DYE_BUNDLE_RECIPE;

    static {
        RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, InventoryExpansion.MOD_ID);

        DYE_BUNDLE_RECIPE = RECIPE_SERIALIZERS.register("dye_bundle", () -> new SimpleCraftingRecipeSerializer<>(DyeBundleRecipe::new));
    }
}

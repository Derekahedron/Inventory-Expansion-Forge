package derekahedron.invexp.recipe;

import derekahedron.invexp.InventoryExpansion;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class InvExpRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, InventoryExpansion.MOD_ID);

    public static final RegistryObject<RecipeSerializer<DyeBundleRecipe>> DYE_BUNDLE_RECIPE =
            RECIPE_SERIALIZERS.register("dye_bundle", DyeBundleRecipe.Serializer::new);
}

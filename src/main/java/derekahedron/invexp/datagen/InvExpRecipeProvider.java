package derekahedron.invexp.datagen;

import derekahedron.invexp.item.InvExpItemTags;
import derekahedron.invexp.item.InvExpItems;
import derekahedron.invexp.recipe.DyeBundleRecipe;
import derekahedron.invexp.util.InvExpUtil;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;
import java.util.function.Consumer;

public class InvExpRecipeProvider extends RecipeProvider {

    public InvExpRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    @SuppressWarnings("DataFlowIssue")
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS,
                        InvExpItems.SACK.get())
                .define('-', Tags.Items.STRING)
                .define('#', Tags.Items.LEATHER)
                .pattern("-#-")
                .pattern("# #")
                .pattern("###")
                .unlockedBy(
                        getHasName(Items.STRING),
                        has(Tags.Items.STRING))
                .save(consumer, InvExpItems.SACK.getId());

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS,
                        InvExpItems.QUIVER.get())
                .define('-', Tags.Items.STRING)
                .define('X', Items.RABBIT_HIDE)
                .define('#', Tags.Items.LEATHER)
                .pattern(" XX")
                .pattern("-##")
                .pattern(" ##")
                .unlockedBy(
                        getHasName(Items.ARROW),
                        has(ItemTags.ARROWS))
                .save(consumer, InvExpItems.QUIVER.getId());

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS,
                        Items.BUNDLE)
                .define('-', Tags.Items.STRING)
                .define('#', Tags.Items.LEATHER)
                .pattern("-")
                .pattern("#")
                .unlockedBy(
                        getHasName(Items.STRING),
                        has(Tags.Items.STRING))
                .save(consumer, InvExpUtil.location(Items.BUNDLE.toString()));

        dyeBundle(consumer, InvExpItems.WHITE_BUNDLE.get(), Items.WHITE_DYE);
        dyeBundle(consumer, InvExpItems.ORANGE_BUNDLE.get(), Items.ORANGE_DYE);
        dyeBundle(consumer, InvExpItems.MAGENTA_BUNDLE.get(), Items.MAGENTA_DYE);
        dyeBundle(consumer, InvExpItems.LIGHT_BLUE_BUNDLE.get(), Items.LIGHT_BLUE_DYE);
        dyeBundle(consumer, InvExpItems.YELLOW_BUNDLE.get(), Items.YELLOW_DYE);
        dyeBundle(consumer, InvExpItems.LIME_BUNDLE.get(), Items.LIME_DYE);
        dyeBundle(consumer, InvExpItems.PINK_BUNDLE.get(), Items.PINK_DYE);
        dyeBundle(consumer, InvExpItems.GRAY_BUNDLE.get(), Items.GRAY_DYE);
        dyeBundle(consumer, InvExpItems.LIGHT_GRAY_BUNDLE.get(), Items.LIGHT_GRAY_DYE);
        dyeBundle(consumer, InvExpItems.PURPLE_BUNDLE.get(), Items.PURPLE_DYE);
        dyeBundle(consumer, InvExpItems.CYAN_BUNDLE.get(), Items.CYAN_DYE);
        dyeBundle(consumer, InvExpItems.BLUE_BUNDLE.get(), Items.BLUE_DYE);
        dyeBundle(consumer, InvExpItems.BROWN_BUNDLE.get(), Items.BROWN_DYE);
        dyeBundle(consumer, InvExpItems.GREEN_BUNDLE.get(), Items.GREEN_DYE);
        dyeBundle(consumer, InvExpItems.RED_BUNDLE.get(), Items.RED_DYE);
        dyeBundle(consumer, InvExpItems.BLACK_BUNDLE.get(), Items.BLACK_DYE);
    }

    public static void dyeBundle(Consumer<FinishedRecipe> consumer, ItemLike bundle, ItemLike dye) {
        new DyeBundleRecipe.Builder(RecipeCategory.TOOLS,
                bundle,
                Ingredient.of(InvExpItemTags.DYEABLE_BUNDLES),
                Ingredient.of(dye))
                .group("bundle_dye")
                .unlockedBy(
                        getHasName(dye),
                        has(dye))
                .save(consumer, Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(bundle.asItem())));
    }
}
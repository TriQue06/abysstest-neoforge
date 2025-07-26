package net.trique.abysstest.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.trique.abysstest.AbyssTest;
import net.trique.abysstest.block.AbyssBlocks;
import net.trique.abysstest.item.AbyssItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class AbyssRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public AbyssRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        List<ItemLike> SOMETHING_SMELTABLES = List.of(AbyssItems.RAW_SOMETHING,
                AbyssBlocks.SOMETHING_ORE);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AbyssBlocks.SOMETHING_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', AbyssItems.SOMETHING_INGOT.get())
                .unlockedBy("has_something_ingot", has(AbyssItems.SOMETHING_INGOT)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AbyssItems.SOMETHING_INGOT.get(), 9)
                .requires(AbyssBlocks.SOMETHING_BLOCK)
                .unlockedBy("has_something_block", has(AbyssBlocks.SOMETHING_BLOCK)).save(recipeOutput);

        oreSmelting(recipeOutput, SOMETHING_SMELTABLES, RecipeCategory.MISC, AbyssItems.SOMETHING_INGOT.get(), 0.25f, 200, "something");
        oreBlasting(recipeOutput, SOMETHING_SMELTABLES, RecipeCategory.MISC, AbyssItems.SOMETHING_INGOT.get(), 0.25f, 100, "something");
    }

    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, AbyssTest.MODID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}
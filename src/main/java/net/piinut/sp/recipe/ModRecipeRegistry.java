package net.piinut.sp.recipe;

import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.piinut.sp.Main;

public class ModRecipeRegistry {

    public static RecipeSerializer<SpecialCraftingRecipe> EXPLODING_MAGMA_CREAM;
    public static RecipeSerializer<SpecialCraftingRecipe> SLIME_BALL;
    public static RecipeSerializer<SpecialCraftingRecipe> BLAZING_MAGMA_CREAM;

    private static <S extends RecipeSerializer<T>, T extends Recipe<?>> S register(String id, S serializer) {
        return Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(Main.MODID, id), serializer);
    }

    public static void registerAll(){
        EXPLODING_MAGMA_CREAM = register("exploding_magma_cream_upgrading", new SpecialRecipeSerializer<>(ExplodingMagmaCreamRecipe::new));
        SLIME_BALL = register("slime_ball_upgrading", new SpecialRecipeSerializer<>(SlimeBallRecipe::new));
        BLAZING_MAGMA_CREAM = register("blazing_magma_cream_upgrading", new SpecialRecipeSerializer<>(BlazingMagmaCreamRecipe::new));
    }

}

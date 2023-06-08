package com.example.flavorquest.domain.useCases

data class FavoriteRecipesUseCases(
    val saveFavoriteRecipe: SaveRecipeUseCase,
    val removeFromFavoriteRecipes: RemoveRecipeUseCase,
    val getFavoriteRecipes: GetFavoriteRecipesUseCase
)

package com.example.flavorquest.domain.useCases

data class FavoriteRecipesUseCases(
    val saveOrRemoveRecipeUseCase: SaveOrRemoveRecipeUseCase,
    val getFavoriteRecipes: GetFavoriteRecipesUseCase,
    val isFavoriteRecipeUseCase: isFavoriteRecipeUseCase
)

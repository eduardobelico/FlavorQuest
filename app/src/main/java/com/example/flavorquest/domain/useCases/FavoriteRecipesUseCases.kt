package com.example.flavorquest.domain.useCases

data class FavoriteRecipesUseCases(
    val saveOrRemoveRecipeUseCase: SaveOrRemoveRecipeUseCase,
    val getFavoriteRecipesUseCase: GetFavoriteRecipesUseCase,
    val isFavoriteRecipeUseCase: isFavoriteRecipeUseCase,
    val getNumberOfFavoriteRecipesUseCase: GetNumberOfFavoriteRecipesUseCase
)

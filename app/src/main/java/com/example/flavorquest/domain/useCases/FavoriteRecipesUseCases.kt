package com.example.flavorquest.domain.useCases

/**
 * Classe que engloba os UseCases de Receitas Favoritadas em apenas um lugar.
 **/

data class FavoriteRecipesUseCases(
    val saveOrRemoveRecipeUseCase: SaveOrRemoveRecipeUseCase,
    val getFavoriteRecipesUseCase: GetFavoriteRecipesUseCase,
    val isFavoriteRecipeUseCase: IsFavoriteRecipeUseCase,
    val getNumberOfFavoriteRecipesUseCase: GetNumberOfFavoriteRecipesUseCase
)

package com.example.flavorquest.presentation.favoriteList

import com.example.flavorquest.presentation.recipeList.RecipeUiState

sealed class FavoriteEvent {
object OnLoadRecipeList : FavoriteEvent()
data class OnFavoriteClick(val recipeState: RecipeUiState): FavoriteEvent()
}

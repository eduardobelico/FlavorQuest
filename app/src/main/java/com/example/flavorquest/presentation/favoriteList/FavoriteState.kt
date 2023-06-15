package com.example.flavorquest.presentation.favoriteList

import com.example.flavorquest.presentation.recipeList.RecipeUiState

sealed class FavoriteState {
    class Data(val favoritesList: List<RecipeUiState>) : FavoriteState()
    class Error(val message: String) : FavoriteState()
    object Loading : FavoriteState()
}

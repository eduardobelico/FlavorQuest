package com.example.flavorquest.presentation.recipeList

sealed class ListState {
    class Data(val recipeList: List<RecipeUiState>) : ListState()
    class Error(val message: String) : ListState()
    object Loading : ListState()
}

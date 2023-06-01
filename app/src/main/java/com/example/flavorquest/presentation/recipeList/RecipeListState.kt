package com.example.flavorquest.presentation.recipeList

import com.example.flavorquest.domain.model.Recipe

sealed class RecipeListState {
    class Data(val recipeList: List<Recipe>) : RecipeListState()
    class Error(val message: String) : RecipeListState()
    object Loading : RecipeListState()
}

package com.example.flavorquest.presentation.home

import com.example.flavorquest.domain.model.Recipe

sealed class RecipeListState {
    class Data(val recipeList: List<Recipe>) : RecipeListState()
    class Error(val message: String) : RecipeListState()
    object Loading : RecipeListState()
    object Empty : RecipeListState()
}

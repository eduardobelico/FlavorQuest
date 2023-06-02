package com.example.flavorquest.presentation.recipeDetails

import com.example.flavorquest.domain.model.Recipe

sealed class RecipeDetailsState {
    data class Data(val data: Recipe) : RecipeDetailsState()
    data class Error(val message: String) : RecipeDetailsState()
    object Loading : RecipeDetailsState()
}

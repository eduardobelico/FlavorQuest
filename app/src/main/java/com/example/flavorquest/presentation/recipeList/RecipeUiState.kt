package com.example.flavorquest.presentation.recipeList

import com.example.flavorquest.domain.model.Recipe

data class RecipeUiState(
    val isFavorite : Boolean = false,
    val recipe : Recipe
)
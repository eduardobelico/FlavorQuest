package com.example.flavorquest.data.remote.model

import com.example.flavorquest.core.getRecipeId

data class RecipeDto(
    val uri: String,
    val label: String,
    val imageUrl: String?,
    val ingredients: String,
    val cuisineType: String,
    val mealType: String,
    val dishType: String,
    val source: String,
    val url: String?
) {
    val id = uri.getRecipeId(),
    val name = label
}

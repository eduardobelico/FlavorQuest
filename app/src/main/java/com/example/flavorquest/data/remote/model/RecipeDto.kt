package com.example.flavorquest.data.remote.model

import com.example.flavorquest.core.getRecipeId
import com.squareup.moshi.Json

data class RecipeDto(
    val uri: String,
    val label: String,
    @field:Json(name = "image")
    val imageUrl: String?,
    @field:Json(name = "ingredientLines")
    val ingredients: List<String> = emptyList(),
    val cuisineType: List<String> = emptyList(),
    val mealType: List<String> = emptyList(),
    val dishType: List<String> = emptyList(),
    val source: String,
    val url: String?
)
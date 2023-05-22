package com.example.flavorquest.data.remote.model

import com.example.flavorquest.core.getRecipeId
import com.squareup.moshi.Json

data class RecipeDto(
    val uri: String,
    val label: String,
    @field:Json(name = "image")
    val imageUrl: String?,
    val ingredients: String,
    val cuisineType: String,
    val mealType: String,
    val dishType: String,
    val source: String,
    val url: String?
)
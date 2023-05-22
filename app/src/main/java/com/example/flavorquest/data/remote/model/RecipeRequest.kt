package com.example.flavorquest.data.remote.model

import com.squareup.moshi.Json

data class RecipeRequest(
    @field:Json(name = "hits")
    val recipeList: List<RecipeDto>
)

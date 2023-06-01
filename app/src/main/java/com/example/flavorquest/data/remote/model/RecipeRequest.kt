package com.example.flavorquest.data.remote.model

import com.google.gson.annotations.SerializedName


data class RecipeRequest(
    @SerializedName("hits")
    val recipeList: List<RecipeDto>
)

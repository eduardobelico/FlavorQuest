package com.example.flavorquest.data.remote.model

import com.google.gson.annotations.SerializedName

data class RecipeListDto(
    @SerializedName("recipe")
    val recipe: RecipeDto
)
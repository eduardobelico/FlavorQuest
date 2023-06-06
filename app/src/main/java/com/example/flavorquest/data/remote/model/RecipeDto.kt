package com.example.flavorquest.data.remote.model

import com.google.gson.annotations.SerializedName

data class RecipeDto(
    val uri: String?,
    @SerializedName("label")
    val name: String?,
    @SerializedName("image")
    val imageUrl: String?,
    @SerializedName("ingredientLines")
    val ingredients: List<String>?,
    val cuisineType: List<String>?,
    val mealType: List<String>?,
    val dishType: List<String>?,
    @SerializedName("dietLabels")
    val diet: List<String>?,
    val source: String?,
    val url: String?
)
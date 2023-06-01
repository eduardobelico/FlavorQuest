package com.example.flavorquest.data.remote.model

import com.google.gson.annotations.SerializedName

data class RecipeDto(
    @SerializedName("uri")
    val uri: String?,
    @SerializedName("label")
    val name: String?,
    @SerializedName("image")
    val imageUrl: String?,
    @SerializedName("ingredientLines")
    val ingredients: List<String>?,
    @SerializedName("cuisineType")
    val cuisineType: List<String>?,
    @SerializedName("mealType")
    val mealType: List<String>?,
    @SerializedName("dishType")
    val dishType: List<String>?,
    @SerializedName("source")
    val source: String?,
    @SerializedName("url")
    val url: String?
)
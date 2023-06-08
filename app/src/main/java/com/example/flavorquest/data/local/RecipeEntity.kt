package com.example.flavorquest.data.local

import androidx.room.PrimaryKey

data class RecipeEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val imageUrl: String?,
    val ingredients: List<String>,
    val cuisineType: List<String>,
    val mealType: List<String>,
    val dishType: List<String>,
    val diet: List<String>,
    val source: String,
    val url: String?
)

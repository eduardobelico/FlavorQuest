package com.example.flavorquest.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RecipeEntity(
    @PrimaryKey(autoGenerate = false)
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

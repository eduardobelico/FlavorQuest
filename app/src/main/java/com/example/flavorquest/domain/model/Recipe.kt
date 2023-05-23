package com.example.flavorquest.domain.model

class Recipe(
    val id: String?,
    val name: String,
    val imageUrl: String?,
    val ingredients: String,
    val cuisineType: String,
    val mealType: String,
    val dishType: String,
    val source: String,
    val url: String?
) {
}
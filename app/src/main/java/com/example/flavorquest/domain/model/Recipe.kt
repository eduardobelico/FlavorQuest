package com.example.flavorquest.domain.model

class Recipe(
    val id: String?,
    val name: String,
    val imageUrl: String?,
    val ingredients: List<String>,
    val cuisineType: List<String>,
    val mealType: List<String>,
    val dishType: List<String>,
    val source: String,
    val url: String?
)
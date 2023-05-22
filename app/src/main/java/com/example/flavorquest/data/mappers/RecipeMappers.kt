package com.example.flavorquest.data.mappers

import com.example.flavorquest.data.remote.model.RecipeDto
import com.example.flavorquest.domain.Recipe


fun RecipeDto.toRecipe(): Recipe {
    return Recipe(
        id = id,
        imageUrl = imageUrl,
        name = name,
        ingredients = ingredients,
        cuisineType = cuisineType,
        mealType = mealType,
        dishType = dishType,
        source = source,
        url = url
    )
}
package com.example.flavorquest.data.mappers

import com.example.flavorquest.core.getRecipeId
import com.example.flavorquest.data.remote.model.RecipeDto
import com.example.flavorquest.domain.model.Recipe


fun RecipeDto.toRecipe(): Recipe {
    return Recipe(
        id = uri.getRecipeId(),
        imageUrl = imageUrl,
        name = label,
        ingredients = ingredients,
        cuisineType = cuisineType,
        mealType = mealType,
        dishType = dishType,
        source = source,
        url = url
    )
}
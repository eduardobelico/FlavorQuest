package com.example.flavorquest.data.mappers

import com.example.flavorquest.core.getRecipeId
import com.example.flavorquest.data.remote.model.RecipeDto
import com.example.flavorquest.domain.model.Recipe


fun RecipeDto.toRecipe(): Recipe {
    
    return Recipe(
        id = uri?.getRecipeId() ?: "",
        imageUrl = imageUrl?: "",
        name = name ?: "",
        ingredients = ingredients?: emptyList(),
        cuisineType = cuisineType?: emptyList(),
        mealType = mealType?: emptyList(),
        dishType = dishType?: emptyList(),
        diet = diet?: emptyList(),
        source = source ?: "",
        url = url ?: ""
    )
}
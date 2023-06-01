package com.example.flavorquest.data.mappers

import android.util.Log
import com.example.flavorquest.core.getRecipeId
import com.example.flavorquest.data.remote.model.RecipeDto
import com.example.flavorquest.domain.model.Recipe


fun RecipeDto.toRecipe(): Recipe {
    Log.i("RecipeDto", "toRecipe: $this")
    return Recipe(
        id = uri?.getRecipeId() ?: "",
        imageUrl = imageUrl ?: "",
        name = label ?: "",
        ingredients = ingredients ?: emptyList(),
        cuisineType = cuisineType ?: emptyList(),
        mealType = mealType ?: emptyList(),
        dishType = dishType ?: emptyList(),
        source = source ?: "",
        url = url ?: ""
    )
}
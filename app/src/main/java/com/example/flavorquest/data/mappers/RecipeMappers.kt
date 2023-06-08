package com.example.flavorquest.data.mappers

import com.example.flavorquest.core.getRecipeId
import com.example.flavorquest.data.local.RecipeEntity
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

fun RecipeDto.toRecipeEntity(): RecipeEntity {
    
    return RecipeEntity(
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

fun RecipeEntity.toRecipe(): Recipe{
    
    return Recipe(
        id = id,
        imageUrl = imageUrl,
        name = name,
        ingredients = ingredients,
        cuisineType = cuisineType,
        mealType = mealType,
        dishType = dishType,
        diet = diet,
        source = source,
        url = url
    )
}
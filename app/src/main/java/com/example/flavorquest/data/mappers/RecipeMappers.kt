package com.example.flavorquest.data.mappers

import android.util.Log
import com.example.flavorquest.core.getRecipeId
import com.example.flavorquest.data.remote.model.RecipeDto
import com.example.flavorquest.domain.model.Recipe


fun RecipeDto.toRecipe(): Recipe {
    
    Log.i("oioi", "uri: $uri")
    Log.i("oioi", "imageUrl: $imageUrl")
    Log.i("oioi", "label: $label")
    Log.i("oioi", "ingredients: $ingredients")
    Log.i("oioi", "cuisineType: $cuisineType")
    Log.i("oioi", "mealType: $mealType")
    Log.i("oioi", "dishType: $dishType")
    Log.i("oioi", "source: $source")
    Log.i("oioi", "url: $url")
    return Recipe(
        id = uri?.getRecipeId(),
        imageUrl = imageUrl?: "",
        name = label ?: "",
        ingredients = ingredients?: emptyList(),
        cuisineType = cuisineType?: emptyList(),
        mealType = mealType?: emptyList(),
        dishType = dishType?: emptyList(),
        source = source ?: "",
        url = url ?: ""
    )
}
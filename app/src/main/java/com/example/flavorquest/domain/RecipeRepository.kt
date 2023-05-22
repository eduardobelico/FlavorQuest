package com.example.flavorquest.domain

import android.app.DownloadManager.Query

interface RecipeRepository {

    suspend fun getRecipeFromQuery(query: String): List<Recipe>
    suspend fun getRecipeFromCuisine(cuisineType: String): List<Recipe>
    suspend fun getRecipeFromDish(dishType: String): List<Recipe>
    suspend fun getRecipeFromQueryCuisine(
        query: String,
        cuisineType: String
    ): List<Recipe>
    suspend fun getRecipeFromQueryDish(
        query: String,
        dishType: String
    ): List<Recipe>
    suspend fun getRecipeFromCuisineDish(
        cuisineType: String,
        dishType: String
    ): List<Recipe>
    suspend fun getRecipeFromQueryCuisineDish(
        query: String,
        cuisineType: String,
        dishType: String
    ): List<Recipe>
}
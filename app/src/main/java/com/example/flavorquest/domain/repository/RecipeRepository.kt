package com.example.flavorquest.domain.repository

import com.example.flavorquest.core.Resource
import com.example.flavorquest.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {

    suspend fun getRecipeFromQuery(query: String): Flow<Resource<List<Recipe>>>
    suspend fun getRecipeFromCuisine(cuisineType: String): Flow<Resource<List<Recipe>>>
    suspend fun getRecipeFromDish(dishType: String): Flow<Resource<List<Recipe>>>
    suspend fun getRecipeFromQueryCuisine(query: String, cuisineType: String): Flow<Resource<List<Recipe>>>
    suspend fun getRecipeFromQueryDish(query: String, dishType: String): Flow<Resource<List<Recipe>>>
    suspend fun getRecipeFromCuisineDish(cuisineType: String, dishType: String): Flow<Resource<List<Recipe>>>
    suspend fun getRecipeFromQueryCuisineDish(query: String, cuisineType: String, dishType: String): Flow<Resource<List<Recipe>>>
}
package com.example.flavorquest.domain.repository

import com.example.flavorquest.core.Resource
import com.example.flavorquest.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    
    fun getRecipeFromQuery(query: String): Flow<Resource<List<Recipe>>>
    fun getRecipeFromCuisine(cuisineType: String): Flow<Resource<List<Recipe>>>
    fun getRecipeFromDish(dishType: String): Flow<Resource<List<Recipe>>>
    fun getRecipeFromQueryCuisine(query: String, cuisineType: String): Flow<Resource<List<Recipe>>>
    fun getRecipeFromQueryDish(query: String, dishType: String): Flow<Resource<List<Recipe>>>
   
    fun getRecipeFromCuisineDish(
        cuisineType: String,
        dishType: String
    ): Flow<Resource<List<Recipe>>>
    
    fun getRecipeFromQueryCuisineDish(
        query: String,
        cuisineType: String,
        dishType: String
    ): Flow<Resource<List<Recipe>>>
    
    fun getRecipeFromId(id: String): Flow<Resource<Recipe>>
    
    suspend fun insertRecipe(recipe: Recipe)
    
    suspend fun deleteRecipe(id: String)
    
    fun getFavoriteRecipes(): Flow<List<Recipe>>
    
    suspend fun isFavorite(id: String): Boolean
    
    fun getAmountOfFavoriteRecipes(): Flow<Int>
}
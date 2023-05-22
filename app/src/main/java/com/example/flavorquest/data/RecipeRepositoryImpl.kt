package com.example.flavorquest.data

import com.example.flavorquest.data.remote.network.RecipeServices
import com.example.flavorquest.domain.Recipe
import com.example.flavorquest.domain.RecipeRepository

class RecipeRepositoryImpl(
    private val recipeServices: RecipeServices
) : RecipeRepository {

    /**
     * Pega lista de receitas
     */

    override suspend fun getRecipeFromQuery(query: String): List<Recipe> {
        TODO("Not yet implemented")
    }

    override suspend fun getRecipeFromCuisine(cuisineType: String): List<Recipe> {
        TODO("Not yet implemented")
    }

    override suspend fun getRecipeFromDish(dishType: String): List<Recipe> {
        TODO("Not yet implemented")
    }

    override suspend fun getRecipeFromQueryCuisine(
        query: String,
        cuisineType: String
    ): List<Recipe> {
        TODO("Not yet implemented")
    }

    override suspend fun getRecipeFromQueryDish(query: String, dishType: String): List<Recipe> {
        TODO("Not yet implemented")
    }

    override suspend fun getRecipeFromCuisineDish(
        cuisineType: String,
        dishType: String
    ): List<Recipe> {
        TODO("Not yet implemented")
    }

    override suspend fun getRecipeFromQueryCuisineDish(
        query: String,
        cuisineType: String,
        dishType: String
    ): List<Recipe> {
        TODO("Not yet implemented")
    }
}
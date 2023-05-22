package com.example.flavorquest.data.repository

import com.example.flavorquest.data.mappers.toRecipe
import com.example.flavorquest.data.remote.network.RecipeServices
import com.example.flavorquest.domain.Recipe
import com.example.flavorquest.domain.RecipeRepository

class RecipeRepositoryImpl(
    private val recipeServices: RecipeServices
) : RecipeRepository {

    /**
     * Pega lista de receitas de acordo com cada combinação de parâmetros
     */

    override suspend fun getRecipeFromQuery(query: String): List<Recipe> {
        val queryResponse = recipeServices.getRecipeFromQuery(query = query)

        return queryResponse.recipeList.map { it.toRecipe() }
    }

    override suspend fun getRecipeFromCuisine(cuisineType: String): List<Recipe> {
        val cuisineResponse = recipeServices.getRecipeFromCuisine(cuisineType = cuisineType)

        return cuisineResponse.recipeList.map { it.toRecipe() }
    }

    override suspend fun getRecipeFromDish(dishType: String): List<Recipe> {
        val dishResponse = recipeServices.getRecipeFromDish(dishType = dishType)

        return dishResponse.recipeList.map { it.toRecipe() }
    }

    override suspend fun getRecipeFromQueryCuisine(query: String, cuisineType: String): List<Recipe> {
        val queryCuisineResponse = recipeServices.getRecipeFromQueryCuisine(query = query, cuisineType = cuisineType)

        return queryCuisineResponse.recipeList.map { it.toRecipe() }
    }

    override suspend fun getRecipeFromQueryDish(query: String, dishType: String): List<Recipe> {
        val queryDishResponse = recipeServices.getRecipeFromQueryDish(query = query, dishType = dishType)

        return queryDishResponse.recipeList.map { it.toRecipe() }
    }

    override suspend fun getRecipeFromCuisineDish(cuisineType: String, dishType: String): List<Recipe> {
        val cuisineDishResponse = recipeServices.getRecipeFromCuisineDish(cuisineType = cuisineType, dishType = dishType)

        return cuisineDishResponse.recipeList.map { it.toRecipe() }
    }

    override suspend fun getRecipeFromQueryCuisineDish(
        query: String,
        cuisineType: String,
        dishType: String
    ): List<Recipe> {
        val queryCuisineDishResponse = recipeServices.getRecipeFromQueryCuisineDish(query = query, cuisineType = cuisineType, dishType = dishType)

        return queryCuisineDishResponse.recipeList.map { it.toRecipe() }
    }
}
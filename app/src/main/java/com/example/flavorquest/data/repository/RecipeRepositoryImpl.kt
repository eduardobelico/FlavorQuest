package com.example.flavorquest.data.repository

import android.util.Log
import com.example.flavorquest.core.Resource
import com.example.flavorquest.data.mappers.toRecipe
import com.example.flavorquest.data.remote.network.RecipeServices
import com.example.flavorquest.domain.model.Recipe
import com.example.flavorquest.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RecipeRepositoryImpl(
    private val service: RecipeServices
) : RecipeRepository {

    /**
     * Pega a lista de receitas de acordo com cada combinação de parâmetros
     */

    override suspend fun getRecipeFromQuery(
        query: String
    ): Flow<Resource<List<Recipe>>> = flow {
        try {
            emit(Resource.Loading())

            val queryResponse = service.getRecipeFromQuery(query = query)
            emit(Resource.Success(queryResponse.recipeList.map { it.toRecipe() }))

        } catch (e: Exception) {
            Log.e("RecipeRepository", "$e")
            emit(Resource.Error("Insira o ingrediente novamente"))
        }
    }

    override suspend fun getRecipeFromCuisine(
        cuisineType: String
    ): Flow<Resource<List<Recipe>>> = flow {
        try {
            emit(Resource.Loading())

            val cuisineResponse = service.getRecipeFromCuisine(cuisineType = cuisineType)
            emit(Resource.Success(cuisineResponse.recipeList.map { it.toRecipe() }))

        } catch (e: Exception) {
            Log.e("RecipeRepository", "$e")
            emit(Resource.Error("Insira as informações novamente"))
        }
    }

    override suspend fun getRecipeFromDish(
        dishType: String
    ): Flow<Resource<List<Recipe>>> = flow {
        try {
            emit(Resource.Loading())

            val dishResponse = service.getRecipeFromDish(dishType = dishType)
            emit(Resource.Success(dishResponse.recipeList.map { it.toRecipe() }))

        } catch (e: Exception) {
            Log.e("RecipeRepository", "$e")
            emit(Resource.Error("Insira as informações novamente"))
        }
    }

    override suspend fun getRecipeFromQueryCuisine(
        query: String,
        cuisineType: String
    ): Flow<Resource<List<Recipe>>> = flow {
        try {
            emit(Resource.Loading())

            val queryCuisineResponse =
                service.getRecipeFromQueryCuisine(query = query, cuisineType = cuisineType)
            emit(Resource.Success(queryCuisineResponse.recipeList.map { it.toRecipe() }))

        } catch (e: Exception) {
            Log.e("RecipeRepository", "$e")
            emit(Resource.Error("Insira as informações novamente"))
        }
    }

    override suspend fun getRecipeFromQueryDish(
        query: String,
        dishType: String
    ): Flow<Resource<List<Recipe>>> = flow {
        try {
            emit(Resource.Loading())

            val queryDishResponse =
                service.getRecipeFromQueryDish(query = query, dishType = dishType)
            emit(Resource.Success(queryDishResponse.recipeList.map { it.toRecipe() }))

        } catch (e: Exception) {
            Log.e("RecipeRepository", "$e")
            emit(Resource.Error("Insira as informações novamente"))
        }
    }

    override suspend fun getRecipeFromCuisineDish(
        cuisineType: String,
        dishType: String
    ): Flow<Resource<List<Recipe>>> = flow {
        try {
            emit(Resource.Loading())

            val cuisineDishResponse =
                service.getRecipeFromCuisineDish(cuisineType = cuisineType, dishType = dishType)
            emit(Resource.Success(cuisineDishResponse.recipeList.map { it.toRecipe() }))

        } catch (e: Exception) {
            Log.e("RecipeRepository", "$e")
            emit(Resource.Error("Insira as informações novamente"))
        }
    }

    override suspend fun getRecipeFromQueryCuisineDish(
        query: String,
        cuisineType: String,
        dishType: String
    ): Flow<Resource<List<Recipe>>> = flow {
        try {
            emit(Resource.Loading())

            val queryCuisineDishResponse = service.getRecipeFromQueryCuisineDish(
                query = query,
                cuisineType = cuisineType,
                dishType = dishType
            )
            emit(Resource.Success(queryCuisineDishResponse.recipeList.map { it.toRecipe() }))

        } catch (e: Exception) {
            Log.e("RecipeRepository", "$e")
            emit(Resource.Error("Insira as informações novamente"))
        }
    }
}
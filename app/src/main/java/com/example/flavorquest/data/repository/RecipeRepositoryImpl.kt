package com.example.flavorquest.data.repository

import android.util.Log
import com.example.flavorquest.core.Resource
import com.example.flavorquest.data.local.RecipeDao
import com.example.flavorquest.data.mappers.toRecipe
import com.example.flavorquest.data.mappers.toRecipeEntity
import com.example.flavorquest.data.remote.network.RecipeServices
import com.example.flavorquest.domain.model.Recipe
import com.example.flavorquest.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow

import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class RecipeRepositoryImpl(
    private val service: RecipeServices,
    private val dao: RecipeDao
) : RecipeRepository {
    
    /**
     * Pega a lista de receitas de acordo com cada combinação de parâmetros
     */
    
    override fun getRecipeFromQuery(
        query: String
    ): Flow<Resource<List<Recipe>>> = flow {
        try {
            emit(Resource.Loading())
            
            val queryResponse = service.getRecipeFromQuery(query = query)
            val recipeList = queryResponse.recipeList.map { recipeListDto ->
                recipeListDto.recipe
            }
            val recipeModel = recipeList.map { recipeDto ->
                recipeDto.toRecipe()
            }
            emit(Resource.Success(recipeModel))
            Log.i("RecipeRepository", "getRecipeFromQuery: ${recipeModel}")
        } catch (e: Exception) {
            Log.e("RecipeRepository", "$e")
            emit(Resource.Error("Insira o ingrediente novamente"))
        }
    }
    
    override fun getRecipeFromCuisine(
        cuisineType: String
    ): Flow<Resource<List<Recipe>>> = flow {
        try {
            emit(Resource.Loading())
            
            val cuisineResponse = service.getRecipeFromCuisine(cuisineType = cuisineType)
            val recipeList = cuisineResponse.recipeList.map { recipeListDto ->
                recipeListDto.recipe
            }
            val recipeModel = recipeList.map { recipeDto ->
                recipeDto.toRecipe()
            }
            emit(Resource.Success(recipeModel))
            
        } catch (e: Exception) {
            Log.e("RecipeRepository", "$e")
            emit(Resource.Error("Insira as informações novamente"))
        }
    }
    
    override fun getRecipeFromDish(
        dishType: String
    ): Flow<Resource<List<Recipe>>> = flow {
        try {
            emit(Resource.Loading())
            
            val dishResponse = service.getRecipeFromDish(dishType = dishType)
            val recipeList = dishResponse.recipeList.map { recipeListDto ->
                recipeListDto.recipe
            }
            val recipeModel = recipeList.map { recipeDto ->
                recipeDto.toRecipe()
            }
            emit(Resource.Success(recipeModel))
            
        } catch (e: Exception) {
            Log.e("RecipeRepository", "$e")
            emit(Resource.Error("Insira as informações novamente"))
        }
    }
    
    override fun getRecipeFromQueryCuisine(
        query: String,
        cuisineType: String
    ): Flow<Resource<List<Recipe>>> = flow {
        try {
            emit(Resource.Loading())
            
            val queryCuisineResponse =
                service.getRecipeFromQueryCuisine(query = query, cuisineType = cuisineType)
            val recipeList = queryCuisineResponse.recipeList.map { recipeListDto ->
                recipeListDto.recipe
            }
            val recipeModel = recipeList.map { recipeDto ->
                recipeDto.toRecipe()
            }
            emit(Resource.Success(recipeModel))
            
        } catch (e: Exception) {
            Log.e("RecipeRepository", "$e")
            emit(Resource.Error("Insira as informações novamente"))
        }
    }
    
    override fun getRecipeFromQueryDish(
        query: String,
        dishType: String
    ): Flow<Resource<List<Recipe>>> = flow {
        try {
            emit(Resource.Loading())
            
            val queryDishResponse =
                service.getRecipeFromQueryDish(query = query, dishType = dishType)
            val recipeList = queryDishResponse.recipeList.map { recipeListDto ->
                recipeListDto.recipe
            }
            val recipeModel = recipeList.map { recipeDto ->
                recipeDto.toRecipe()
            }
            emit(Resource.Success(recipeModel))
            
        } catch (e: Exception) {
            Log.e("RecipeRepository", "$e")
            emit(Resource.Error("Insira as informações novamente"))
        }
    }
    
    override fun getRecipeFromCuisineDish(
        cuisineType: String,
        dishType: String
    ): Flow<Resource<List<Recipe>>> = flow {
        try {
            emit(Resource.Loading())
            
            val cuisineDishResponse =
                service.getRecipeFromCuisineDish(cuisineType = cuisineType, dishType = dishType)
            val recipeList = cuisineDishResponse.recipeList.map { recipeListDto ->
                recipeListDto.recipe
            }
            val recipeModel = recipeList.map { recipeDto ->
                recipeDto.toRecipe()
            }
            emit(Resource.Success(recipeModel))
            
        } catch (e: Exception) {
            Log.e("RecipeRepository", "$e")
            emit(Resource.Error("Insira as informações novamente"))
        }
    }
    
    override fun getRecipeFromQueryCuisineDish(
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
            val recipeList = queryCuisineDishResponse.recipeList.map { recipeListDto ->
                recipeListDto.recipe
            }
            val recipeModel = recipeList.map { recipeDto ->
                recipeDto.toRecipe()
            }
            emit(Resource.Success(recipeModel))
        } catch (e: Exception) {
            Log.e("RecipeRepository", "$e")
            emit(Resource.Error("Insira as informações novamente"))
        }
    }
    
    /**
     * Pega uma receita de acordo com o id.
     **/
    
    override fun getRecipeFromId(
        id: String
    ): Flow<Resource<Recipe>> = flow {
        try {
            emit(Resource.Loading())
            
            val idResponse = service.getRecipeFromId(id = id)
            emit(Resource.Success(idResponse.recipe.toRecipe()))
            
        } catch (e: Exception) {
            Log.e("RecipeRepository", "$e")
            emit(Resource.Error("Não foi possível encontrar a receita"))
        }
    }
    
    /**
     * Adiciona receita aos favoritos.
     **/
    
    override suspend fun insertRecipe(recipe: Recipe) {
        dao.saveRecipe(recipe.toRecipeEntity())
    }
    
    /**
     * Remove receita dos favoritos.
     **/
    
    override suspend fun deleteRecipe(id: String) {
        dao.removeRecipe(id)
    }
    
    /**
     * Pega a lista de receitas favoritadas.
     **/
    
    override fun getFavoriteRecipes(): Flow<List<Recipe>> {
        return dao.getFavoriteRecipes().map { entities ->
            entities.map { it.toRecipe() }
        }
    }
    
    /**
     * Checa se a receita está favoritada ou não.
     **/
    
    override suspend fun isFavorite(id: String): Boolean {
        val favoriteList = dao.isFavorite(id)
        return favoriteList.isNotEmpty()
    }
    
    /**
     * Pega a quantidade de receitas favoritadas.
     **/
    
    override fun getAmountOfFavoriteRecipes(): Flow<Int> {
        return dao.getFavoriteRecipesCount()
    }
    
}
package com.example.flavorquest.domain.useCases.recipeUseCases

import android.util.Log
import com.example.flavorquest.core.Resource
import com.example.flavorquest.domain.model.Recipe
import com.example.flavorquest.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RequestUseCase(
    private val repository: RecipeRepository
) {
    //need a code that can receive null values
    
    
    operator fun invoke(
        query: String?,
        cuisineType: String?,
        dishType: String?
    ): Flow<Resource<List<Recipe>>> {
        
        return flow {
            if (cuisineType.isNullOrBlank() && dishType.isNullOrBlank()) {
                Log.i("oi", "invoke: query=$query")
                repository.getRecipeFromQuery(
                    query = query ?: ""
                )
            } else if (query.isNullOrBlank() && dishType.isNullOrBlank()) {
                repository.getRecipeFromCuisine(
                    cuisineType = cuisineType ?: ""
                )
            } else if (query.isNullOrBlank() && cuisineType.isNullOrBlank()) {
                repository.getRecipeFromDish(
                    dishType = dishType ?: ""
                )
            } else if (dishType.isNullOrBlank()) {
                repository.getRecipeFromQueryCuisine(
                    query = query ?: "",
                    cuisineType = cuisineType ?: ""
                )
            } else if (cuisineType.isNullOrBlank()) {
                repository.getRecipeFromQueryDish(
                    query = query ?: "",
                    dishType = dishType ?: ""
                )
            } else if (query.isNullOrBlank()) {
                repository.getRecipeFromCuisineDish(
                    cuisineType = cuisineType ?: "",
                    dishType = dishType ?: ""
                )
            } else {
                repository.getRecipeFromQueryCuisineDish(
                    query = query,
                    cuisineType = cuisineType,
                    dishType = dishType
                )
            }
        }
    }
}
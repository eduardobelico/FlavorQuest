package com.example.flavorquest.domain.useCases

import com.example.flavorquest.core.Resource
import com.example.flavorquest.domain.model.Recipe
import com.example.flavorquest.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow

class GetRecipeListUseCase(
    private val repository: RecipeRepository
) {
    operator fun invoke(
        query: String?,
        cuisineType: String?,
        dishType: String?
    ): Flow<Resource<List<Recipe>>> {
        
        return if (cuisineType.isNullOrBlank() && dishType.isNullOrBlank() && !query.isNullOrBlank()) {
            repository.getRecipeFromQuery(
                query = query
            )
        } else if (!cuisineType.isNullOrBlank() && dishType.isNullOrBlank() && query.isNullOrBlank()) {
            repository.getRecipeFromCuisine(
                cuisineType = cuisineType
            )
        } else if (cuisineType.isNullOrBlank() && !dishType.isNullOrBlank() && query.isNullOrBlank()) {
            repository.getRecipeFromDish(
                dishType = dishType
            )
        } else if (!cuisineType.isNullOrBlank() && dishType.isNullOrBlank() && !query.isNullOrBlank()) {
            repository.getRecipeFromQueryCuisine(
                query = query,
                cuisineType = cuisineType
            )
        } else if (cuisineType.isNullOrBlank() && !dishType.isNullOrBlank() && !query.isNullOrBlank()) {
            repository.getRecipeFromQueryDish(
                query = query,
                dishType = dishType
            )
        } else if (!cuisineType.isNullOrBlank() && !dishType.isNullOrBlank() && query.isNullOrBlank()) {
            repository.getRecipeFromCuisineDish(
                cuisineType = cuisineType,
                dishType = dishType
            )
        } else {
            repository.getRecipeFromQueryCuisineDish(
                query = query!!,
                cuisineType = cuisineType!!,
                dishType = dishType!!
            )
        }
    }
}
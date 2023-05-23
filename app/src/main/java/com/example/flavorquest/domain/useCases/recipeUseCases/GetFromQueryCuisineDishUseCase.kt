package com.example.flavorquest.domain.useCases.recipeUseCases

import com.example.flavorquest.core.Resource
import com.example.flavorquest.domain.model.Recipe
import com.example.flavorquest.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow

class GetFromQueryCuisineDishUseCase(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(
        query: String,
        cuisineType: String,
        dishType: String
    ): Flow<Resource<List<Recipe>>> = repository.getRecipeFromQueryCuisineDish(
        query = query,
        cuisineType = cuisineType,
        dishType = dishType
    )
}
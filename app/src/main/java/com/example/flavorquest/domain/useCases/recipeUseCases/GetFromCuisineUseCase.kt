package com.example.flavorquest.domain.useCases.recipeUseCases

import com.example.flavorquest.core.Resource
import com.example.flavorquest.domain.model.Recipe
import com.example.flavorquest.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow

class GetFromCuisineUseCase(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(
        cuisineType: String
    ): Flow<Resource<List<Recipe>>> = repository.getRecipeFromCuisine(cuisineType = cuisineType)
}
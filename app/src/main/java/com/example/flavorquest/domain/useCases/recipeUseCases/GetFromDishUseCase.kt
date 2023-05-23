package com.example.flavorquest.domain.useCases.recipeUseCases

import com.example.flavorquest.core.Resource
import com.example.flavorquest.domain.model.Recipe
import com.example.flavorquest.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow

class GetFromDishUseCase(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(
        dishType: String?
    ): Flow<Resource<List<Recipe>>> = repository.getRecipeFromDish(dishType)
}
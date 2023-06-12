package com.example.flavorquest.domain.useCases

import com.example.flavorquest.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow

class GetNumberOfFavoriteRecipesUseCase(
    private val repository: RecipeRepository
) {
    operator fun invoke(): Flow<Int> = repository.getAmountOfFavoriteRecipes()
}
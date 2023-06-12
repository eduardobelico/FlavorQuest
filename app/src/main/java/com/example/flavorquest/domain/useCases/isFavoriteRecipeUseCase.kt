package com.example.flavorquest.domain.useCases

import com.example.flavorquest.domain.repository.RecipeRepository

class isFavoriteRecipeUseCase(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(id: String): Boolean {
        return repository.isFavorite(id)
    }
}
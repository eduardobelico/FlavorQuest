package com.example.flavorquest.domain.useCases

import com.example.flavorquest.domain.repository.RecipeRepository

/**
 * Classe que faz a requisição ao repositório para realizar a checagem se uma receita
 * está favoritada ou não.
 **/

class IsFavoriteRecipeUseCase(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(id: String): Boolean {
        return repository.isFavorite(id)
    }
}
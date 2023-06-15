package com.example.flavorquest.domain.useCases

import com.example.flavorquest.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow

/**
 * Classe que faz a requisição ao repositório para coletar o número de receitas favoritadas.
 **/

class GetNumberOfFavoriteRecipesUseCase(
    private val repository: RecipeRepository
) {
    operator fun invoke(): Flow<Int> = repository.getAmountOfFavoriteRecipes()
}
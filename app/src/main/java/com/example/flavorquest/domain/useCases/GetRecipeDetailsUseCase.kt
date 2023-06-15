package com.example.flavorquest.domain.useCases

import com.example.flavorquest.core.Resource
import com.example.flavorquest.domain.model.Recipe
import com.example.flavorquest.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow

/**
 * Classe que faz a requisição ao repositório para coletar os detalhes de uma receita
 * enviando o id como parâmetro.
 **/

class GetRecipeDetailsUseCase(
    private val repository: RecipeRepository
) {
    operator fun invoke(
        id: String
    ): Flow<Resource<Recipe>> {
        
        return repository.getRecipeFromId(
            id = id
        )
    }
}
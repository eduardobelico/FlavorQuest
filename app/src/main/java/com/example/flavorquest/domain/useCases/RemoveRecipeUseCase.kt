package com.example.flavorquest.domain.useCases

import com.example.flavorquest.domain.model.Recipe
import com.example.flavorquest.domain.repository.RecipeRepository

class RemoveRecipeUseCase(
    private val repository: RecipeRepository
    ) {
    
    suspend operator fun invoke(recipe: Recipe) {
        repository.deleteRecipe(recipe)
    }
}
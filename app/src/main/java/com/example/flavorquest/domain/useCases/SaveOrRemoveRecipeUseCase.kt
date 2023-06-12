package com.example.flavorquest.domain.useCases

import com.example.flavorquest.domain.model.Recipe
import com.example.flavorquest.domain.repository.RecipeRepository

class SaveOrRemoveRecipeUseCase(
    private val repository: RecipeRepository
) {
    
    suspend operator fun invoke(recipe: Recipe) {
        if (!repository.isFavorite(recipe.id)) {
            repository.insertRecipe(recipe)
        } else {
            repository.deleteRecipe(recipe.id)
        }
    }
}

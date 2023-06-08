package com.example.flavorquest.di

import com.example.flavorquest.domain.useCases.*
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object DomainModule {
    
    fun load() {
        loadKoinModules(useCasesModule())
    }
    
    private fun useCasesModule(): Module {
        return module {
            factory { GetRecipeListUseCase(repository = get()) }
            factory { GetRecipeDetailsUseCase(repository = get()) }
            factory { SaveRecipeUseCase(repository = get()) }
            factory { RemoveRecipeUseCase(repository = get()) }
            factory { GetFavoriteRecipesUseCase(repository = get()) }
            factory { FavoriteRecipesUseCases(
                saveFavoriteRecipe = get(),
                removeFromFavoriteRecipes = get(),
                getFavoriteRecipes = get()
            ) }
        }
    }
}
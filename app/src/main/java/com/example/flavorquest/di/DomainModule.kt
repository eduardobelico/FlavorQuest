package com.example.flavorquest.di

import com.example.flavorquest.domain.useCases.*
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object DomainModule {
    
    /**
     * Load do Módulos da camada de Domain.
     **/
    
    fun load() {
        loadKoinModules(useCasesModule())
    }
    
    private fun useCasesModule(): Module {
        return module {
            factory { GetRecipeListUseCase(repository = get()) }
            factory { GetRecipeDetailsUseCase(repository = get()) }
            factory { SaveOrRemoveRecipeUseCase(repository = get()) }
            factory { IsFavoriteRecipeUseCase(repository = get()) }
            factory { GetFavoriteRecipesUseCase(repository = get()) }
            factory { GetNumberOfFavoriteRecipesUseCase(repository = get()) }
            factory { FavoriteRecipesUseCases(
                saveOrRemoveRecipeUseCase = get(),
                isFavoriteRecipeUseCase = get(),
                getFavoriteRecipesUseCase = get(),
                getNumberOfFavoriteRecipesUseCase = get()
            ) }
        }
    }
}
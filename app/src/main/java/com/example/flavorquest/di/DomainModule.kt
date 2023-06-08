package com.example.flavorquest.di

import com.example.flavorquest.domain.useCases.GetRecipeDetailsUseCase
import com.example.flavorquest.domain.useCases.GetRecipeListUseCase
import com.example.flavorquest.domain.useCases.RemoveRecipeUseCase
import com.example.flavorquest.domain.useCases.SaveRecipeUseCase
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
        }
    }
}
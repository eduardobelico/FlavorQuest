package com.example.flavorquest.di

import com.example.flavorquest.domain.useCases.recipeUseCases.*
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object DomainModule {
    
    fun load() {
        loadKoinModules(useCaseModule())
    }
    
    private fun useCaseModule(): Module {
        return module {
            factory { RequestUseCase(repository = get()) }
        }
    }
}
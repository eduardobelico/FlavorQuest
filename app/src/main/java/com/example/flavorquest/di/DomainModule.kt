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
            factory { GetFromQueryUseCase(repository = get())}
            factory { GetFromCuisineUseCase(repository = get())}
            factory { GetFromDishUseCase(repository = get())}
            factory { GetFromQueryCuisineUseCase(repository = get())}
            factory { GetFromQueryDishUseCase(repository = get())}
            factory { GetFromCuisineDishUseCase(repository = get())}
            factory { GetFromQueryCuisineDishUseCase(repository = get())}
        }
    }
}
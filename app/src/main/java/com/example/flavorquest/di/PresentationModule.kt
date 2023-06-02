package com.example.flavorquest.di

import com.example.flavorquest.presentation.home.HomeViewModel
import com.example.flavorquest.presentation.recipeList.ListViewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object PresentationModule {
    fun load() {
        loadKoinModules(viewModelModule())
    }
    
    private fun viewModelModule(): Module {
        return module {
            factory { HomeViewModel() }
            factory { ListViewModel(getRecipeListUseCase = get()) }
        }
    }
}
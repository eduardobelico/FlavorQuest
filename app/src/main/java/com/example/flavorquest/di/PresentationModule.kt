package com.example.flavorquest.di

import com.example.flavorquest.presentation.favoriteList.FavoriteRecipesViewModel
import com.example.flavorquest.presentation.home.HomeViewModel
import com.example.flavorquest.presentation.recipeDetails.DetailsViewModel
import com.example.flavorquest.presentation.recipeList.ListViewModel
import com.example.flavorquest.presentation.userLevel.UserLevelViewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object PresentationModule {
    fun load() {
        loadKoinModules(viewModelModule())
    }
    
    private fun viewModelModule(): Module {
        return module {
            factory { HomeViewModel(favoriteRecipesUseCases = get()) }
            factory { ListViewModel(getRecipeListUseCase = get(), favoriteRecipesUseCases = get()) }
            factory { DetailsViewModel(getDetailsUseCase = get(), favoriteRecipesUseCases = get()) }
            factory { FavoriteRecipesViewModel(favoriteRecipesUseCases = get()) }
            factory { UserLevelViewModel(favoriteRecipesUseCases = get()) }
        }
    }
}
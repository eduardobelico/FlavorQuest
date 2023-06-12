package com.example.flavorquest.presentation.favoriteList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flavorquest.domain.model.Recipe
import com.example.flavorquest.domain.useCases.FavoriteRecipesUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FavoriteRecipesViewModel(
    private val favoriteRecipesUseCases: FavoriteRecipesUseCases
) : ViewModel() {
    
    private val _favoriteRecipes = MutableStateFlow<FavoriteState>(FavoriteState.Loading)
    val favoriteRecipes: StateFlow<FavoriteState> get() = _favoriteRecipes
    
    fun getFavoriteRecipes(event: FavoriteEvent) {
        when (event) {
            is FavoriteEvent.OnLoadRecipeList -> {
                viewModelScope.launch {
                    favoriteRecipesUseCases.getFavoriteRecipesUseCase().collectLatest { result ->
                            val favoritesList = result.map { recipe ->
                                val isFavorite = favoriteRecipesUseCases.isFavoriteRecipeUseCase(recipe.id)
                                Recipe(
                                    recipe.id,
                                    recipe.name,
                                    recipe.imageUrl,
                                    recipe.ingredients,
                                    recipe.cuisineType,
                                    recipe.mealType,
                                    recipe.dishType,
                                    recipe.diet,
                                    recipe.source,
                                    recipe.url
                                ).apply {
                                    this.isFavorite = isFavorite
                                }
                            }
                            _favoriteRecipes.value = FavoriteState.Data(favoritesList)
                        }
                    }
            }
            is FavoriteEvent.OnFavoriteClick -> {
                viewModelScope.launch {
                    favoriteRecipesUseCases.saveOrRemoveRecipeUseCase(event.recipe)
                }
            }
        }
    }
}
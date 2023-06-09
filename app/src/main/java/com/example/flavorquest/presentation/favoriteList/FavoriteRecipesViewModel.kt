package com.example.flavorquest.presentation.favoriteList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flavorquest.domain.useCases.FavoriteRecipesUseCases
import com.example.flavorquest.presentation.recipeList.RecipeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FavoriteRecipesViewModel(
    private val favoriteRecipesUseCases: FavoriteRecipesUseCases
) : ViewModel() {
    
    private val _favoriteRecipes = MutableStateFlow<FavoriteState>(FavoriteState.Loading)
    val favoriteRecipes: StateFlow<FavoriteState> get() = _favoriteRecipes
    
    /**
     * Carrega lista de receitas favoritadas.
     * */
    
    fun getFavoriteRecipes(event: FavoriteEvent) {
        when (event) {
            is FavoriteEvent.OnLoadRecipeList -> {
                viewModelScope.launch {
                    favoriteRecipesUseCases.getFavoriteRecipesUseCase().collectLatest { result ->
                        val favoritesList = result.map { recipe ->
                            RecipeUiState(
                                isFavorite = favoriteRecipesUseCases.isFavoriteRecipeUseCase(recipe.id),
                                recipe = recipe
                            )
                        }
                        _favoriteRecipes.value = FavoriteState.Data(favoritesList)
                    }
                }
            }
            is FavoriteEvent.OnFavoriteClick -> {
                viewModelScope.launch {
                    favoriteRecipesUseCases.saveOrRemoveRecipeUseCase(event.recipeState.recipe)
                }
            }
        }
    }
}
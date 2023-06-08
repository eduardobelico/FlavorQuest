package com.example.flavorquest.presentation.favoriteList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flavorquest.domain.model.Recipe
import com.example.flavorquest.domain.useCases.FavoriteRecipesUseCases
import com.example.flavorquest.presentation.state.ListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FavoriteRecipesViewModel(
    private val favoriteRecipesUseCases: FavoriteRecipesUseCases
) : ViewModel() {
    
    private val _favoriteRecipes = MutableStateFlow<ListState>(ListState.Loading)
    val favoriteRecipes: StateFlow<ListState> get() = _favoriteRecipes
    
    fun getFavoriteRecipes() {
        viewModelScope.launch {
            favoriteRecipesUseCases.getFavoriteRecipes().collectLatest { result ->
                try {
                    _favoriteRecipes.value = ListState.Data(result)
                } catch (e: Exception) {
                    _favoriteRecipes.value = ListState.Error(e.message ?: "Unknown error occurred")
                }
            }
        }
    }
    
    fun removeFromFavorites(recipe: Recipe) {
        viewModelScope.launch {
            favoriteRecipesUseCases.removeFromFavoriteRecipes(recipe)
        }
    }
}
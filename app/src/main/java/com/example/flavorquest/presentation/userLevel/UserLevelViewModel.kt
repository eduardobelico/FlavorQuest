package com.example.flavorquest.presentation.userLevel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flavorquest.domain.useCases.FavoriteRecipesUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class UserLevelViewModel(
    private val favoriteRecipesUseCases: FavoriteRecipesUseCases
) : ViewModel() {
    
    private val _numFavoriteRecipes = MutableStateFlow(0)
    val numFavoriteRecipes: StateFlow<Int> get() = _numFavoriteRecipes
    
    init {
        getNumberOfFavoriteRecipes()
    }
    
    fun getNumberOfFavoriteRecipes() {
        viewModelScope.launch {
            favoriteRecipesUseCases.getNumberOfFavoriteRecipesUseCase()
                .collectLatest { numRecipes ->
                    _numFavoriteRecipes.value = numRecipes
                }
        }
    }
    
}
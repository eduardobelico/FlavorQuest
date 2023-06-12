package com.example.flavorquest.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flavorquest.domain.useCases.FavoriteRecipesUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel(
    private val favoriteRecipesUseCases: FavoriteRecipesUseCases
) : ViewModel() {
    
    private val _state = MutableStateFlow<HomeState>(HomeState.Empty)
    val state: StateFlow<HomeState> get() = _state
    
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
    
    fun checkParameters(
        query: String?,
        cuisineType: String?,
        dishType: String?
    ) {
        if (query.isNullOrBlank() && cuisineType.isNullOrBlank() && dishType.isNullOrBlank()) {
            _state.value = HomeState.Error("Insira algum dado sobre a receita!")
        } else {
            _state.value = HomeState.Success
        }
    }
    
    fun stateRefresh() {
        _state.value = HomeState.Empty
    }
}
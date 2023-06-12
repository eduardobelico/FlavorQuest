package com.example.flavorquest.presentation.recipeDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flavorquest.core.Resource
import com.example.flavorquest.domain.model.Recipe
import com.example.flavorquest.domain.useCases.FavoriteRecipesUseCases
import com.example.flavorquest.domain.useCases.GetRecipeDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val getDetailsUseCase: GetRecipeDetailsUseCase,
    private val favoriteRecipesUseCases: FavoriteRecipesUseCases
) : ViewModel() {
    
    private val _recipeDetails = MutableStateFlow<DetailsState>(DetailsState.Loading)
    val recipeDetails: StateFlow<DetailsState> get() = _recipeDetails
    
    private val _recipeSaved = MutableStateFlow<Boolean?>(null)
    val recipeSaved: StateFlow<Boolean?> get() = _recipeSaved
    
    fun getRecipeDetails(id: String) {
        viewModelScope.launch {
            getDetailsUseCase(id).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _recipeDetails.value = DetailsState.Loading
                    }
                    is Resource.Error -> {
                        result.message?.let { message ->
                            _recipeDetails.value = DetailsState.Error(message)
                        }
                    }
                    is Resource.Success -> {
                        result.data?.let { recipeDetails ->
                            _recipeDetails.value = DetailsState.Data(recipeDetails)
                        }
                    }
                }
            }
        }
    }
    
    fun saveRecipe(recipe: Recipe) {
        viewModelScope.launch {
            favoriteRecipesUseCases.saveOrRemoveRecipeUseCase(recipe)
            _recipeSaved.value = true
        }
    }
    
}

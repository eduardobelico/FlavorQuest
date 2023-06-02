package com.example.flavorquest.presentation.recipeDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flavorquest.core.Resource
import com.example.flavorquest.domain.useCases.GetRecipeDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RecipeDetailsViewModel(
    private val getDetailsUseCase: GetRecipeDetailsUseCase
) : ViewModel() {
    
    private val _recipeDetails = MutableStateFlow<RecipeDetailsState>(RecipeDetailsState.Loading)
    val recipeDetails: StateFlow<RecipeDetailsState> get() = _recipeDetails
    
    fun getRecipeDetails(id: String) {
        viewModelScope.launch {
            getDetailsUseCase(id).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _recipeDetails.value = RecipeDetailsState.Loading
                    }
                    is Resource.Error -> {
                        result.message?.let { message ->
                            _recipeDetails.value = RecipeDetailsState.Error(message)
                        }
                    }
                    is Resource.Success -> {
                        result.data?.let { recipeDetails ->
                            _recipeDetails.value = RecipeDetailsState.Data(recipeDetails)
                        }
                    }
                }
            }
        }
    }
}

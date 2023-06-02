package com.example.flavorquest.presentation.recipeDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flavorquest.core.Resource
import com.example.flavorquest.domain.useCases.GetRecipeDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val getDetailsUseCase: GetRecipeDetailsUseCase
) : ViewModel() {
    
    private val _recipeDetails = MutableStateFlow<DetailsState>(DetailsState.Loading)
    val recipeDetails: StateFlow<DetailsState> get() = _recipeDetails
    
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
}

package com.example.flavorquest.presentation.recipeList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flavorquest.core.Resource
import com.example.flavorquest.domain.useCases.recipeUseCases.RequestUseCase
import com.example.flavorquest.presentation.RecipeListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ListViewModel(
    private val requestUseCase: RequestUseCase
) : ViewModel() {
    
    private val _recipeList = MutableStateFlow<RecipeListState>(RecipeListState.Loading)
    val recipeList: StateFlow<RecipeListState> get() = _recipeList
    
    fun getRecipes(
        query: String?,
        cuisineType: String?,
        dishType: String?
    ) {
        viewModelScope.launch {
            requestUseCase.invoke(
                query = query,
                cuisineType = cuisineType,
                dishType = dishType
            ).collectLatest { result ->
                
                when(result) {
                    is Resource.Loading -> {
                        _recipeList.value = RecipeListState.Loading
                    }
                    is Resource.Error -> {
                        result.message?.let { message ->
                            _recipeList.value = RecipeListState.Error(message)
                        }
                    }
                    is Resource.Success -> {
                        result.data?.let { recipeList ->
                            _recipeList.value = RecipeListState.Data(recipeList)
                            
                        }
                    }
                }
            }
        }
    }
}

package com.example.flavorquest.presentation.recipeList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flavorquest.core.Resource
import com.example.flavorquest.domain.useCases.GetRecipeListUseCase
import com.example.flavorquest.presentation.state.ListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ListViewModel(
    private val getRecipeListUseCase: GetRecipeListUseCase
) : ViewModel() {
    
    private val _recipeList = MutableStateFlow<ListState>(ListState.Loading)
    val recipeList: StateFlow<ListState> get() = _recipeList
    
    fun getRecipes(
        query: String?,
        cuisineType: String?,
        dishType: String?
    ) {
        viewModelScope.launch {
            getRecipeListUseCase(
                query = query,
                cuisineType = cuisineType,
                dishType = dishType
            ).collectLatest { result ->
                when (result) {
                    is Resource.Loading -> {
                        _recipeList.value = ListState.Loading
                    }
                    is Resource.Error -> {
                        result.message?.let { message ->
                            _recipeList.value = ListState.Error(message)
                        }
                    }
                    is Resource.Success -> {
                        result.data?.let { recipeList ->
                            _recipeList.value = ListState.Data(recipeList)
                        }
                    }
                }
            }
        }
    }
}

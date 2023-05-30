package com.example.flavorquest.presentation.recipeList

import android.util.Log
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
    
    init {
        getRecipes()
    }
    
    fun getRecipes(
        query: String? = null,
        cuisineType: String? = null,
        dishType: String? = null
    ) {
        viewModelScope.launch {
            Log.i("oi", "getRecipes: Query=$query, CuisineType=$cuisineType, DishType=$dishType")
            requestUseCase(
                query = query,
                cuisineType = cuisineType,
                dishType = dishType
            ).collectLatest { result ->
                Log.i("oii", "getRecipes: Result=$result")
                when (result) {
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
                            Log.i("oi", "getRecipes: $recipeList")
                        }
                    }
                }
            }
        }
    }
}

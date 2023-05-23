package com.example.flavorquest.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flavorquest.core.Resource
import com.example.flavorquest.domain.useCases.RecipeUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel(
    private val recipeUseCases: RecipeUseCases
) : ViewModel() {

    private val _recipeList = MutableStateFlow<RecipeListState>(RecipeListState.Loading)
    val recipeList: StateFlow<RecipeListState> get() = _recipeList

    fun getRecipes(
        query: String?,
        cuisineType: String?,
        dishType: String?
    ) {

        /**
         * Verificação de segurança para garantir que pelo menos um campo esteja preenchido.
         * */

        if (query.isNullOrBlank() && cuisineType.isNullOrBlank() && dishType.isNullOrBlank()) {
            _recipeList.value = RecipeListState.Error("Insira algum dado sobre a receita!")
            return
        }

        if (cuisineType.isNullOrBlank() && dishType.isNullOrBlank()) {
            viewModelScope.launch {
                recipeUseCases.getRecipeFromQuery(query).collectLatest { response ->

                    when (response) {
                        is Resource.Loading -> {
                            _recipeList.value = RecipeListState.Loading
                        }
                        is Resource.Success -> {
                            response.data?.let { recipeList ->
                                _recipeList.value = RecipeListState.Data(recipeList)
                            }
                        }
                        is Resource.Error -> {
                            response.message?.let { errorMessage ->
                                _recipeList.value = RecipeListState.Error(errorMessage)
                            }
                        }
                    }
                }
            }
        }

        if (query.isNullOrBlank() && dishType.isNullOrBlank()) {
            viewModelScope.launch {
                recipeUseCases.getRecipefromCuisine(cuisineType).collectLatest { response ->

                    when (response) {
                        is Resource.Loading -> {
                            _recipeList.value = RecipeListState.Loading
                        }
                        is Resource.Success -> {
                            response.data?.let { recipeList ->
                                _recipeList.value = RecipeListState.Data(recipeList)
                            }
                        }
                        is Resource.Error -> {
                            response.message?.let { errorMessage ->
                                _recipeList.value = RecipeListState.Error(errorMessage)
                            }
                        }
                    }
                }
            }
        }

        if (query.isNullOrBlank() && cuisineType.isNullOrBlank()) {
            viewModelScope.launch {
                recipeUseCases.getRecipefromDish(dishType).collectLatest { response ->

                    when (response) {
                        is Resource.Loading -> {
                            _recipeList.value = RecipeListState.Loading
                        }
                        is Resource.Success -> {
                            response.data?.let { recipeList ->
                                _recipeList.value = RecipeListState.Data(recipeList)
                            }
                        }
                        is Resource.Error -> {
                            response.message?.let { errorMessage ->
                                _recipeList.value = RecipeListState.Error(errorMessage)
                            }
                        }
                    }
                }
            }
        }

        if (dishType.isNullOrBlank()) {
            viewModelScope.launch {
                recipeUseCases.getRecipefromQueryCuisine(query, cuisineType)
                    .collectLatest { response ->

                        when (response) {
                            is Resource.Loading -> {
                                _recipeList.value = RecipeListState.Loading
                            }
                            is Resource.Success -> {
                                response.data?.let { recipeList ->
                                    _recipeList.value = RecipeListState.Data(recipeList)
                                }
                            }
                            is Resource.Error -> {
                                response.message?.let { errorMessage ->
                                    _recipeList.value = RecipeListState.Error(errorMessage)
                                }
                            }
                        }
                    }
            }
        }

        if (cuisineType.isNullOrBlank()) {
            viewModelScope.launch {
                recipeUseCases.getRecipefromQueryDish(query, dishType).collectLatest { response ->

                    when (response) {
                        is Resource.Loading -> {
                            _recipeList.value = RecipeListState.Loading
                        }
                        is Resource.Success -> {
                            response.data?.let { recipeList ->
                                _recipeList.value = RecipeListState.Data(recipeList)
                            }
                        }
                        is Resource.Error -> {
                            response.message?.let { errorMessage ->
                                _recipeList.value = RecipeListState.Error(errorMessage)
                            }
                        }
                    }
                }
            }
        }

        if (query.isNullOrBlank()) {
            viewModelScope.launch {
                recipeUseCases.getRecipefromCuisineDish(cuisineType, dishType)
                    .collectLatest { response ->

                        when (response) {
                            is Resource.Loading -> {
                                _recipeList.value = RecipeListState.Loading
                            }
                            is Resource.Success -> {
                                response.data?.let { recipeList ->
                                    _recipeList.value = RecipeListState.Data(recipeList)
                                }
                            }
                            is Resource.Error -> {
                                response.message?.let { errorMessage ->
                                    _recipeList.value = RecipeListState.Error(errorMessage)
                                }
                            }
                        }
                    }
            }
        }

        if (!query.isNullOrBlank() && !cuisineType.isNullOrBlank() && !dishType.isNullOrBlank()) {
            viewModelScope.launch {
                recipeUseCases.getRecipefromQueryCuisineDish(query, cuisineType, dishType)
                    .collectLatest { response ->

                        when (response) {
                            is Resource.Loading -> {
                                _recipeList.value = RecipeListState.Loading
                            }
                            is Resource.Success -> {
                                response.data?.let { recipeList ->
                                    _recipeList.value = RecipeListState.Data(recipeList)
                                }
                            }
                            is Resource.Error -> {
                                response.message?.let { errorMessage ->
                                    _recipeList.value = RecipeListState.Error(errorMessage)
                                }
                            }
                        }
                    }
            }
        }
    }
}
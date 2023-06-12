package com.example.flavorquest.presentation.recipeList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flavorquest.core.Resource
import com.example.flavorquest.domain.model.Recipe
import com.example.flavorquest.domain.useCases.FavoriteRecipesUseCases
import com.example.flavorquest.domain.useCases.GetRecipeListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ListViewModel(
    private val getRecipeListUseCase: GetRecipeListUseCase,
    private val favoriteRecipesUseCases: FavoriteRecipesUseCases
) : ViewModel() {
    
    private val _recipeList = MutableStateFlow<ListState>(ListState.Loading)
    val recipeList: StateFlow<ListState> get() = _recipeList
    
    
    fun onEvent(
        event: ListEvent
    ) {
        when (event) {
            is ListEvent.OnLoadList -> {
                viewModelScope.launch {
                    getRecipeListUseCase(
                        query = event.query,
                        cuisineType = event.cuisineType,
                        dishType = event.dishType
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
                                    val filteredRecipeList = recipeList.map { recipe ->
                                        val isFavorite =
                                            favoriteRecipesUseCases.isFavoriteRecipeUseCase(recipe.id)
                                        Recipe(
                                            recipe.id,
                                            recipe.name,
                                            recipe.imageUrl,
                                            recipe.ingredients,
                                            recipe.cuisineType,
                                            recipe.mealType,
                                            recipe.dishType,
                                            recipe.diet,
                                            recipe.source,
                                            recipe.url
                                        ).apply {
                                            this.isFavorite = isFavorite
                                        }
                                    }
                                    _recipeList.value = ListState.Data(filteredRecipeList)
                                }
                            }
                        }
                    }
                }
            }
            is ListEvent.OnFavoriteClick -> {
                viewModelScope.launch {
                    favoriteRecipesUseCases.saveOrRemoveRecipeUseCase(event.recipe)
                }
            }
        }
    }
}

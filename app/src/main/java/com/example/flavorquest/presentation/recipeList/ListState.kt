package com.example.flavorquest.presentation.recipeList

import com.example.flavorquest.domain.model.Recipe

sealed class ListState {
    class Data(val recipeList: List<Recipe>) : ListState()
    class Error(val message: String) : ListState()
    object Loading : ListState()
}

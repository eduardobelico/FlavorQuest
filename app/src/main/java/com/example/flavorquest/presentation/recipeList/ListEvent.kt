package com.example.flavorquest.presentation.recipeList

import com.example.flavorquest.domain.model.Recipe

sealed class ListEvent {
    data class OnLoadList(
        val query: String?,
        val cuisineType: String?,
        val dishType: String?
    ) : ListEvent()
    
    data class OnFavoriteClick(val recipe: Recipe) : ListEvent()
}

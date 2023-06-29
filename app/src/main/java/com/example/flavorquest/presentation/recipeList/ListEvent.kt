package com.example.flavorquest.presentation.recipeList

sealed class ListEvent {
    data class OnLoadList(
        val query: String?,
        val cuisineType: String?,
        val dishType: String?
    ) : ListEvent()
}

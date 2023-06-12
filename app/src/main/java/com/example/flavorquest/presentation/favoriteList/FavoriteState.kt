package com.example.flavorquest.presentation.favoriteList

import com.example.flavorquest.domain.model.Recipe

sealed class FavoriteState {
    class Data(val favoritesList: List<Recipe>) : FavoriteState()
    class Error(val message: String) : FavoriteState()
    object Loading : FavoriteState()
}

package com.example.flavorquest.presentation.favoriteList

import com.example.flavorquest.domain.model.Recipe

sealed class FavoriteEvent {
object OnLoadRecipeList : FavoriteEvent()
data class OnFavoriteClick(val recipe: Recipe): FavoriteEvent()
}

package com.example.flavorquest.presentation.state

import com.example.flavorquest.domain.model.Recipe

sealed class DetailsState {
    data class Data(val data: Recipe) : DetailsState()
    data class Error(val message: String) : DetailsState()
    object Loading : DetailsState()
}

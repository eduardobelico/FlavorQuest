package com.example.flavorquest.presentation.home

import androidx.lifecycle.ViewModel
import com.example.flavorquest.presentation.state.HomeState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel : ViewModel() {
    
    private val _state = MutableStateFlow<HomeState>(HomeState.Empty)
    val state: StateFlow<HomeState> get() = _state
    
    fun checkParameters(
        query: String?,
        cuisineType: String?,
        dishType: String?
    ) {
        if (query.isNullOrBlank() && cuisineType.isNullOrBlank() && dishType.isNullOrBlank()) {
            _state.value = HomeState.Error("Insira algum dado sobre a receita!")
        } else {
            _state.value = HomeState.Success
        }
    }
    
    fun stateRefresh() {
        _state.value = HomeState.Empty
    }
}
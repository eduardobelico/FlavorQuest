package com.example.flavorquest.presentation.home

sealed class HomeState {
    object Success : HomeState()
    class Error(val message: String) : HomeState()
    object Empty : HomeState()
}

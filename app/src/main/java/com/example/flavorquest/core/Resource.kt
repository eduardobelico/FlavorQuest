package com.example.flavorquest.core

/**
 * Classe para tratamento utlizado no repository
 * */

sealed class Resource<out T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?) : Resource<T>(data)
    class Error<T>(message: String? = null, data: T? = null) : Resource<T>(data, message)
    class Loading<T>(data: T? = null) : Resource<T>(data)
}

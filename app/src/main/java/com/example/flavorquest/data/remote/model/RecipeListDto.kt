package com.example.flavorquest.data.remote.model

import com.google.gson.annotations.SerializedName

/**
 * Modelo de como os dados da lista de Receitas são recebidos da API.
 * */

data class RecipeListDto(
    @SerializedName("recipe")
    val recipe: RecipeDto
)
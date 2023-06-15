package com.example.flavorquest.data.remote.model

import com.google.gson.annotations.SerializedName

/**
 * Modelo de como os dados do pedido são recebidos da API.
 * */

data class RecipeRequest(
    @SerializedName("hits")
    val recipeList: List<RecipeListDto>
)

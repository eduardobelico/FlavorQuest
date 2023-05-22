package com.example.flavorquest.data.remote.network

import com.example.flavorquest.core.Constants.APP_ID
import com.example.flavorquest.core.Constants.APP_KEY
import com.example.flavorquest.core.Constants.TYPE
import com.example.flavorquest.data.remote.model.RecipeRequest
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeServices {

    @GET("api/recipes/v2")
    suspend fun getRecipeFromQuery(
        @Query("type") type: String = TYPE,
        @Query("app_id") appId: String = APP_ID,
        @Query("app_key") appKey: String = APP_KEY,
        @Query("q") query: String
    ): RecipeRequest

    @GET("api/recipes/v2")
    suspend fun getRecipeFromCuisine(
        @Query("type") type: String = TYPE,
        @Query("app_id") appId: String = APP_ID,
        @Query("app_key") appKey: String = APP_KEY,
        @Query("cuisineType") cuisineType: String
    ): RecipeRequest

    @GET("api/recipes/v2")
    suspend fun getRecipeFromDish(
        @Query("type") type: String = TYPE,
        @Query("app_id") appId: String = APP_ID,
        @Query("app_key") appKey: String = APP_KEY,
        @Query("dishType") dishType: String
    ): RecipeRequest

    @GET("api/recipes/v2")
    suspend fun getRecipeFromQueryCuisine(
        @Query("type") type: String = TYPE,
        @Query("app_id") appId: String = APP_ID,
        @Query("app_key") appKey: String = APP_KEY,
        @Query("q") query: String,
        @Query("cuisineType") cuisineType: String
    ): RecipeRequest

    @GET("api/recipes/v2")
    suspend fun getRecipeFromQueryDish(
        @Query("type") type: String = TYPE,
        @Query("app_id") appId: String = APP_ID,
        @Query("app_key") appKey: String = APP_KEY,
        @Query("q") query: String,
        @Query("dishType") dishType: String
    ): RecipeRequest

    @GET("api/recipes/v2")
    suspend fun getRecipeFromCuisineDish(
        @Query("type") type: String = TYPE,
        @Query("app_id") appId: String = APP_ID,
        @Query("app_key") appKey: String = APP_KEY,
        @Query("cuisineType") cuisineType: String,
        @Query("dishType") dishType: String
    ): RecipeRequest

    @GET("api/recipes/v2")
    suspend fun getRecipeFromQueryCuisineDish(
        @Query("type") type: String = TYPE,
        @Query("app_id") appId: String = APP_ID,
        @Query("app_key") appKey: String = APP_KEY,
        @Query("q") query: String,
        @Query("cuisineType") cuisineType: String,
        @Query("dishType") dishType: String
    ): RecipeRequest

}
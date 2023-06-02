package com.example.flavorquest.data.remote.network

import com.example.flavorquest.core.Constants.APP_ID
import com.example.flavorquest.core.Constants.APP_KEY
import com.example.flavorquest.core.Constants.TYPE
import com.example.flavorquest.data.remote.model.RecipeListDto
import com.example.flavorquest.data.remote.model.RecipeRequest
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeServices {
    
    @GET("api/recipes/v2")
    suspend fun getRecipeFromQuery(
        @Query("q") query: String,
        @Query("type") type: String = TYPE,
        @Query("app_id") appId: String = APP_ID,
        @Query("app_key") appKey: String = APP_KEY
    ): RecipeRequest
    
    @GET("api/recipes/v2")
    suspend fun getRecipeFromCuisine(
        @Query("cuisineType") cuisineType: String,
        @Query("type") type: String = TYPE,
        @Query("app_id") appId: String = APP_ID,
        @Query("app_key") appKey: String = APP_KEY
    ): RecipeRequest
    
    @GET("api/recipes/v2")
    suspend fun getRecipeFromDish(
        @Query("dishType") dishType: String,
        @Query("type") type: String = TYPE,
        @Query("app_id") appId: String = APP_ID,
        @Query("app_key") appKey: String = APP_KEY
    ): RecipeRequest
    
    @GET("api/recipes/v2")
    suspend fun getRecipeFromQueryCuisine(
        @Query("q") query: String,
        @Query("cuisineType") cuisineType: String,
        @Query("type") type: String = TYPE,
        @Query("app_id") appId: String = APP_ID,
        @Query("app_key") appKey: String = APP_KEY
    ): RecipeRequest
    
    @GET("api/recipes/v2")
    suspend fun getRecipeFromQueryDish(
        @Query("q") query: String,
        @Query("dishType") dishType: String,
        @Query("type") type: String = TYPE,
        @Query("app_id") appId: String = APP_ID,
        @Query("app_key") appKey: String = APP_KEY
    ): RecipeRequest
    
    @GET("api/recipes/v2")
    suspend fun getRecipeFromCuisineDish(
        @Query("cuisineType") cuisineType: String,
        @Query("dishType") dishType: String,
        @Query("type") type: String = TYPE,
        @Query("app_id") appId: String = APP_ID,
        @Query("app_key") appKey: String = APP_KEY
    ): RecipeRequest
    
    @GET("api/recipes/v2")
    suspend fun getRecipeFromQueryCuisineDish(
        @Query("q") query: String,
        @Query("cuisineType") cuisineType: String,
        @Query("dishType") dishType: String,
        @Query("type") type: String = TYPE,
        @Query("app_id") appId: String = APP_ID,
        @Query("app_key") appKey: String = APP_KEY
    ): RecipeRequest
    
    @GET("api/recipes/v2/")
    suspend fun getRecipeFromId(
        @Query("id") id: String,
        @Query("type") type: String = TYPE,
        @Query("app_id") appId: String = APP_ID,
        @Query("app_key") appKey: String = APP_KEY
    ): RecipeListDto
    
}
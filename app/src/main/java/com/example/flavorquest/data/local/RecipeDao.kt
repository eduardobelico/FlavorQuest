package com.example.flavorquest.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

/**
 * Classe de controle do banco de dados pelo Room
 * */

@Dao
interface RecipeDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveRecipe(recipe: RecipeEntity)
    
    @Delete
    suspend fun deleteRecipe(recipe: RecipeEntity)
    
    @Query("SELECT * FROM RecipeEntity")
    fun getFavoriteRecipes(): Flow<List<RecipeEntity>>
}
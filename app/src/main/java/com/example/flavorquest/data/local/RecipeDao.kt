package com.example.flavorquest.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

/**
 * Classe de controle do banco de dados pelo Room.
 * */

@Dao
interface RecipeDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveRecipe(recipe: RecipeEntity)
    
    @Query("DELETE FROM RecipeEntity WHERE id = :id")
    suspend fun removeRecipe(id: String)
    
    @Query("SELECT * FROM RecipeEntity")
    fun getFavoriteRecipes(): Flow<List<RecipeEntity>>
    
    @Query("SELECT * FROM RecipeEntity WHERE id = :id")
    suspend fun isFavorite(id: String): List<RecipeEntity>
    
    @Query("SELECT COUNT(*) FROM RecipeEntity")
    fun getFavoriteRecipesCount(): Flow<Int>
}
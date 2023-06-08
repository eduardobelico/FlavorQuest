package com.example.flavorquest.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy

/**
 * Classe de controle do banco de dados pelo Room
 * */

@Dao
interface RecipeDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveRecipe(recipe: RecipeEntity)
    
    @Delete
    suspend fun deleteRecipe(recipe: RecipeEntity)
}
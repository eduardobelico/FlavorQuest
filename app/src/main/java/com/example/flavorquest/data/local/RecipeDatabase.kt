package com.example.flavorquest.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.flavorquest.data.local.converters.ListToStringConverters

@Database(
    entities = [RecipeEntity::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(ListToStringConverters::class)
abstract class RecipeDatabase : RoomDatabase() {
    
    abstract val dao: RecipeDao
    
    companion object {
        
        @Volatile
        private var dbInstance: RecipeDatabase? = null
        
        fun getInstance(context: Context): RecipeDatabase {
            synchronized(this) {
                var instance = dbInstance
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        RecipeDatabase::class.java,
                        "recipe_database"
                    ).build()
                    dbInstance = instance
                }
                return instance
            }
        }
    }
}
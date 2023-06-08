package com.example.flavorquest.domain.useCases

import com.example.flavorquest.domain.model.Recipe
import com.example.flavorquest.domain.repository.RecipeRepository

class SaveRecipeUseCase(
    private val repository: RecipeRepository
) {
    
    suspend operator fun invoke(recipe: Recipe) {
        repository.insertRecipe(recipe)
    }
}
//    suspend operator fun invoke(
//        id: String,
//        name: String,
//        imageUrl: String?,
//        ingredients: List<String>,
//        cuisineType: List<String>,
//        mealType: List<String>,
//        dishType: List<String>,
//        diet: List<String>,
//        source: String,
//        url: String?
//    ) {
//        val recipe = Recipe(
//            id = id,
//            name = name,
//            imageUrl = imageUrl,
//            ingredients = ingredients,
//            cuisineType = cuisineType,
//            mealType = mealType,
//            dishType = dishType,
//            diet = diet,
//            source = source,
//            url = url
//        )
//        repository.insertRecipe(recipe)
//    }
//}
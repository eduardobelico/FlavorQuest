package com.example.flavorquest.domain.useCases

import com.example.flavorquest.core.Resource
import com.example.flavorquest.domain.model.Recipe
import com.example.flavorquest.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow

class GetRecipeListUseCase(
    private val repository: RecipeRepository
) {
    
    private val cuisineTypeMap = mapOf(
        "Americana" to "american",
        "Asiática" to "asian",
        "Britânica" to "british",
        "Caribenha" to "caribbean",
        "Centro-Europeia" to "central europe",
        "Chinesa" to "chinese",
        "Francesa" to "french",
        "Indiana" to "indian",
        "Italiana" to "italian",
        "Japonesa" to "japanese",
        "Kosher" to "kosher",
        "Leste-Europeia" to "eastern europe",
        "Médio-Oriental" to "middle eastern",
        "Mediterrânea" to "mediterranean",
        "Mexicana" to "mexican",
        "Nórdica" to "nordic",
        "Sulamericana" to "south american",
        "Sul-asiática" to "south east asian"
    )
    
    
    private val dishTypeMap = mapOf(
        "Acompanhamentos" to "side dish",
        "Biscoitos" to "biscuits and cookies",
        "Cereais" to "cereals",
        "Condimentos" to "condiments and sauces",
        "Doces" to "desserts",
        "Drinks" to "drinks",
        "Entradas" to "starter",
        "Pães" to "bread",
        "Panquecas" to "pancake",
        "Prato Principal" to "main course",
        "Preparativos" to "preps",
        "Preservados" to "preserve",
        "Saladas" to "salad",
        "Sanduíches" to "sandwiches",
        "Sobremesas" to "desserts",
        "Sopas" to "soup"
    )
    
    operator fun invoke(
        query: String?,
        cuisineType: String?,
        dishType: String?
    ): Flow<Resource<List<Recipe>>> {
    
        val mappedCuisineType = cuisineTypeMap[cuisineType] ?: ""
        val mappedDishType = dishTypeMap[dishType] ?: ""
        
        return if (cuisineType.isNullOrBlank() && dishType.isNullOrBlank() && !query.isNullOrBlank()) {
            repository.getRecipeFromQuery(
                query = query
            )
        } else if (!cuisineType.isNullOrBlank() && dishType.isNullOrBlank() && query.isNullOrBlank()) {
            repository.getRecipeFromCuisine(
                cuisineType = mappedCuisineType
            )
        } else if (cuisineType.isNullOrBlank() && !dishType.isNullOrBlank() && query.isNullOrBlank()) {
            repository.getRecipeFromDish(
                dishType = mappedDishType
            )
        } else if (!cuisineType.isNullOrBlank() && dishType.isNullOrBlank() && !query.isNullOrBlank()) {
            repository.getRecipeFromQueryCuisine(
                query = query,
                cuisineType = mappedCuisineType
            )
        } else if (cuisineType.isNullOrBlank() && !dishType.isNullOrBlank() && !query.isNullOrBlank()) {
            repository.getRecipeFromQueryDish(
                query = query,
                dishType = mappedDishType
            )
        } else if (!cuisineType.isNullOrBlank() && !dishType.isNullOrBlank() && query.isNullOrBlank()) {
            repository.getRecipeFromCuisineDish(
                cuisineType = mappedCuisineType,
                dishType = mappedDishType
            )
        } else {
            repository.getRecipeFromQueryCuisineDish(
                query = query!!,
                cuisineType = mappedCuisineType,
                dishType = mappedDishType
            )
        }
    }
}
package com.example.flavorquest.domain.useCases

import com.example.flavorquest.core.Resource
import com.example.flavorquest.domain.model.Recipe
import com.example.flavorquest.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow

/**
 * Classe que faz a requisição ao repositório para coletar a lista de receitas enviando query,
 * cuisineType e dishType como parâmetros.
 **/

class GetRecipeListUseCase(
    private val repository: RecipeRepository
) {
    
    /**
     * Mapeamento das traduções para enviar as informações corretas à API de acordo com
     * a seleção de tipo e culinária realizada pelo usuário.
     **/
    
    private val cuisineTypeMap = mapOf(
        "Americana" to "american",
        "Asiática" to "asian",
        "Britânica" to "british",
        "Caribenha" to "caribbean",
        "Europa Central" to "central europe",
        "Chinesa" to "chinese",
        "Francesa" to "french",
        "Indiana" to "indian",
        "Italiana" to "italian",
        "Japonesa" to "japanese",
        "Kosher" to "kosher",
        "Europa Oriental" to "eastern europe",
        "Oriente Médio" to "middle eastern",
        "Mediterrânea" to "mediterranean",
        "Mexicana" to "mexican",
        "Nórdica" to "nordic",
        "Sulamericana" to "south american",
        "Sul-asiática" to "south east asian",
        "nenhuma" to null
    
    )
    
    private val dishTypeMap = mapOf(
        "Biscoitos e Cookies" to "biscuits and cookies",
        "Cereais" to "cereals",
        "Condimentos e Molhos" to "condiments and sauces",
        "Doces" to "desserts",
        "Bebidas" to "drinks",
        "Entradas" to "starter",
        "Pães" to "bread",
        "Panquecas" to "pancake",
        "Prato Principal" to "main course",
        "Preparativos" to "preps",
        "Preservados" to "preserve",
        "Saladas" to "salad",
        "Sanduíches" to "sandwiches",
        "Sobremesas" to "desserts",
        "Sopas" to "soup",
        "nenhum" to null
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
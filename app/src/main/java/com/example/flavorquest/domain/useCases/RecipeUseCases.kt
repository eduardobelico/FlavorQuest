package com.example.flavorquest.domain.useCases

import com.example.flavorquest.domain.useCases.recipeUseCases.*

data class RecipeUseCases(
    val getRecipeListFromQuery: GetFromQueryUseCase,
    val getRecipeListFromCuisine: GetFromCuisineUseCase,
    val getRecipeListFromDish: GetFromDishUseCase,
    val getRecipeListFromQueryCuisine: GetFromQueryCuisineUseCase,
    val getRecipeListFromQueryDish: GetFromQueryDishUseCase,
    val getRecipeListFromCuisineDish: GetFromCuisineDishUseCase,
    val getRecipeListFromQueryCuisineDish: GetFromQueryCuisineDishUseCase
    )

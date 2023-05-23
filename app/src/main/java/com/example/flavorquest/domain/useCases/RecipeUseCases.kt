package com.example.flavorquest.domain.useCases

import com.example.flavorquest.domain.useCases.recipeUseCases.*

data class RecipeUseCases(
    val getRecipeFromQuery: GetFromQueryUseCase,
    val getRecipefromCuisine: GetFromCuisineUseCase,
    val getRecipefromDish: GetFromDishUseCase,
    val getRecipefromQueryCuisine: GetFromQueryCuisineUseCase,
    val getRecipefromQueryDish: GetFromQueryDishUseCase,
    val getRecipefromCuisineDish: GetFromCuisineDishUseCase,
    val getRecipefromQueryCuisineDish: GetFromQueryCuisineDishUseCase
)
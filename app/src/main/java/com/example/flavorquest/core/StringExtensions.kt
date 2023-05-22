package com.example.flavorquest.core

import com.example.flavorquest.core.Constants.BASE_RECIPE_URI

fun String.getRecipeId(): String? {
    return if (this.contains(BASE_RECIPE_URI)) {
        this.replace(BASE_RECIPE_URI, "")
    } else {
        null
    }
}
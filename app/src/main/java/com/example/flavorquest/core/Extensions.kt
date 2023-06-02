package com.example.flavorquest.core

import android.view.View
import android.widget.ImageView
import coil.load
import com.example.flavorquest.R
import com.example.flavorquest.core.Constants.BASE_RECIPE_URI
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.Translator
import com.google.mlkit.nl.translate.TranslatorOptions

fun String.getRecipeId(): String {
   return this.replace(BASE_RECIPE_URI, "")
}

fun String.removeBrackets(): String {
   return this.replace("[", "").replace("]", "")
}

fun ImageView.loadImage(url: String? = null) {
   if (url != null) {
      visibilityVisible()
      
      load(url) {
         error(R.drawable.erro)
         placeholder(R.drawable.placeholder)
         crossfade(1000)
      }
   }
}

/**
 * Trabalhando com a visibilidade de uma view em 3 diferentes estados.
 * */

fun View.visibilityVisible() {
   this.visibility = View.VISIBLE
}

fun View.visibilityGone() {
   this.visibility = View.GONE
}

fun View.visibilityInvisible() {
   this.visibility = View.INVISIBLE
}

fun createTranslator(): Translator {
   val options = TranslatorOptions.Builder()
      .setSourceLanguage(TranslateLanguage.ENGLISH)
      .setTargetLanguage(TranslateLanguage.PORTUGUESE)
      .build()
   
   return Translation.getClient(options)
}
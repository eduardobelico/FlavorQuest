package com.example.flavorquest.presentation.recipeDetails

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flavorquest.core.loadImage
import com.example.flavorquest.core.removeBrackets
import com.example.flavorquest.databinding.RecipeDetailsItemBinding
import com.example.flavorquest.domain.model.Recipe

class DetailsAdapter : RecyclerView.Adapter<DetailsAdapter.DetailsViewHolder>() {
    
    inner class DetailsViewHolder(val binding: RecipeDetailsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var recipe: Recipe
        
        fun bindView(recipe: Recipe) {
            this.recipe = recipe
            with(binding) {
                detailsImageUrl.loadImage(recipe.imageUrl)
                detailsName.text = recipe.name
                detailsDishType.text = recipe.dishType.toString().removeBrackets()
                detailsCuisineType.text = recipe.cuisineType.toString().removeBrackets()
                detailsMealType.text = recipe.mealType.toString().removeBrackets()
                detailsIngredients.text = recipe.ingredients.toString().removeBrackets()
                detailsSource.text = recipe.source
                detailsUrl.text = recipe.url
            }
        }
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        TODO("Not yet implemented")
    }
    
    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
    
    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}
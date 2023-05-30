package com.example.flavorquest.presentation.recipeList

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.flavorquest.core.loadImage
import com.example.flavorquest.databinding.RecipeCardBinding
import com.example.flavorquest.domain.model.Recipe

class RecipeAdapter : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {
    
    private var recipeList = emptyList<Recipe>()
    
    inner class RecipeViewHolder(val binding: RecipeCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var recipe: Recipe
        
        fun bindView(recipe: Recipe) {
            this.recipe = recipe
            with(binding) {
                recipeImageUrl.loadImage(recipe.imageUrl)
                recipeName.text = recipe.name
                recipeDishType.text = recipe.dishType.toString()
                recipeCuisineType.text = recipe.cuisineType.toString()
            }
            Log.i("oi", "bindView: $recipe")
        }
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecipeCardBinding.inflate(inflater, parent, false)
        Log.i("oi", "onCreateViewHolder")
        
        return RecipeViewHolder(binding)
    }
    
    override fun getItemCount(): Int = recipeList.size
    
    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bindView(recipeList[position])
        Log.i("oi", "onBindViewHolder: ${recipeList[position]}")
    }
    
    fun setData(newRecipeList: List<Recipe>) {
        val filteredList = newRecipeList.filter { it.name != "" }
        val diffResults = DiffUtil.calculateDiff(RecipeDiffUtil(recipeList, filteredList))
        recipeList = filteredList
        diffResults.dispatchUpdatesTo(this)
        Log.i("oi", "setData: $recipeList")
    }
    
    class RecipeDiffUtil(
        private val oldRecipeList: List<Recipe>,
        private val newRecipeList: List<Recipe>
    ) : DiffUtil.Callback() {
        override fun getOldListSize() = oldRecipeList.size
        override fun getNewListSize() = newRecipeList.size
        
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldRecipeList[oldItemPosition].id == newRecipeList[newItemPosition].id
        
        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldRecipeList[oldItemPosition] == newRecipeList[newItemPosition]
    }
}
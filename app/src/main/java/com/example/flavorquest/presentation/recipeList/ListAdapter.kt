package com.example.flavorquest.presentation.recipeList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.flavorquest.core.loadImage
import com.example.flavorquest.core.removeBrackets
import com.example.flavorquest.databinding.RecipeListItemBinding
import com.example.flavorquest.domain.model.Recipe

class ListAdapter(
    var selectedRecipe: (recipe: Recipe) -> Unit = {}
) : RecyclerView.Adapter<ListAdapter.RecipeViewHolder>() {
    
    private var recipeList = emptyList<Recipe>()
    
    inner class RecipeViewHolder(val binding: RecipeListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var recipe: Recipe
        
        init {
            itemView.setOnClickListener {
                if (::recipe.isInitialized) {
                    selectedRecipe(recipe)
                }
            }
        }
        
        fun bindView(recipe: Recipe) {
            this.recipe = recipe
            with(binding) {
                recipeImageUrl.loadImage(recipe.imageUrl)
                recipeName.text = recipe.name
                recipeDishType.text = recipe.dishType.toString().removeBrackets()
                recipeCuisineType.text = recipe.cuisineType.toString().removeBrackets()
            }
        }
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecipeListItemBinding.inflate(inflater, parent, false)
        
        return RecipeViewHolder(binding)
    }
    
    override fun getItemCount(): Int = recipeList.size
    
    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bindView(recipeList[position])
    }
    
    fun setData(newRecipeList: List<Recipe>) {
        val diffResults = DiffUtil.calculateDiff(RecipeDiffUtil(recipeList, newRecipeList))
        recipeList = newRecipeList
        diffResults.dispatchUpdatesTo(this)
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
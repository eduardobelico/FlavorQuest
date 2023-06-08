package com.example.flavorquest.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.flavorquest.core.TranslatorFactory
import com.example.flavorquest.core.loadImage
import com.example.flavorquest.core.removeBrackets
import com.example.flavorquest.databinding.RecipeListItemBinding
import com.example.flavorquest.domain.model.Recipe

class ListAdapter(
    var selectedRecipe: (recipe: Recipe) -> Unit = {},
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
            val englishPortugueseTranslator =
                TranslatorFactory.createEnglishToPortugueseTranslator()
            
            with(binding) {
                recipeImageUrl.loadImage(recipe.imageUrl)
                val name = recipe.name
                englishPortugueseTranslator.translate(name)
                    .addOnSuccessListener { translatedText ->
                        recipeName.text = translatedText
                    }
                    .addOnFailureListener { _ ->
                        recipeName.text = name
                    }
                val dishType =
                    recipe.dishType.toString().removeBrackets().replaceFirstChar { it.uppercase() }
                englishPortugueseTranslator.translate(dishType)
                    .addOnSuccessListener { translatedText ->
                        recipeDishType.text = translatedText
                    }
                    .addOnFailureListener { _ ->
                        recipeDishType.text = dishType
                    }
                val cuisineType = recipe.cuisineType.toString().removeBrackets()
                    .replaceFirstChar { it.uppercase() }
                englishPortugueseTranslator.translate(cuisineType)
                    .addOnSuccessListener { translatedText ->
                        val modifiedText = translatedText
                            .replace("Americano", "Americana")
                            .replace("Sul americano", "Sulamericana")
                            .replace("Mediterrâneo", "Mediterrânea")
                            .replace("Francês", "Francesa")
                            .replace("Mundo", "Global")
                        recipeCuisineType.text = modifiedText
                    }
                    .addOnFailureListener { _ ->
                        recipeCuisineType.text = cuisineType
                    }
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
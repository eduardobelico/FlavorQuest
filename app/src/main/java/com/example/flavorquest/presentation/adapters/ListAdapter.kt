package com.example.flavorquest.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.flavorquest.R
import com.example.flavorquest.core.Extensions.*
import com.example.flavorquest.databinding.RecipeListItemBinding
import com.example.flavorquest.presentation.recipeList.RecipeUiState

/**
 * Adapter do recyclerview usado para a lista de resultado da busca de receita
 * e para a lista de favoritos.
 **/

class ListAdapter(
    var selectedRecipe: (recipe: RecipeUiState) -> Unit = {},
    var addOrRemove: (recipe: RecipeUiState) -> Unit = {}
) : RecyclerView.Adapter<ListAdapter.RecipeViewHolder>() {
    
    private var recipeList = emptyList<RecipeUiState>()
    var showFavoriteIcon: Boolean = true
    
    inner class RecipeViewHolder(val binding: RecipeListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var recipe: RecipeUiState
        
        init {
            itemView.setOnClickListener {
                if (::recipe.isInitialized) {
                    selectedRecipe(recipe)
                }
            }
            binding.favoriteIcon.setOnClickListener {
                if (::recipe.isInitialized) {
                    addOrRemove(recipe)
                    val position = adapterPosition
                    val drawableResId = if (recipe.isFavorite) {
                        R.drawable.save_icon
                    } else {
                        R.drawable.save_icon_unselected
                    }
                    
                    if (position != RecyclerView.NO_POSITION) {
                        binding.favoriteIcon.setBackgroundResource(drawableResId)
                        notifyItemChanged(adapterPosition)
                    }
                }
            }
        }
    
        /**
         * Binding dos detalhes de receita com as modificações necessárias
         * e utilizando o tradutor para entregar o resultado em português.
         */

        fun bindView(recipeState: RecipeUiState) {
            this.recipe = recipeState
            val englishPortugueseTranslator =
                TranslatorFactory.createEnglishToPortugueseTranslator()
            
            with(binding) {
                recipeImageUrl.loadImage(recipeState.recipe.imageUrl)
                val name = recipeState.recipe.name
                englishPortugueseTranslator.translate(name)
                    .addOnSuccessListener { translatedText ->
                        recipeName.text = translatedText
                    }
                    .addOnFailureListener { _ ->
                        recipeName.text = name
                    }
                val dishType =
                    recipeState.recipe.dishType.toString().removeBrackets()
                        .replaceFirstChar { it.uppercase() }
                englishPortugueseTranslator.translate(dishType)
                    .addOnSuccessListener { translatedText ->
                        val modifiedTranslatedText = adjustTranslatedDishType(translatedText)
                        recipeDishType.text = modifiedTranslatedText
                    }
                    .addOnFailureListener { _ ->
                        recipeDishType.text = dishType
                    }
                val cuisineType = recipeState.recipe.cuisineType.toString().removeBrackets()
                    .replaceFirstChar { it.uppercase() }
                englishPortugueseTranslator.translate(cuisineType)
                    .addOnSuccessListener { translatedText ->
                        val modifiedTranslatedText = adjustTranslatedCuisineType(translatedText)
                        recipeCuisineType.text = modifiedTranslatedText
                    }
                    .addOnFailureListener { _ ->
                        recipeCuisineType.text = cuisineType
                    }
                if (showFavoriteIcon) {
                    binding.favoriteIcon.visibility = View.VISIBLE
                    if (recipeState.isFavorite)
                        binding.favoriteIcon.setBackgroundResource(R.drawable.save_icon)
                    else
                        binding.favoriteIcon.setBackgroundResource(R.drawable.save_icon_unselected)
                } else {
                    binding.favoriteIcon.visibility = View.GONE
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
    
    /**
     * Atualização do recyclerview para obter a versão mais recente da lista.
     */
    
    fun setData(newRecipeList: List<RecipeUiState>) {
        val diffResults = DiffUtil.calculateDiff(RecipeDiffUtil(recipeList, newRecipeList))
        recipeList = newRecipeList
        diffResults.dispatchUpdatesTo(this)
    }
    
    class RecipeDiffUtil(
        private val oldRecipeList: List<RecipeUiState>,
        private val newRecipeList: List<RecipeUiState>
    ) : DiffUtil.Callback() {
        override fun getOldListSize() = oldRecipeList.size
        override fun getNewListSize() = newRecipeList.size
        
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldRecipeList[oldItemPosition].recipe.id == newRecipeList[newItemPosition].recipe.id
        
        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldRecipeList[oldItemPosition] == newRecipeList[newItemPosition]
    }
}
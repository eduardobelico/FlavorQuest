package com.example.flavorquest.presentation.recipeDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.flavorquest.core.loadImage
import com.example.flavorquest.core.removeBrackets
import com.example.flavorquest.core.visibilityGone
import com.example.flavorquest.core.visibilityVisible
import com.example.flavorquest.databinding.FragmentRecipeDetailsBinding
import com.example.flavorquest.domain.model.Recipe
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : Fragment() {
    
    private var _binding: FragmentRecipeDetailsBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel by viewModel<DetailsViewModel>()
    private val args by navArgs<DetailsFragmentArgs>()
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipeDetailsBinding.inflate(inflater, container, false)
        getDetailsArgs()
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecipeDetails()
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    
    private fun getDetailsArgs() {
        viewModel.getRecipeDetails(id = args.recipeId)
    }
    
    private fun initRecipeDetails() {
        lifecycleScope.launch {
            viewModel.recipeDetails.collect { state ->
                when (state) {
                    is DetailsState.Data -> {
                        val recipe = state.data
                        with(binding.searchError) {
                            errorMessage.visibilityGone()
                            progressBar.visibilityGone()
                        }
                        bindView(recipe)
                    }
                    is DetailsState.Error -> {
                        with(binding.searchError) {
                            errorMessage.visibilityVisible()
                            progressBar.visibilityGone()
                            errorMessage.text = state.message
                        }
                    }
                    DetailsState.Loading -> {
                        with(binding.searchError) {
                            errorMessage.visibilityGone()
                            progressBar.visibilityVisible()
                        }
                    }
                }
            }
        }
    }
    
    private fun bindView(recipe: Recipe) {
        with(binding) {
            detailsImageUrl.loadImage(recipe.imageUrl)
            detailsName.text = recipe.name
            detailsDishType.text = recipe.dishType.toString().removeBrackets().replaceFirstChar { it.uppercase() }
            detailsCuisineType.text = recipe.cuisineType.toString().removeBrackets().replaceFirstChar { it.uppercase() }
            detailsMealType.text = recipe.mealType.toString().removeBrackets().replaceFirstChar { it.uppercase() }
            detailsIngredients.text = recipe.ingredients.joinToString("\n").removeBrackets()
            detailsDiet.text = recipe.diet.toString().removeBrackets().replaceFirstChar { it.uppercase() }
            detailsSource.text = recipe.source
            detailsUrl.text = recipe.url
        }
    }
}

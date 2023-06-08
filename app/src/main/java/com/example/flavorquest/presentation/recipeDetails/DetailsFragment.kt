package com.example.flavorquest.presentation.recipeDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.flavorquest.core.*
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
        val englishPortugueseTranslator = TranslatorFactory.createEnglishToPortugueseTranslator()
        
        with(binding) {
            detailsImageUrl.loadImage(recipe.imageUrl)
            val name = recipe.name
            englishPortugueseTranslator.translate(name)
                .addOnSuccessListener { translatedText ->
                    detailsName.text = translatedText
                }
                .addOnFailureListener { _ ->
                    detailsName.text = name
                }
            val dishType =
                recipe.dishType.toString().removeBrackets().replaceFirstChar { it.uppercase() }
            englishPortugueseTranslator.translate(dishType)
                .addOnSuccessListener { translatedText ->
                    detailsDishType.text = translatedText
                }
                .addOnFailureListener { _ ->
                    detailsDishType.text = dishType
                }
            val cuisineType =
                recipe.cuisineType.toString().removeBrackets().replaceFirstChar { it.uppercase() }
            englishPortugueseTranslator.translate(cuisineType)
                .addOnSuccessListener { translatedText ->
                    detailsCuisineType.text = translatedText
                }
                .addOnFailureListener { _ ->
                    detailsCuisineType.text = cuisineType
                }
            val mealType =
                recipe.mealType.toString().removeBrackets().replaceFirstChar { it.uppercase() }
            englishPortugueseTranslator.translate(mealType)
                .addOnSuccessListener { translatedText ->
                    detailsMealType.text = translatedText
                }
                .addOnFailureListener { _ ->
                    detailsMealType.text = mealType
                }
            val ingredients = recipe.ingredients.joinToString("\n").removeBrackets()
            englishPortugueseTranslator.translate(ingredients)
                .addOnSuccessListener { translatedText ->
                    detailsIngredients.text = translatedText
                }
                .addOnFailureListener { _ ->
                    detailsIngredients.text = ingredients
                }
            val diet =
                recipe.diet.joinToString("\n").removeBrackets().replaceFirstChar { it.uppercase() }
            englishPortugueseTranslator.translate(diet)
                .addOnSuccessListener { translatedText ->
                    detailsDiet.text = translatedText
                }
                .addOnFailureListener { _ ->
                    detailsDiet.text = diet
                }
            detailsSource.text = recipe.source
            detailsUrl.text = recipe.url
        }
        
        val saveTextView = binding.detailsSave
        
        saveTextView.setOnClickListener {
            viewModel.saveRecipe(recipe)
        }
    }
}

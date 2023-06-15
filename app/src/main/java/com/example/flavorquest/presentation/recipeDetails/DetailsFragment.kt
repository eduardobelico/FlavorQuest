package com.example.flavorquest.presentation.recipeDetails

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.flavorquest.R
import com.example.flavorquest.core.Extensions.*
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
                    if (name.isBlank()) {
                        detailsName.text = "--------"
                    } else {
                        detailsName.text = translatedText
                    }
                }
                .addOnFailureListener { _ ->
                    detailsName.text = name
                }
            
            val dishType =
                recipe.dishType.toString().removeBrackets().replaceFirstChar { it.uppercase() }
            englishPortugueseTranslator.translate(dishType)
                .addOnSuccessListener { translatedText ->
                    val modifiedTranslatedText = adjustTranslatedDishType(translatedText)
                    if (dishType.isBlank()) {
                        detailsDishType.text = "--------"
                    } else {
                        detailsDishType.text = modifiedTranslatedText
                    }
                    setLevelView(modifiedTranslatedText)
                }
                .addOnFailureListener { _ ->
                    detailsDishType.text = dishType
                }
            
            val cuisineType =
                recipe.cuisineType.toString().removeBrackets().replaceFirstChar { it.uppercase() }
            englishPortugueseTranslator.translate(cuisineType)
                .addOnSuccessListener { translatedText ->
                    val modifiedTranslatedText = adjustTranslatedCuisineType(translatedText)
                    if (cuisineType.isBlank()) {
                        detailsCuisineType.text = "--------"
                    } else {
                        detailsCuisineType.text = modifiedTranslatedText
                    }
                }
                .addOnFailureListener { _ ->
                    detailsCuisineType.text = cuisineType
                }
            
            val mealType =
                recipe.mealType.toString().removeBrackets().replaceFirstChar { it.uppercase() }
            englishPortugueseTranslator.translate(mealType)
                .addOnSuccessListener { translatedText ->
                    if (mealType.isBlank()) {
                        detailsMealType.text = "--------"
                    } else {
                        detailsMealType.text = translatedText
                    }
                }
                .addOnFailureListener { _ ->
                    detailsMealType.text = mealType
                }
            
            val ingredients = recipe.ingredients.joinToString().removeBrackets()
            
            englishPortugueseTranslator.translate(ingredients)
                .addOnSuccessListener { translatedText ->
                    if (ingredients.isBlank()) {
                        detailsIngredients.text = "--------"
                    } else {
                        val formatedText = translatedText.replace(",", ", \n \n")
                        binding.detailsIngredients.text = formatedText
                    }
                }
                .addOnFailureListener { _ ->
                    detailsIngredients.text = ingredients
                }
            
            val diet =
                recipe.diet.joinToString("\n").removeBrackets().replaceFirstChar { it.uppercase() }
            englishPortugueseTranslator.translate(diet)
                .addOnSuccessListener { translatedText ->
                    if (diet.isBlank()) {
                        detailsDiet.text = "--------"
                    } else {
                        detailsDiet.text = translatedText
                    }
                }
                .addOnFailureListener { _ ->
                    detailsDiet.text = diet
                }
            
            detailsSource.text = recipe.source
            
            detailsSource.setOnClickListener {
                val url = recipe.url
                val urlIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(url)
                )
                startActivity(urlIntent)
            }
        }
        setBottomView(recipe)
    }
    
    private fun setBottomView(recipe: Recipe) {
        binding.bottomInteractionSave.setOnClickListener {
            viewModel.saveRecipe(recipe)
            Toast.makeText(requireContext(), "Receita Salva", Toast.LENGTH_SHORT).show()
        }
        
        binding.detailsToHome.setOnClickListener {
            val navController = findNavController()
            val action = DetailsFragmentDirections.recipeDetailsFragmentToHomeFragment()
            if (navController.currentDestination?.id == R.id.recipeDetailsFragment) {
                navController.navigate(action)
            }
        }
        
        binding.detailsToFavorites.setOnClickListener {
            val navController = findNavController()
            val action =
                DetailsFragmentDirections.recipeDetailsFragmentToFavoriteRecipesFragment()
            if (navController.currentDestination?.id == R.id.recipeDetailsFragment) {
                navController.navigate(action)
            }
        }
    }
    
    private fun setLevelView(dishType: String) {
        val drawableResId =
            if (DishTypesLevel.easyDishTypes.any {
                    it == dishType
                }) {
                R.drawable.level_easy
            } else if (DishTypesLevel.midDishTypes.any {
                    it == dishType
                }) {
                R.drawable.level_mid
            } else if (DishTypesLevel.hardDishTypes.any {
                    it == dishType
                }) {
                R.drawable.level_hard
            } else {
                R.drawable.border_details_items
            }
        binding.detailsRecipeLevel.setImageResource(drawableResId)
    }
}


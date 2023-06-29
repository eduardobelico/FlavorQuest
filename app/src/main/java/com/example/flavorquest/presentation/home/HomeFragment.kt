package com.example.flavorquest.presentation.home

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.flavorquest.R
import com.example.flavorquest.databinding.FragmentHomeBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel by viewModel<HomeViewModel>()
    
    private var selectedQuery: String? = null
    private var selectedCuisineType: String? = null
    private var selectedDishType: String? = null
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        parametersBinding()
        setSearchButton()
        setFavoriteRecipesButton()
        setUserLevelButton()
        
        return binding.root
    }
    
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    
    override fun onResume() {
        super.onResume()
        dropdownItemsBinding()
    }
    
    override fun onPause() {
        super.onPause()
        viewModel.stateRefresh()
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getResult()
        userLevelBinding()
    }
    
    /**
     * Set do botão de busca. A busca precisa de pelo menos um dos 3 parâmetros para acontecer,
     * sendo o resultado enviado para o fragment de lista de receitas.
     */
    
    private fun setSearchButton() {
        val searchButton = binding.searchButton
        searchButton.setOnClickListener {
            selectedQuery = binding.searchQuery.text.toString().trim()
            
            viewModel.checkParameters(
                query = selectedQuery,
                cuisineType = selectedCuisineType,
                dishType = selectedDishType
            )
        }
    }
    
    /**
     * Binding dos itens selecionados nos exposed dropdown menus que contém
     * os tipos de receita e os tipos de culinária.
     */
    
    private fun parametersBinding() {
        
        val cuisineTypeTextView = binding.cuisineTypeAutocompleteTextview
        cuisineTypeTextView.setOnItemClickListener { parent, _, position, _ ->
            selectedCuisineType = parent.getItemAtPosition(position).toString()
        }
        
        val dishTypeTextView = binding.dishTypeAutocompleteTextview
        dishTypeTextView.setOnItemClickListener { parent, _, position, _ ->
            selectedDishType = parent.getItemAtPosition(position).toString()
        }
    }
    
    /**
     * Set da funcionalidade de alterar o nome ao que usuário é referido no textview
     * de introdução.
     */
    
    private fun userLevelBinding() {
        lifecycleScope.launch {
            viewModel.numFavoriteRecipes.collect { count ->
            
        val introductionTemplate = getString(R.string.introduction_text)
        
        val userLevel: String = when {
            count >= 1 && count < 10 -> getString(R.string.user_level_1)
            count >= 10 && count < 30 -> getString(R.string.user_level_2)
            count >= 30 && count < 50 -> getString(R.string.user_level_3)
            count >= 50 && count < 100 -> getString(R.string.user_level_4)
            count >= 100 -> getString(R.string.user_level_5)
            else -> getString(R.string.user_level_0)
        }
        
        val spannableBuilder = SpannableStringBuilder()
        spannableBuilder.append(String.format(introductionTemplate, userLevel))
        
        val spanStart = spannableBuilder.indexOf(userLevel)
        val spanEnd = spanStart + userLevel.length
        
        spannableBuilder.setSpan(
            StyleSpan(Typeface.BOLD),
            spanStart,
            spanEnd,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        val color = ContextCompat.getColor(requireContext(), R.color.darker_cadet_blue)
        spannableBuilder.setSpan(
            ForegroundColorSpan(color),
            spanStart,
            spanEnd,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        binding.introductionText.text = spannableBuilder
            }
        }
    }
    
    /**
     * Utiliza a função do ViewModel para navegar para o fragment de lista de receitas.
     */
    
    private fun getResult() {
        lifecycleScope.launch {
            viewModel.state.collectLatest { result ->
                when (result) {
                    is HomeState.Error -> {
                        Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT).show()
                    }
                    HomeState.Success -> {
                        toRecipeListFragment()
                    }
                    else -> {}
                }
            }
        }
    }
    
    private fun toRecipeListFragment() {
        val navController = findNavController()
        val action = HomeFragmentDirections.homeFragmentToRecipeListFragment(
            selectedQuery,
            selectedCuisineType,
            selectedDishType
        )
        if (navController.currentDestination?.id == R.id.homeFragment) {
            navController.navigate(action)
        }
    }
    
    private fun setFavoriteRecipesButton() {
        binding.favoriteRecipesIcon.setOnClickListener {
            val navController = findNavController()
            val action = HomeFragmentDirections.homeFragmentToFavoriteRecipesFragment()
            if (navController.currentDestination?.id == R.id.homeFragment) {
                navController.navigate(action)
            }
        }
    }
    
    private fun setUserLevelButton() {
        binding.userLevelIcon.setOnClickListener {
            val navController = findNavController()
            val action = HomeFragmentDirections.homeFragmentToUserLevelFragment()
            if (navController.currentDestination?.id == R.id.homeFragment) {
                navController.navigate(action)
            }
        }
    }
    
    private fun dropdownItemsBinding() {
        val dishTypes = resources.getStringArray(R.array.dish)
        val dishArrayAdapter =
            ArrayAdapter(requireContext(), R.layout.dropdown_item, dishTypes)
        binding.dishTypeAutocompleteTextview.setAdapter(dishArrayAdapter)
        
        val cuisineTypes = resources.getStringArray(R.array.cuisine)
        val cuisineArrayAdapter =
            ArrayAdapter(requireContext(), R.layout.dropdown_item, cuisineTypes)
        binding.cuisineTypeAutocompleteTextview.setAdapter(cuisineArrayAdapter)
    }
}




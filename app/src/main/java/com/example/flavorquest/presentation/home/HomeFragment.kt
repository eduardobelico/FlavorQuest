package com.example.flavorquest.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.flavorquest.R
import com.example.flavorquest.core.visibilityGone
import com.example.flavorquest.core.visibilityVisible
import com.example.flavorquest.databinding.FragmentHomeBinding
import com.example.flavorquest.presentation.RecipeListState
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
        
        setSearchButton()
        
        return binding.root
    }
    
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        dropdownItemsBinding()
    }
    
    private fun setSearchButton() {
        val searchButton = binding.searchButton
        searchButton.setOnClickListener {
            lifecycleScope.launch {
                parametersBinding()
                searchList()
                toRecipeListFragment()
                Log.i("oi", "setSearchButton: $selectedQuery, $selectedCuisineType, $selectedDishType")
            }
        }
    }
    
    private fun parametersBinding() {
        
        selectedQuery = binding.searchQuery.text.toString()
        
        val cuisineTypeTextView = binding.cuisineTypeAutocompleteTextview
        cuisineTypeTextView.setOnItemClickListener { parent, _, position, _ ->
            selectedCuisineType = parent.getItemAtPosition(position).toString()
        }
        
        val dishTypeTextView = binding.dishTypeAutocompleteTextview
        dishTypeTextView.setOnItemClickListener { parent, _, position, _ ->
            selectedDishType = parent.getItemAtPosition(position).toString()
        }
        
        viewModel.checkParameters(
            query = selectedQuery,
            cuisineType = selectedCuisineType,
            dishType = selectedDishType
        )
        Log.i("oi", "parametersBinding: $selectedQuery, $selectedCuisineType, $selectedDishType")
    }
    
    private fun searchList() {
        lifecycleScope.launch {
            viewModel.recipeList.collectLatest { result ->
                when (result) {
                    is RecipeListState.Loading -> {
                        with(binding.searchError) {
                            progressBar.visibilityVisible()
                            errorMessage.visibilityGone()
                            Log.i("oi", "searchList: $result")
                        }
                    }
                    is RecipeListState.Error -> {
                        with(binding.searchError) {
                            progressBar.visibilityGone()
                            errorMessage.visibilityVisible()
                            Log.i("oi", "searchList: $result")
                        }
                    }
                    is RecipeListState.Data -> {
                        with(binding.searchError) {
                            progressBar.visibilityGone()
                            errorMessage.visibilityGone()
                            Log.i("oi", "searchList: $result")
                        }
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
        navController.navigate(action)
        Log.i("oi", "toRecipeListFragment: $selectedQuery, $selectedCuisineType, $selectedDishType")
    }
    
    private fun dropdownItemsBinding() {
        val dishTypes = resources.getStringArray(R.array.dish)
        val dishArrayAdapter =
            ArrayAdapter(requireContext(), R.layout.dropdown_item, dishTypes)
        binding.dishTypeAutocompleteTextview.setAdapter(dishArrayAdapter)
        Log.i("oi", "dropdownItemsBinding: $dishTypes")
        
        val cuisineTypes = resources.getStringArray(R.array.cuisine)
        val cuisineArrayAdapter =
            ArrayAdapter(requireContext(), R.layout.dropdown_item, cuisineTypes)
        binding.cuisineTypeAutocompleteTextview.setAdapter(cuisineArrayAdapter)
        Log.i("oi", "dropdownItemsBinding: $cuisineTypes")
    }
}

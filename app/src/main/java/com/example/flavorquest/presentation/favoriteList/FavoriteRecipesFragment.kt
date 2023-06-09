package com.example.flavorquest.presentation.favoriteList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.flavorquest.R
import com.example.flavorquest.core.Constants
import com.example.flavorquest.core.visibilityGone
import com.example.flavorquest.core.visibilityVisible
import com.example.flavorquest.databinding.FragmentFavoriteRecipesBinding
import com.example.flavorquest.domain.model.Recipe
import com.example.flavorquest.presentation.adapters.ListAdapter
import com.example.flavorquest.presentation.state.ListState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteRecipesFragment : Fragment() {
    
    private var _binding: FragmentFavoriteRecipesBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var listAdapter: ListAdapter
    private val viewModel by viewModel<FavoriteRecipesViewModel>()
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteRecipesBinding.inflate(inflater, container, false)
        getFavoriteRecipes()
        setToolbar()
        initRecyclerView()
        return binding.root
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        populateRecyclerView()
    }
    
    private fun getFavoriteRecipes() {
        viewModel.getFavoriteRecipes()
    }
    
    private fun removeRecipeFromFavorites(recipe: Recipe) {
        viewModel.removeFromFavorites(recipe)
    }
    
    private fun setToolbar() {
        val activity = activity as AppCompatActivity
        activity.setSupportActionBar(binding.favoriteToolbar)
        activity.title = Constants.TOOLBAR_FAVORITES_TITLE
    }
    
    private fun initRecyclerView() {
        listAdapter = ListAdapter()
        binding.favoriteRecyclerview.adapter = listAdapter
        binding.favoriteRecyclerview.setHasFixedSize(true)
        
        listAdapter.selectedRecipe = {
            toDetailsFragment(recipeId = it.id)
        }
    }
    
    private fun toDetailsFragment(recipeId: String) {
        val navController = findNavController()
        val action =
            FavoriteRecipesFragmentDirections.actionFavoriteRecipesFragmentToRecipeDetailsFragment(
                recipeId
            )
        
        if (navController.currentDestination?.id == R.id.favoriteRecipesFragment) {
            return navController.navigate(action)
        }
    }
    
    private fun populateRecyclerView() {
        lifecycleScope.launch {
            viewModel.favoriteRecipes.collectLatest { result ->
                when (result) {
                    is ListState.Data -> {
                        with(binding.favoriteSearchError) {
                            errorMessage.visibilityGone()
                            progressBar.visibilityGone()
                        }
                        listAdapter.setData(result.recipeList)
                    }
                    is ListState.Error -> {
                        Toast.makeText(requireContext(), "Erro", Toast.LENGTH_SHORT).show()
                        with(binding.favoriteSearchError) {
                            errorMessage.visibilityVisible()
                            progressBar.visibilityGone()
                            errorMessage.text = result.message
                        }
                    }
                    ListState.Loading -> {
                        with(binding.favoriteSearchError) {
                            errorMessage.visibilityGone()
                            progressBar.visibilityVisible()
                        }
                    }
                }
            }
        }
    }
}
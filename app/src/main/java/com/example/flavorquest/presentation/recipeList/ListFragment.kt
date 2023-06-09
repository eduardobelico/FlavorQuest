package com.example.flavorquest.presentation.recipeList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.flavorquest.R
import com.example.flavorquest.core.Constants.TOOLBAR_LIST_TITLE
import com.example.flavorquest.core.visibilityGone
import com.example.flavorquest.core.visibilityVisible
import com.example.flavorquest.databinding.FragmentRecipeListBinding
import com.example.flavorquest.presentation.adapters.ListAdapter
import com.example.flavorquest.presentation.state.ListState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFragment : Fragment() {
    
    private var _binding: FragmentRecipeListBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var listAdapter: ListAdapter
    private val args by navArgs<ListFragmentArgs>()
    private val viewModel by viewModel<ListViewModel>()
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipeListBinding.inflate(inflater, container, false)
        getSearchArgs()
        setToolbar()
        initRecyclerView()
        
        return binding.root
    }
    
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        populateRecyclerView()
    }
    
    private fun getSearchArgs() {
        viewModel.getRecipes(query = args.query, cuisineType = args.cuisineType, dishType = args.dishType)
    }
    
    private fun setToolbar() {
        val activity = activity as AppCompatActivity
        activity.setSupportActionBar(binding.listToolbar)
        activity.title = TOOLBAR_LIST_TITLE
    }
    
    private fun initRecyclerView() {
        listAdapter = ListAdapter()
        binding.recipeListRecyclerview.adapter = listAdapter
        binding.recipeListRecyclerview.setHasFixedSize(true)
        
        listAdapter.selectedRecipe = {
            toDetailsFragment(recipeId = it.id)
        }
    }
    
    private fun toDetailsFragment(recipeId: String) {
        val navController = findNavController()
        val action = ListFragmentDirections.recipeListFragmentToRecipeDetailsFragment(recipeId)
        
        if (navController.currentDestination?.id == R.id.recipeListFragment) {
            return navController.navigate(action)
        }
    }
    
    private fun populateRecyclerView() {
        lifecycleScope.launch {
            viewModel.recipeList.collectLatest { result ->
                when (result) {
                    is ListState.Data -> {
                        with(binding.searchError) {
                            errorMessage.visibilityGone()
                            progressBar.visibilityGone()
                        }
                        listAdapter.setData(result.recipeList)
                    }
                    is ListState.Error -> {
                        Toast.makeText(requireContext(), "Erro", Toast.LENGTH_SHORT).show()
                        with(binding.searchError) {
                            errorMessage.visibilityVisible()
                            progressBar.visibilityGone()
                            errorMessage.text = result.message
                        }
                    }
                    ListState.Loading -> {
                        with(binding.searchError) {
                            errorMessage.visibilityGone()
                            progressBar.visibilityVisible()
                            
                        }
                    }
                }
            }
        }
    }
}
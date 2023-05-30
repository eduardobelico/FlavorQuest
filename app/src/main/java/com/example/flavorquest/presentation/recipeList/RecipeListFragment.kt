package com.example.flavorquest.presentation.recipeList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.flavorquest.core.Constants.TOOLBAR_TITLE
import com.example.flavorquest.core.visibilityGone
import com.example.flavorquest.core.visibilityVisible
import com.example.flavorquest.databinding.FragmentRecipeListBinding
import com.example.flavorquest.presentation.RecipeListState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecipeListFragment : Fragment() {
    
    private var _binding: FragmentRecipeListBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var recipeAdapter: RecipeAdapter
    private val args by navArgs<RecipeListFragmentArgs>()
    private val viewModel by viewModel<ListViewModel>()
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipeListBinding.inflate(inflater, container, false)
        getSearchArgs()
        setToolbar()
        initRecyclerView()
        Log.i("oi", "onCreateView")
        
        return binding.root
    }
    
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getSearchArgs()
        populateRecyclerView()
        Log.i("oi", "onViewCreated")
    }
    
    private fun getSearchArgs() {
        viewModel.getRecipes(args.query, args.cuisineType, args.dishType)
        Log.i("oi", "getSearchArgs: ${args.query}, ${args.cuisineType}, ${args.dishType}")
    }
    
    private fun setToolbar() {
        val activity = activity as AppCompatActivity
        activity.setSupportActionBar(binding.listToolbar)
        activity.title = TOOLBAR_TITLE
    }
    
    private fun initRecyclerView() {
        recipeAdapter = RecipeAdapter()
        binding.recipeListRecyclerview.adapter = recipeAdapter
        binding.recipeListRecyclerview.setHasFixedSize(true)
        Log.i("oi", "initRecyclerView")
    }
    
    private fun populateRecyclerView() {
        
        lifecycleScope.launch {
            Log.i("oi", "populateRecyclerView")
            viewModel.recipeList.collectLatest { result ->
                Log.i("oi", "populateRecyclerView - RecipeListState: $result")
                when (result) {
                    is RecipeListState.Data -> {
                        with(binding.searchError) {
                            progressBar.visibilityGone()
                            errorMessage.visibilityGone()
                        }
                        Log.i("oi", "RecipeListState.Data - Recipe List: ${result.recipeList}")
                        recipeAdapter.setData(result.recipeList)
                        Log.i("oi", "RecipeListState.Data")
                    }
                    is RecipeListState.Error -> {
                        with(binding.searchError) {
                            errorMessage.visibilityVisible()
                            progressBar.visibilityGone()
                            errorMessage.text = result.message
                            
                        }
                    }
                    RecipeListState.Loading -> {
                        with(binding.searchError) {
                            errorMessage.visibilityGone()
                            progressBar.visibilityVisible()
                        }
                    }
                    else -> {}
                }
            }
        }
    }
}
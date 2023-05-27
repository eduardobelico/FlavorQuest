package com.example.flavorquest.presentation.recipeList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.flavorquest.databinding.FragmentRecipeListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecipeListFragment : Fragment() {
    
    private var _binding: FragmentRecipeListBinding? = null
    private val binding get() = _binding!!
    
    private val args by navArgs<RecipeListFragmentArgs>()
    private val viewModel by viewModel<ListViewModel>()
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipeListBinding.inflate(inflater, container, false)
        
        return binding.root
    }
    
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    
    private fun getSearchArgs() {
    }
    
}
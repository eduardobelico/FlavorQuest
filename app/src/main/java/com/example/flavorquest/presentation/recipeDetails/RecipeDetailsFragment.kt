package com.example.flavorquest.presentation.recipeDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.flavorquest.R
import com.example.flavorquest.databinding.FragmentRecipeDetailsBinding

class RecipeDetailsFragment : Fragment() {
    
    private var _binding: FragmentRecipeDetailsBinding? = null
    private val binding get() = _binding!!
    
    private val args by navArgs<RecipeDetailsFragmentArgs>()
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipeDetailsBinding.inflate(inflater, container, false)
        
        return binding.root
    }
    
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       
    }
    
    private fun getSearchArgs() {}
    
}
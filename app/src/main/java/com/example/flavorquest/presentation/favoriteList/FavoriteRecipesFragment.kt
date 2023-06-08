package com.example.flavorquest.presentation.favoriteList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.flavorquest.databinding.FragmentFavoriteRecipesBinding
import com.example.flavorquest.presentation.recipeList.ListAdapter

class FavoriteRecipesFragment : Fragment() {
    
    private var _binding: FragmentFavoriteRecipesBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var listAdapter: ListAdapter
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteRecipesBinding.inflate(inflater, container, false)
    
        return binding.root
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.example.flavorquest.presentation.recipeDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.flavorquest.core.Constants.TOOLBAR_DETAILS_TITLE
import com.example.flavorquest.databinding.FragmentRecipeDetailsBinding
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
        setToolbar()
        
        return binding.root
    }
    
    private fun setToolbar() {
        val activity = activity as AppCompatActivity
        activity.setSupportActionBar(binding.detailsToolbar)
        activity.title = TOOLBAR_DETAILS_TITLE
    }
    
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
    }
    
    private fun getDetailsArgs() {
        viewModel.getRecipeDetails(id = args.recipeId)
    }
    
}
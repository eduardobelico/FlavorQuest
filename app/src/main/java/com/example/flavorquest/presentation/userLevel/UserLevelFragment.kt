package com.example.flavorquest.presentation.userLevel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.flavorquest.R
import com.example.flavorquest.core.Constants
import com.example.flavorquest.databinding.FragmentUserLevelBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserLevelFragment : Fragment() {
    
    private var _binding: FragmentUserLevelBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel by viewModel<UserLevelViewModel>()
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserLevelBinding.inflate(inflater, container, false)
        setToolbar()
        
        return binding.root
    }
    
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    
    private fun setToolbar() {
        val activity = activity as AppCompatActivity
        activity.setSupportActionBar(binding.userLevelToolbar)
        activity.title = Constants.TOOLBAR_USER_LEVEL_TITLE
        
        binding.userLevelToolbar.setNavigationOnClickListener {
            toHomeFragment()
        }
    }
    
    private fun toHomeFragment() {
        val navController = findNavController()
        val action = UserLevelFragmentDirections.userLevelFragmentToHomeFragment()
        if (navController.currentDestination?.id == R.id.userLevelFragment) {
            navController.navigate(action)
        }
    }
}
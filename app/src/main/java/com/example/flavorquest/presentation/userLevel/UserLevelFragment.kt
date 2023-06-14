package com.example.flavorquest.presentation.userLevel

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.flavorquest.R
import com.example.flavorquest.core.Constants
import com.example.flavorquest.databinding.FragmentUserLevelBinding
import kotlinx.coroutines.launch
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
        userLevelBinding()
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
    
    private fun userLevelBinding() {
        lifecycleScope.launch {
            viewModel.numFavoriteRecipes.collect { count ->
                
                val userLevel: String = when {
                    count >= 1 && count < 10 -> getString(R.string.user_level_1)
                    count >= 10 && count < 30 -> getString(R.string.user_level_2)
                    count >= 30 && count < 50 -> getString(R.string.user_level_3)
                    count >= 50 && count < 100 -> getString(R.string.user_level_4)
                    count >= 100 -> getString(R.string.user_level_5)
                    else -> getString(R.string.user_level_0)
                }
                
                binding.userLevel.text = userLevel
                binding.recipeAmount.text = count.toString()
                
                val userLevelExplainer = getString(R.string.user_level_explainer)
                val spannableBuilder = SpannableStringBuilder()
                spannableBuilder.append(String.format(userLevelExplainer, "FlavorQuest"))
                
                val spanStart = spannableBuilder.indexOf("FlavorQuest")
                val spanEnd = spanStart + "FlavorQuest".length
    
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
                binding.userLevelExplainer.text = spannableBuilder
            }
        }
    }
}
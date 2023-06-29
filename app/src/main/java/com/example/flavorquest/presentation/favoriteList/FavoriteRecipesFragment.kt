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
import com.example.flavorquest.core.Extensions.visibilityGone
import com.example.flavorquest.core.Extensions.visibilityVisible
import com.example.flavorquest.databinding.FragmentFavoriteRecipesBinding
import com.example.flavorquest.presentation.adapters.ListAdapter
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
        viewModel.getFavoriteRecipes(FavoriteEvent.OnLoadRecipeList)
    }
    
    private fun setToolbar() {
        val activity = activity as AppCompatActivity
        activity.setSupportActionBar(binding.favoriteToolbar)
        activity.title = Constants.TOOLBAR_FAVORITES_TITLE
        
        binding.favoriteToolbar.setNavigationOnClickListener {
            toHomeFragment()
        }
    }
    
    /**
     * Funções de apoio de navegação para os fragments respectivos.
     **/
    
    private fun toDetailsFragment(recipeId: String) {
        val navController = findNavController()
        val action =
            FavoriteRecipesFragmentDirections.favoriteRecipesFragmentToRecipeDetailsFragment(
                recipeId
            )
        
        if (navController.currentDestination?.id == R.id.favoriteRecipesFragment) {
            return navController.navigate(action)
        }
    }
    
    private fun toHomeFragment() {
        val navController = findNavController()
        val action = FavoriteRecipesFragmentDirections.favoriteRecipesFragmentToHomeFragment()
        
        if (navController.currentDestination?.id == R.id.favoriteRecipesFragment) {
            return navController.navigate(action)
        }
    }
    
    /**
     * Inicia o recyclerview e ao clicar em algum item enviado para RecipeDetailsFragment
     * com o ID da receita clicada, além de possibilitar a funcionalidade de adiocionar
     * ou remover cada receita aos favoritos.
     **/
    
    private fun initRecyclerView() {
        listAdapter = ListAdapter()
        binding.favoriteRecyclerview.adapter = listAdapter
        binding.favoriteRecyclerview.setHasFixedSize(true)
        
        listAdapter.selectedRecipe = {
            toDetailsFragment(recipeId = it.recipe.id)
        }
        listAdapter.addOrRemove = { recipe ->
            viewModel.getFavoriteRecipes(FavoriteEvent.OnFavoriteClick(recipe))
        }
    }
    
    /**
     * Coleta os dados da ViewModel
     * Em caso de sucesso: é colocado em display a lista de receitas.
     * Em caso de Loading: é colocado em display uma progressbar e escodido o resto
     * do conteúdo.
     * Em caso de Erro: é mostrado mensagem de erro.
     **/
    
    private fun populateRecyclerView() {
        lifecycleScope.launch {
            viewModel.favoriteRecipes.collectLatest { result ->
                when (result) {
                    is FavoriteState.Data -> {
                        with(binding.favoriteSearchError) {
                            errorMessage.visibilityGone()
                            progressBar.visibilityGone()
                        }
                        listAdapter.setData(result.favoritesList)
                    }
                    is FavoriteState.Error -> {
                        Toast.makeText(requireContext(), "Erro", Toast.LENGTH_SHORT).show()
                        with(binding.favoriteSearchError) {
                            errorMessage.visibilityVisible()
                            progressBar.visibilityGone()
                            errorMessage.text = result.message
                        }
                    }
                    FavoriteState.Loading -> {
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
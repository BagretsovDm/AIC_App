package com.example.aic_app.ui.fragments.favorites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.aic_app.R
import com.example.aic_app.databinding.FragmentFavoritesBinding
import com.example.aic_app.ui.ARG
import com.example.aic_app.ui.adapters.ArtworksAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    private val viewModel: FavoritesViewModel by viewModels()
    private var binding: FragmentFavoritesBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentFavoritesBinding.bind(view)

        val adapter = ArtworksAdapter(::goToDetail)

        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.favorites.collect {
                adapter.submitList(it)
            }
        }

        binding!!.rv.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun goToDetail(id: Long) {
        val args = Bundle().apply {
            putLong(ARG, id)
        }

        findNavController().navigate(R.id.action_favorites_to_detailsFragment, args)
    }
}

package com.example.aic_app.ui.fragments.artworks

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.aic_app.R
import com.example.aic_app.databinding.FragmentArtworksBinding
import com.example.aic_app.ui.ARG
import com.example.aic_app.ui.adapters.ArtworksAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ArtworksFragment : Fragment(R.layout.fragment_artworks) {

    private val viewModel: ArtworksViewModel by viewModels()
    private var binding: FragmentArtworksBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentArtworksBinding.bind(view)

        val adapter = ArtworksAdapter(::goToDetail)

        viewModel.isVisible.observe(viewLifecycleOwner) {
            binding!!.loading.isVisible = it
        }

        lifecycleScope.launch(Dispatchers.IO) {

            viewModel.artworks.collect {
                adapter.submitList(it)
                viewModel.setVisible(it.isEmpty())
            }
        }

        binding?.rv?.adapter = adapter
    }

    private fun goToDetail(id: Long) {
        val args = Bundle().apply{
            putLong(ARG, id)
        }

        findNavController().navigate(R.id.action_artworks_to_detailsFragment, args)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}

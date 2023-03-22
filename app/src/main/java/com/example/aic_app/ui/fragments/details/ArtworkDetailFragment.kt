package com.example.aic_app.ui.fragments.details

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.aic_app.R
import com.example.aic_app.databinding.FragmentDetailsBinding
import com.example.aic_app.ui.ARG
import com.example.aic_app.ui.PREFIX
import com.example.aic_app.ui.SUFFIX
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArtworkDetailFragment : Fragment(R.layout.fragment_details) {

    private val viewModel: ArtworkDetailViewModel by viewModels()
    private var binding: FragmentDetailsBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchArtwork(
            arguments?.getLong(ARG) ?: -1
        )

        binding = FragmentDetailsBinding.bind(view)

        setupObservers()

        binding!!.favoriteBtn.setOnClickListener {

            Toast.makeText(
                requireContext(),
                if (!it.isSelected) {
                    viewModel.addToFavorites()
                    "Added to favorites!"
                } else {
                    viewModel.removeFromFavorites()
                    "Remove from favorites"
                },
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun setupObservers() {
        viewModel.artwork.observe(viewLifecycleOwner) { art ->
            with(binding!!) {
                Picasso.get().load(PREFIX + art.imageId + SUFFIX).into(artView)
                title.text = art.title
                date.text = art.date
                author.text = art.artist
            }
        }

        viewModel.isFavorite.observe(viewLifecycleOwner) {
            binding!!.favoriteBtn.isSelected = it
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}

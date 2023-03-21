package com.example.aic_app.ui.fragments.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.aic_app.R
import com.example.aic_app.databinding.FragmentSearchBinding
import com.example.aic_app.ui.ARG
import com.example.aic_app.ui.adapters.ArtworksAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

    private var binding: FragmentSearchBinding? = null
    private val viewModel: SearchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSearchBinding.bind(view)

        val adapter = ArtworksAdapter(::goToDetail)

        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.searchResult.collect {
                adapter.submitList(it)
            }
        }

        binding!!.rv.adapter = adapter

        binding!!.textInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                s?.let { text ->
                    viewModel.fetchSearchResult(text.toString())
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun goToDetail(id: Long) {
        val args = Bundle().apply{
            putLong(ARG, id)
        }

        findNavController().navigate(R.id.action_search_to_detailsFragment, args)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}

package com.example.aic_app.ui.fragments.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aic_app.repositories.NetworkRepository
import com.example.aic_app.ui.model.Art
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: NetworkRepository,
) : ViewModel() {

    private val _searchResult = MutableStateFlow(listOf<Art>())
    val searchResult: StateFlow<List<Art>> = _searchResult

    fun fetchSearchResult(query: String) {
        val list = mutableListOf<Art>()

        viewModelScope.launch(Dispatchers.IO) {
            repository.fetchSearchData(query).forEach { result ->
                val artwork = repository.fetchById(result.id)

                artwork?.imageId?.let {
                    list.add(
                        artwork.toArt()
                    )
                }
            }
            _searchResult.value = list
        }
    }
}

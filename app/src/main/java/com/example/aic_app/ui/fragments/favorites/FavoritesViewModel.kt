package com.example.aic_app.ui.fragments.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aic_app.repositories.LocalRepository
import com.example.aic_app.ui.model.Art
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val localRepository: LocalRepository
) : ViewModel() {

    private val _favorites = MutableStateFlow(listOf<Art>())
    val favorites: StateFlow<List<Art>> = _favorites

    init {
        viewModelScope.launch(Dispatchers.IO) {
            fetchFromFavorites()
        }
    }

    private suspend fun fetchFromFavorites() {
        localRepository.getAllFavorites().collect {
            _favorites.value = it.map { entity -> entity.toArt() }
        }
    }
}

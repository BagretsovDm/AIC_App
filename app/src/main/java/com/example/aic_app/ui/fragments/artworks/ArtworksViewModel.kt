package com.example.aic_app.ui.fragments.artworks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aic_app.data.room.entities.ArtworksEntity
import com.example.aic_app.repositories.LocalRepository
import com.example.aic_app.repositories.NetworkRepository
import com.example.aic_app.ui.model.Art
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtworksViewModel @Inject constructor(
    private val networkRepository: NetworkRepository,
    private val localRepository: LocalRepository
) : ViewModel() {

    private val range = (1..1000)

    private val _isVisible = MutableLiveData(true)
    val isVisible: LiveData<Boolean> = _isVisible

    private val _artworks = MutableStateFlow(listOf<Art>())
    val artworks: StateFlow<List<Art>> = _artworks

    init {
        viewModelScope.launch(Dispatchers.IO) {
            localRepository.clear()
            fetchFromNetwork(range.shuffled().first(), 60)
            fetchFromLocal()
        }
    }

    fun setVisible(flag: Boolean) {
        _isVisible.postValue(flag)
    }

    private suspend fun fetchFromLocal() {
        localRepository.getAllArtworks().collect {
            _artworks.value = it.map { entity -> entity.toArt() }
        }
    }

    private suspend fun fetchFromNetwork(page: Int, limit: Int) {
        networkRepository.fetchData(page, limit).forEach { artwork ->
            if (!artwork.imageId.isNullOrEmpty()) {
                localRepository.addArtwork(
                    ArtworksEntity.toArtworksEntity(artwork)
                )
            }
        }
    }
}

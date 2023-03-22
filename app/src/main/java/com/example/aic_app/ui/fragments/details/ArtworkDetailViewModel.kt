package com.example.aic_app.ui.fragments.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aic_app.data.room.entities.FavoritesEntity
import com.example.aic_app.repositories.LocalRepository
import com.example.aic_app.repositories.NetworkRepository
import com.example.aic_app.ui.model.Art
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtworkDetailViewModel @Inject constructor(
    private val localRepository: LocalRepository,
    private val networkRepository: NetworkRepository
) : ViewModel() {

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> = _isFavorite

    private val _artwork = MutableLiveData<Art>()
    val artwork: LiveData<Art> = _artwork

    fun updateIsFavorite() {
        _isFavorite.postValue(!_isFavorite.value!!)
    }

    fun fetchArtwork(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            val art = try {
                localRepository.getFavoriteById(id).toArt()
            } catch (_: Exception) {
                networkRepository.fetchById(id)?.toArt()
            }

            art?.let {
                _artwork.postValue(it)
                _isFavorite.postValue(it.isFavorite)
            }
        }
    }

    fun addToFavorites() {
        viewModelScope.launch(Dispatchers.IO) {
            _artwork.value?.let {
                FavoritesEntity.toFavoriteEntity(
                    it.copy(isFavorite = true)
                )
            }?.let {
                updateIsFavorite()
                localRepository.addFavorite(
                    it
                )
            }
        }
    }

    fun removeFromFavorites() {
        viewModelScope.launch(Dispatchers.IO) {
            _artwork.value?.let {
                updateIsFavorite()
                localRepository.deleteFavorite(it.id)
            }
        }
    }
}

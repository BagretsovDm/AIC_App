package com.example.aic_app.repositories

import com.example.aic_app.data.room.daos.ArtworksDao
import com.example.aic_app.data.room.daos.FavoritesDao
import com.example.aic_app.data.room.entities.ArtworksEntity
import com.example.aic_app.data.room.entities.FavoritesEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalRepository @Inject constructor(
    private val artworks: ArtworksDao, private val favorites: FavoritesDao,
) {

    suspend fun clear() {
        artworks.clearDb()
    }

    suspend fun addArtwork(artworksEntity: ArtworksEntity) {
        artworks.addArt(artworksEntity)
    }

    fun getAllArtworks(): Flow<List<ArtworksEntity>> {
        return artworks.getAll()
    }

    suspend fun getArtworkById(id: Long): ArtworksEntity {
        return artworks.getById(id)
    }

    fun getAllFavorites(): Flow<List<FavoritesEntity>> {
        return favorites.getAll()
    }

    suspend fun getFavoriteById(id: Long): FavoritesEntity {
        return favorites.getById(id)
    }

    suspend fun deleteFavorite(id: Long) {
        favorites.delete(id)
    }
}

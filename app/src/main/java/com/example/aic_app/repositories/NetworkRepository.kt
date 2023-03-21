package com.example.aic_app.repositories

import com.example.aic_app.data.api.ArtworksApi
import com.example.aic_app.data.api.models.ArtResult
import com.example.aic_app.data.api.models.Artwork
import javax.inject.Inject

class NetworkRepository @Inject constructor(private val api: ArtworksApi) {

    suspend fun fetchData(page: Int, limit: Int): List<Artwork> {
        return api.getData(page, limit).body()?.data ?: emptyList()
    }

    suspend fun fetchSearchData(query: String): List<ArtResult> {
        return api.search(query).body()?.data ?: emptyList()
    }

    suspend fun fetchById(id: Long): Artwork? {
        return api.getById(id).body()?.data
    }
}

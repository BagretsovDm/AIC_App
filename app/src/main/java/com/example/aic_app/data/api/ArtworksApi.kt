package com.example.aic_app.data.api

import com.example.aic_app.data.api.models.ArtModel
import com.example.aic_app.data.api.models.Artworks
import com.example.aic_app.data.api.models.SearchResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "api/v1/artworks"
private const val SEARCH_URL = "api/v1/artworks/search"

interface ArtworksApi {
    @GET(BASE_URL)
    suspend fun getData(@Query("page") page: Int, @Query("limit") limit: Int): Response<Artworks>

    @GET(SEARCH_URL)
    suspend fun search(@Query("q") q: String): Response<SearchResult>

    @GET("$BASE_URL/{id}")
    suspend fun getById(@Path("id") id: Long): Response<ArtModel>
}

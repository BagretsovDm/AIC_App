package com.example.aic_app.data.api.models

data class Artworks(
    val pagination: Pagination,
    val data: List<Artwork>,
    val info: Info,
)

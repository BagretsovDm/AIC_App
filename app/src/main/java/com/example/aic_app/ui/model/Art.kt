package com.example.aic_app.ui.model

import com.example.aic_app.data.api.models.Artwork

data class Art(
    val id: Long,
    val title: String,
    val date: String,
    val artist: String,
    val imageId: String,
    val isFavorite: Boolean
)

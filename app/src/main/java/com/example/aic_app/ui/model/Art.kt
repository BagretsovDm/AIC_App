package com.example.aic_app.ui.model

data class Art(
    val id: Long,
    val title: String,
    val date: String,
    val artist: String,
    val imageId: String,
    val isFavorite: Boolean
)

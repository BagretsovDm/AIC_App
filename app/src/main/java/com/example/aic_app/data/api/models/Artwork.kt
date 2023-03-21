package com.example.aic_app.data.api.models

import com.example.aic_app.ui.model.Art
import com.squareup.moshi.Json

data class Artwork(
    @field:Json(name = "id")
    val id: Long,
    @field:Json(name = "title")
    val title: String?,
    @field:Json(name = "date_display")
    val dateDisplay: String?,
    @field:Json(name = "artist_display")
    val artistDisplay: String?,
    @field:Json(name = "place_of_origin")
    val placeOfOrigin: String?,
    @field:Json(name = "medium_display")
    val mediumDisplay: String?,
    @field:Json(name = "artist_title")
    val artistTitle: String?,
    @field:Json(name = "image_id")
    val imageId: String?
) {
    fun toArt(): Art {
        return Art(
            id = id,
            title = title.orEmpty(),
            date = dateDisplay.orEmpty(),
            artist = artistDisplay.orEmpty(),
            imageId = imageId.orEmpty(),
            isFavorite = false
        )
    }
}

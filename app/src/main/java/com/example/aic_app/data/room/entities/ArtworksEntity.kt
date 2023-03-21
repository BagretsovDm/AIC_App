package com.example.aic_app.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.aic_app.data.api.models.Artwork
import com.example.aic_app.ui.model.Art

@Entity(tableName = "artworks")
data class ArtworksEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val title: String,
    val date: String,
    val artist: String,
    val imageId: String,
    val isFavorite: Boolean
) {
    fun toArt(): Art {
        return Art(
            id = id,
            title = title,
            date = date,
            artist = artist,
            imageId = imageId,
            isFavorite = isFavorite
        )
    }

    companion object {

        fun toArtworksEntity(artwork: Artwork): ArtworksEntity {
            val title = artwork.title.orEmpty()
            val date = artwork.dateDisplay.orEmpty()
            val artist = artwork.artistTitle.orEmpty()

            return ArtworksEntity(
                id = artwork.id,
                title = title,
                date = date,
                artist = artist,
                imageId = artwork.imageId!!,
                isFavorite = false
            )
        }
    }
}

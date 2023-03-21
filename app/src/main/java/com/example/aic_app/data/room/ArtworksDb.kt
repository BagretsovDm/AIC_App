package com.example.aic_app.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.aic_app.data.room.daos.ArtworksDao
import com.example.aic_app.data.room.daos.FavoritesDao
import com.example.aic_app.data.room.entities.ArtworksEntity
import com.example.aic_app.data.room.entities.FavoritesEntity

const val ARTWORKS_TABLE_NAME = "artworks"
const val FAVORITES_TABLE_NAME = "favorites"

@Database(version = 1, entities = [ArtworksEntity::class, FavoritesEntity::class])
abstract class ArtworksDb : RoomDatabase() {
    abstract fun artworksDao(): ArtworksDao
    abstract fun favoritesDao(): FavoritesDao
}

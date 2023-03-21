package com.example.aic_app.data.room.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.aic_app.data.room.ARTWORKS_TABLE_NAME
import com.example.aic_app.data.room.entities.ArtworksEntity
import kotlinx.coroutines.flow.Flow

private const val TABLE = ARTWORKS_TABLE_NAME

@Dao
interface ArtworksDao {

    @Query("SELECT * FROM $TABLE")
    fun getAll(): Flow<List<ArtworksEntity>>

    @Query("SELECT * FROM $TABLE WHERE id = :id")
    suspend fun getById(id: Long): ArtworksEntity

    @Insert
    suspend fun addArt(artworksEntity: ArtworksEntity)

    @Query("DELETE FROM $TABLE")
    suspend fun clearDb()
}

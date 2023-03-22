package com.example.aic_app.data.room.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.aic_app.data.room.FAVORITES_TABLE_NAME
import com.example.aic_app.data.room.entities.FavoritesEntity
import kotlinx.coroutines.flow.Flow

private const val TABLE = FAVORITES_TABLE_NAME

@Dao
interface FavoritesDao {

    @Insert
    suspend fun addArt(favoritesEntity: FavoritesEntity)
    @Query("SELECT * FROM $TABLE")
    fun getAll(): Flow<List<FavoritesEntity>>

    @Query("SELECT * FROM $TABLE WHERE id = :id")
    fun getById(id: Long): FavoritesEntity

    @Query("DELETE FROM $TABLE WHERE id = :id")
    fun delete(id: Long)

    @Query("DELETE FROM $TABLE")
    fun clearDb()
}

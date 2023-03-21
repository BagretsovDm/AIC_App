package com.example.aic_app.di

import android.content.Context
import androidx.room.Room
import com.example.aic_app.data.room.ArtworksDb
import com.example.aic_app.data.room.daos.ArtworksDao
import com.example.aic_app.data.room.daos.FavoritesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ArtworksDb {
        return Room.databaseBuilder(
            context = context,
            klass = ArtworksDb::class.java,
            name = "artworks_db"
        ).build()
    }

    @Provides
    fun provideArtworksDao(artworksDb: ArtworksDb): ArtworksDao {
        return artworksDb.artworksDao()
    }

    @Provides
    fun provideFavoritesDao(artworksDb: ArtworksDb): FavoritesDao {
        return artworksDb.favoritesDao()
    }
}

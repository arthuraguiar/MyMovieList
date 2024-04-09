package com.example.data_local.di

import android.app.Application
import androidx.room.Room
import com.example.data_local.dao.MovieDao
import com.example.data_local.db.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val MOVIE_DB_NAME = "Movie.db"

@Module
@InstallIn(SingletonComponent::class)
internal object LocalDataModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        application: Application,
    ): MovieDatabase {
        return Room
            .databaseBuilder(application, MovieDatabase::class.java, MOVIE_DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providePokemonDao(appDatabase: MovieDatabase): MovieDao {
        return appDatabase.movieDao()
    }
}
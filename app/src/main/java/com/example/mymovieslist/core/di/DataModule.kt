package com.example.mymovieslist.core.di

import com.example.mymovieslist.data.datasource.MoviesDataSource
import com.example.mymovieslist.data.datasource.MoviesDataSourceImpl
import com.example.mymovieslist.data.repository.MovieRepositoryImpl
import com.example.mymovieslist.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
@InstallIn(SingletonComponent::class)
@Module
abstract class DataModule {

    @Singleton
    @Binds
    abstract fun bindMoviesDataSource(
        moviesDataSourceImpl: MoviesDataSourceImpl
    ): MoviesDataSource

    @Singleton
    @Binds
    abstract fun bindMovieRepository(
        movieRepositoryImpl: MovieRepositoryImpl
    ): MovieRepository
}
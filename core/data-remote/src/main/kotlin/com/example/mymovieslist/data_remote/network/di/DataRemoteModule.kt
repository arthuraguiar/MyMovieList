package com.example.mymovieslist.data_remote.network.di

import com.example.mymovieslist.data_remote.network.datasource.MoviesDataSource
import com.example.mymovieslist.data_remote.network.datasource.MoviesDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DataRemoteModule {

    @Binds
    abstract fun bindMoviesDataSource(
        moviesDataSourceImpl: MoviesDataSourceImpl
    ): MoviesDataSource
}
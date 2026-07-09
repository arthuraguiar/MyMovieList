package br.com.mymovieslist.dataremote.network.di

import br.com.mymovieslist.dataremote.network.datasource.MoviesDataSource
import br.com.mymovieslist.dataremote.network.datasource.MoviesDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal abstract class DataRemoteModule {

    @Binds
    abstract fun bindMoviesDataSource(
        moviesDataSourceImpl: MoviesDataSourceImpl
    ): MoviesDataSource
}

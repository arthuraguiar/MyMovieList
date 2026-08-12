package br.com.mymovieslist.dataremote.network.di

import br.com.mymovieslist.dataremote.network.datasource.MoviesDataSource
import br.com.mymovieslist.dataremote.network.datasource.MoviesDataSourceImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataRemoteModule = module {
    singleOf(::MoviesDataSourceImpl) {
        bind<MoviesDataSource>()
    }
}

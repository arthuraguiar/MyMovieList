package br.com.mymovieslist.di

import br.com.domain.repository.MovieRepository
import br.com.mymovieslist.data.mapper.MovieMapper
import br.com.mymovieslist.data.repository.MovieRepositoryImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataModule = module {
    factoryOf(::MovieMapper)
    singleOf(::MovieRepositoryImpl) {
        bind<MovieRepository>()
    }
}

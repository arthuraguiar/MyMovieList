package br.com.domain.di

import br.com.domain.usecase.GetPopularMoviesListUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::GetPopularMoviesListUseCase)
}

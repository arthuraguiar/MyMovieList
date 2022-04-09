package com.example.mymovieslist.core.di

import com.example.mymovieslist.data.repository.MovieRepositoryImpl
import com.example.mymovieslist.domain.repository.MovieRepository
import com.example.mymovieslist.domain.usecase.GetPopularMoviesListUseCase
import com.example.mymovieslist.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    factory<MovieRepository> {
        MovieRepositoryImpl()
    }
    factory {
        GetPopularMoviesListUseCase(
            movieRepository = get()
        )
    }

    viewModel {
        MainViewModel(get())
    }
}
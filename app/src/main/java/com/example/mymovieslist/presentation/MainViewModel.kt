package com.example.mymovieslist.presentation

import com.example.mymovieslist.core.viewmodel.BaseViewModel
import com.example.mymovieslist.domain.usecase.GetPopularMoviesListUseCase

class MainViewModel(
    private val getPopularMoviesListUseCase: GetPopularMoviesListUseCase
) : BaseViewModel<MainState>(MainState()) {
    init {
        getPopularMovies()
    }

    private fun getPopularMovies() {
        val movies = getPopularMoviesListUseCase()
        setState { it.copy(moviesList = movies, isEmptyState = movies.isEmpty()) }
    }
}
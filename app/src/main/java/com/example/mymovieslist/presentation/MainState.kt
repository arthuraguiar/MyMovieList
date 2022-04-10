package com.example.mymovieslist.presentation

import com.example.mymovieslist.core.viewmodel.state.ScreenState
import com.example.mymovieslist.domain.model.Movie

data class MainState(
    val isLoading: Boolean = false,
    val moviesList: List<Movie> = emptyList(),
    val isEmptyState: Boolean = false,
    val isErrorState: Boolean = false
) : ScreenState {
    fun getSuccessState(moviesList: List<Movie>): MainState {
        return this.copy(
            moviesList = moviesList,
            isErrorState = false,
            isEmptyState = moviesList.isEmpty(),
        )
    }

    fun getErrorState(): MainState {
        return this.copy(
            isErrorState = true
        )
    }
}

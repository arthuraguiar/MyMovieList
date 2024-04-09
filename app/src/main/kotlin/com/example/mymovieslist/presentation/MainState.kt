package com.example.mymovieslist.presentation

import com.example.mymovieslist.core.viewmodel.state.ScreenState
import com.example.domain.model.Movie
import com.example.domain.model.MovieSection

data class MainState(
    val isLoading: Boolean = false,
    var nowPlayingMovies: MovieSection = MovieSection("Now Playing", emptyList()),
    var popularMovies: MovieSection = MovieSection("Popular", emptyList()),
    var upcomingMovies: MovieSection = MovieSection("Upcoming", emptyList()),
    val isEmptyState: Boolean = false,
    val isErrorState: Boolean = false,
) : ScreenState {

    fun getErrorState(): MainState {
        return this.copy(
            isErrorState = true
        )
    }
}

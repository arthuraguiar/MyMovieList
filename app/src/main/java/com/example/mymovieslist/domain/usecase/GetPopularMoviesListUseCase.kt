package com.example.mymovieslist.domain.usecase

import com.example.mymovieslist.domain.model.Movie
import com.example.mymovieslist.domain.repository.MovieRepository

class GetPopularMoviesListUseCase(
    private val movieRepository: MovieRepository
) {

    operator fun invoke(): List<Movie> {
        return movieRepository.getPopularMoviesList()
    }

}
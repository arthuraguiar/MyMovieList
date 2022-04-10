package com.example.mymovieslist.domain.usecase

import com.example.mymovieslist.domain.model.Movie
import com.example.mymovieslist.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetPopularMoviesListUseCase(
    private val movieRepository: MovieRepository
) {

    operator fun invoke(): Flow<List<Movie>> {
        return movieRepository.getPopularMoviesList()
    }

}
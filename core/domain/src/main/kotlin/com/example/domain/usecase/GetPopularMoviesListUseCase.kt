package com.example.domain.usecase

import com.example.domain.model.Movie
import com.example.domain.repository.MovieRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetPopularMoviesListUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(page: Int): Flow<List<Movie>> {
        return movieRepository.getPopularMoviesList(page)
    }
}
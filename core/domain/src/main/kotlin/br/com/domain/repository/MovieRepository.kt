package br.com.domain.repository

import br.com.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getPopularMoviesList(
        page: Int
    ): Flow<List<Movie>>
}
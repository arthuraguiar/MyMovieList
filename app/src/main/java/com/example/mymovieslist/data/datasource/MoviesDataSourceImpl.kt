package com.example.mymovieslist.data.datasource

import com.example.common.extensions.flowOf
import com.example.mymovieslist.data.model.FetchPopularMoviesResponse
import com.example.mymovieslist.data.network.MovieService
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class MoviesDataSourceImpl @Inject constructor(
    private val movieService: MovieService,
): MoviesDataSource {

    override fun fetchPopularMovies(): Flow<FetchPopularMoviesResponse> {
        return flowOf {
            movieService.fetchPopularMovies()
        }
    }
}
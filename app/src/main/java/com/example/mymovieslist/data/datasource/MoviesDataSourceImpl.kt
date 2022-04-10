package com.example.mymovieslist.data.datasource

import com.example.mymovieslist.data.model.FetchPopularMoviesResponse
import com.example.mymovieslist.data.network.MovieService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MoviesDataSourceImpl(
    private val movieService: MovieService,
): MoviesDataSource {

    override fun fetchPopularMovies(): Flow<FetchPopularMoviesResponse> {
        return flow {
            emit(movieService.fetchPopularMovies())
        }
    }
}
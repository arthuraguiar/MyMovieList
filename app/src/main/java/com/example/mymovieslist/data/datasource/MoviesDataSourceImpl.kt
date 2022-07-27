package com.example.mymovieslist.data.datasource

import com.example.mymovieslist.data.model.FetchPopularMoviesResponse
import com.example.mymovieslist.data.network.MovieService
import com.example.mymovieslist.domain.mapper.parseException
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MoviesDataSourceImpl @Inject constructor(
    private val movieService: MovieService,
): MoviesDataSource {

    override fun fetchPopularMovies(): Flow<FetchPopularMoviesResponse> {
        return flow {
            emit(movieService.fetchPopularMovies())
        }.parseException()
    }
}
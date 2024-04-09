package com.example.mymovieslist.data_remote.network.datasource

import com.example.common.extensions.flowOf
import com.example.mymovieslist.data_remote.network.api.MovieService
import com.example.mymovieslist.data_remote.network.datasource.model.FetchPopularMoviesResponse
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class MoviesDataSourceImpl @Inject constructor(
    private val movieService: MovieService,
) : MoviesDataSource {

    override suspend fun fetchPopularMovies(
        page: Int,
    ): FetchPopularMoviesResponse {
        return  movieService.fetchPopularMovies(page)
    }
}
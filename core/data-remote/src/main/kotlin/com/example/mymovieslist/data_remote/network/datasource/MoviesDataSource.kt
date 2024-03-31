package com.example.mymovieslist.data_remote.network.datasource

import com.example.mymovieslist.data_remote.network.datasource.model.FetchPopularMoviesResponse
import kotlinx.coroutines.flow.Flow

interface MoviesDataSource {
    fun fetchPopularMovies(
        page: Int
    ): Flow<FetchPopularMoviesResponse>
}
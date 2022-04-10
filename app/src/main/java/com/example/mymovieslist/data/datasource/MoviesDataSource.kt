package com.example.mymovieslist.data.datasource

import com.example.mymovieslist.data.model.FetchPopularMoviesResponse
import kotlinx.coroutines.flow.Flow

interface MoviesDataSource {
    fun fetchPopularMovies(): Flow<FetchPopularMoviesResponse>
}
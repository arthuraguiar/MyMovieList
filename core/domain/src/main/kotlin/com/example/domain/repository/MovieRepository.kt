package com.example.domain.repository

import com.example.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getPopularMoviesList(
        page: Int
    ): Flow<List<Movie>>
}
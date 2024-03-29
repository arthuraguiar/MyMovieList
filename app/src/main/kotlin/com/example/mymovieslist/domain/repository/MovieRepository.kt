package com.example.mymovieslist.domain.repository

import com.example.mymovieslist.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getPopularMoviesList(): Flow<List<Movie>>
}
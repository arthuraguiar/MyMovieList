package com.example.mymovieslist.domain.repository

import com.example.mymovieslist.domain.model.Movie

interface MovieRepository {
    fun getPopularMoviesList(): List<Movie>
}
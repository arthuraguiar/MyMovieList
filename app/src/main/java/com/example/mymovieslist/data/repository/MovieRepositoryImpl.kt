package com.example.mymovieslist.data.repository

import com.example.mymovieslist.data.datasource.MoviesDataSource
import com.example.mymovieslist.domain.mapper.MovieMapper
import com.example.mymovieslist.domain.model.Movie
import com.example.mymovieslist.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepositoryImpl(
    private val moviesDataSource: MoviesDataSource,
    private val movieMapper: MovieMapper,
) : MovieRepository {
    override fun getPopularMoviesList(): Flow<List<Movie>>  =
        moviesDataSource.fetchPopularMovies().map { movieMapper.toMovieList(it) }
}
package com.example.mymovieslist.data.repository

import com.example.domain.model.Movie
import com.example.mymovieslist.data.mapper.MovieMapper
import com.example.domain.repository.MovieRepository
import com.example.mymovieslist.data_remote.network.datasource.MoviesDataSource
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepositoryImpl @Inject constructor(
    private val moviesDataSource: MoviesDataSource,
    private val movieMapper: MovieMapper,
) : MovieRepository {
    override fun getPopularMoviesList(
        page: Int
    ): Flow<List<Movie>>  =
        moviesDataSource.fetchPopularMovies(
            page
        ).map { movieMapper.toMovieList(it) }
}
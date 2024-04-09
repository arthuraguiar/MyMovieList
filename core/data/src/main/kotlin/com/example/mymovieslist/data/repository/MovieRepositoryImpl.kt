package com.example.mymovieslist.data.repository

import com.example.data_local.dao.MovieDao
import com.example.domain.model.Movie
import com.example.mymovieslist.data.mapper.MovieMapper
import com.example.domain.repository.MovieRepository
import com.example.mymovieslist.data.mapper.toDomain
import com.example.mymovieslist.data.mapper.toEntity
import com.example.mymovieslist.data_remote.network.datasource.MoviesDataSource
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepositoryImpl @Inject constructor(
    private val moviesDataSource: MoviesDataSource,
    private val movieDao: MovieDao,
    private val movieMapper: MovieMapper,
) : MovieRepository {
    override fun getPopularMoviesList(
        page: Int
    ): Flow<List<Movie>>  = flow {

        val localDbPage = movieDao.getMoviesFromPage(page).toDomain()

        if (localDbPage.isEmpty()) {
            val remote = movieMapper.toMovieList(
                moviesDataSource.fetchPopularMovies(page)
            )

            movieDao.insertAll(remote.toEntity(page))
        }

        emit(movieDao.getAllMovies(page).toDomain())
    }
}
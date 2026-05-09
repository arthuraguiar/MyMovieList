package br.com.mymovieslist.data.repository

import br.com.domain.model.Movie
import br.com.mymovieslist.data.mapper.MovieMapper
import br.com.domain.repository.MovieRepository
import br.com.mymovieslist.data_remote.network.datasource.MoviesDataSource
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class MovieRepositoryImpl @Inject constructor(
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
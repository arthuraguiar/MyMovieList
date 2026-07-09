package br.com.mymovieslist.data_remote.network.datasource

import br.com.extensions.flowOf
import br.com.mymovieslist.data_remote.network.api.MovieService
import br.com.mymovieslist.data_remote.network.datasource.model.FetchPopularMoviesResponse
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

internal class MoviesDataSourceImpl @Inject constructor(
    private val movieService: MovieService,
) : MoviesDataSource {

    override fun fetchPopularMovies(
        page: Int,
    ): Flow<FetchPopularMoviesResponse> {
        return flowOf { movieService.fetchPopularMovies(page) }

    }
}
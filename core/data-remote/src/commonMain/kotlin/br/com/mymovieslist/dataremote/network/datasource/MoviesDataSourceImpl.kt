package br.com.mymovieslist.dataremote.network.datasource

import br.com.extensions.flowOf
import br.com.mymovieslist.dataremote.network.api.MovieService
import br.com.mymovieslist.dataremote.network.datasource.model.FetchPopularMoviesResponse
import kotlinx.coroutines.flow.Flow

internal class MoviesDataSourceImpl(
    private val movieService: MovieService,
) : MoviesDataSource {

    override fun fetchPopularMovies(
        page: Int,
    ): Flow<FetchPopularMoviesResponse> {
        return flowOf { movieService.fetchPopularMovies(page) }
    }
}

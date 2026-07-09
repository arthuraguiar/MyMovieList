package br.com.mymovieslist.data_remote.network.api

import br.com.mymovieslist.data_remote.network.datasource.model.FetchPopularMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

private const val PAGE = "page"

internal interface MovieService {

    @GET("popular")
    suspend fun fetchPopularMovies(
        @Query(PAGE) page: Int,
    ): FetchPopularMoviesResponse
}
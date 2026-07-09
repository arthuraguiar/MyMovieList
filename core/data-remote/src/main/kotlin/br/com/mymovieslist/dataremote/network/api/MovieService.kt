package br.com.mymovieslist.dataremote.network.api

import br.com.mymovieslist.dataremote.network.datasource.model.FetchPopularMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

private const val PAGE = "page"

internal interface MovieService {

    @GET("popular")
    suspend fun fetchPopularMovies(
        @Query(PAGE) page: Int,
    ): FetchPopularMoviesResponse
}

package com.example.mymovieslist.data.network

import com.example.mymovieslist.data.model.FetchPopularMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("popular")
    suspend fun fetchPopularMovies(): FetchPopularMoviesResponse

    @GET("")
    suspend fun getMovieDetails(
        @Query("movie_id") movieId: Int
    )
}
package com.example.mymovieslist.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val originalLanguage: String,
    val releaseDate: String,
    val posterUrl: String
)

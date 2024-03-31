package com.example.mymovieslist.data.mapper

import com.example.data_local.entities.MovieEntity
import com.example.domain.model.Movie

fun List<Movie>.toEntity(page: Int): List<MovieEntity> {
    return this.map {
        MovieEntity(
            title = it.title,
            posterUrl = it.posterUrl,
            page = page,
            originalLanguage = it.originalLanguage,
            releaseDate = it.releaseDate
        )
    }
}

fun List<MovieEntity>.toDomain(): List<Movie> {
    return this.map {
        Movie(
            title = it.title,
            posterUrl = it.posterUrl,
            originalLanguage = it.originalLanguage,
            releaseDate = it.releaseDate
        )
    }
}
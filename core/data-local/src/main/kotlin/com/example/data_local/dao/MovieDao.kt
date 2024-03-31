package com.example.data_local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data_local.entities.MovieEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_entity where page = :page")
    suspend fun getMoviesFromPage(page: Int): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg movie: MovieEntity)
}
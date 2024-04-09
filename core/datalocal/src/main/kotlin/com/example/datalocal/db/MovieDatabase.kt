package com.example.data_local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data_local.dao.MovieDao
import com.example.data_local.entities.MovieEntity

private const val VERSION = 1
@Database(entities = [MovieEntity::class], version = VERSION)
internal abstract class MovieDatabase : RoomDatabase(){
    abstract fun movieDao(): MovieDao
}
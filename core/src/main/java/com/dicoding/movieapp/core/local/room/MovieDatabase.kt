package com.dicoding.movieapp.core.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dicoding.movieapp.core.local.entity.MovieEntity
import com.dicoding.movieapp.core.local.entity.TvShowEntity

@Database(
    entities = [MovieEntity::class, TvShowEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

}
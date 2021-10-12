package com.dicoding.movieapp.core.core_di

import android.content.Context
import androidx.room.Room
import com.dicoding.movieapp.core.local.room.MovieDao
import com.dicoding.movieapp.core.local.room.MovieDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun providesDatabase(context: Context): MovieDatabase = Room.databaseBuilder(
        context, MovieDatabase::class.java, "Movies.db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideMovieDao(database: MovieDatabase): MovieDao = database.movieDao()
}
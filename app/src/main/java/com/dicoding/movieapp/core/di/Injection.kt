package com.dicoding.movieapp.core.di

import android.content.Context
import com.dicoding.movieapp.core.local.room.MovieDatabase
import com.dicoding.movieapp.core.network.config.ApiConfig
import com.dicoding.movieapp.core.source.LocalDataSource
import com.dicoding.movieapp.core.source.MovieRepository
import com.dicoding.movieapp.core.source.RemoteDataSource
import com.dicoding.movieapp.core.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context) : MovieRepository {
        val database = MovieDatabase.getInstance(context)

        val remoteDataSource =  RemoteDataSource.getInstance(ApiConfig.provideApiService())
        val localDataSource = LocalDataSource.getInstance(database.movieDao())
        val appExecutors = AppExecutors()

        return  MovieRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}
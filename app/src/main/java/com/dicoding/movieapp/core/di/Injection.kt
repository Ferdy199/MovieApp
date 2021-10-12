package com.dicoding.movieapp.core.di

import android.content.Context
import com.dicoding.movieapp.core.domain.repository.MovieDataSource
import com.dicoding.movieapp.core.domain.repository.MovieRepository
import com.dicoding.movieapp.core.domain.usecase.MovieInteractor
import com.dicoding.movieapp.core.domain.usecase.MovieUseCase
import com.dicoding.movieapp.core.local.room.MovieDatabase
import com.dicoding.movieapp.core.network.config.ApiConfig
import com.dicoding.movieapp.core.source.LocalDataSource
import com.dicoding.movieapp.core.source.RemoteDataSource
import com.dicoding.movieapp.core.utils.AppExecutors

object Injection {
    private fun provideRepository(context: Context) : MovieDataSource {
        val database = MovieDatabase.getInstance(context)

        val remoteDataSource =  RemoteDataSource.getInstance(ApiConfig.provideApiService())
        val localDataSource = LocalDataSource.getInstance(database.movieDao())
        val appExecutors = AppExecutors()

        return  MovieRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideMovieUseCase(context: Context): MovieUseCase{
        val repository = provideRepository(context)
        return MovieInteractor(repository)
    }
}
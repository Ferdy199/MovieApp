package com.dicoding.movieapp.core.core_di

import com.dicoding.movieapp.core.domain.repository.MovieDataSource
import com.dicoding.movieapp.core.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module

@Module(includes = [NetworkModule::class, DatabaseModule::class])
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(movieRepository: MovieRepository): MovieDataSource
}
package com.dicoding.movieapp.di

import com.dicoding.movieapp.core.domain.usecase.MovieInteractor
import com.dicoding.movieapp.core.domain.usecase.MovieUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {
    @Binds
    abstract fun provideMovieUseCase(movieInteractor: MovieInteractor): MovieUseCase
}
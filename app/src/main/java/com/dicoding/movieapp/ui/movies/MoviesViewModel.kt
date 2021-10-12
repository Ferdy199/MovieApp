package com.dicoding.movieapp.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.movieapp.core.domain.usecase.MovieUseCase

class MoviesViewModel(movieUseCase: MovieUseCase) : ViewModel() {

    val getAllMovies = movieUseCase.getAllMovies().asLiveData()
}
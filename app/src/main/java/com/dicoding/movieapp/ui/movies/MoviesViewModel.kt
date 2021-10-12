package com.dicoding.movieapp.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.movieapp.core.domain.usecase.MovieUseCase
import javax.inject.Inject

class MoviesViewModel @Inject constructor(movieUseCase: MovieUseCase) : ViewModel() {

    val getAllMovies = movieUseCase.getAllMovies().asLiveData()
}
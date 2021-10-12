package com.dicoding.movieapp.ui.tvShow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.movieapp.core.domain.usecase.MovieUseCase
import javax.inject.Inject

class TvShowViewModel @Inject constructor(movieUseCase: MovieUseCase) : ViewModel() {
    val getAllTvShow = movieUseCase.getAllTvShow().asLiveData()
}
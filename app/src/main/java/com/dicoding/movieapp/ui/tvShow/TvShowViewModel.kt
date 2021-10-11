package com.dicoding.movieapp.ui.tvShow

import androidx.lifecycle.ViewModel
import com.dicoding.movieapp.core.domain.usecase.MovieUseCase

class TvShowViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val getAllTvShow = movieUseCase.getAllTvShow()
}
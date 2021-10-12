package com.dicoding.movieapp.ui.favorite.tvfav

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.movieapp.core.domain.usecase.MovieUseCase

class TvFavoriteViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun getTvShowFavorite() = movieUseCase.getTvShowFavorite().asLiveData()
}
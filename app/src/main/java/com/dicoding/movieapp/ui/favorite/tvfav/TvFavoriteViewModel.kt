package com.dicoding.movieapp.ui.favorite.tvfav

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.movieapp.core.domain.usecase.MovieUseCase
import javax.inject.Inject

class TvFavoriteViewModel @Inject constructor(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun getTvShowFavorite() = movieUseCase.getTvShowFavorite().asLiveData()
}
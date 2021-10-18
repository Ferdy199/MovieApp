package com.dicoding.movieapp.favorite.tvfav

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.movieapp.core.domain.usecase.MovieUseCase
import javax.inject.Inject

class TvShowFavViewModel @Inject constructor(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun getTvShowFavorite() = movieUseCase.getTvShowFavorite().asLiveData()
}
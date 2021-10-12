package com.dicoding.movieapp.ui.favorite.moviefav

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.movieapp.core.domain.usecase.MovieUseCase

class MovieFavoriteViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun getMovieFavorite() = movieUseCase.getMovieFavorite().asLiveData()
}
package com.dicoding.movieapp.favorite.moviefav

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.movieapp.core.domain.usecase.MovieUseCase
import javax.inject.Inject

class MovieFavViewModel @Inject constructor(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun getMovieFavorite() = movieUseCase.getMovieFavorite().asLiveData()
}
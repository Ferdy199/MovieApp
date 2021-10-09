package com.dicoding.movieapp.ui.favorite.moviefav

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.movieapp.core.local.entity.MovieEntity
import com.dicoding.movieapp.core.source.MovieRepository

class MovieFavoriteViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    fun getMovieFavorite() : LiveData<List<MovieEntity>> = movieRepository.getMovieFavorite()
}
package com.dicoding.movieapp.ui.favorite.tvfav

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.movieapp.core.local.entity.TvShowEntity
import com.dicoding.movieapp.core.source.MovieRepository

class TvFavoriteViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    fun getTvShowFavorite() : LiveData<List<TvShowEntity>> = movieRepository.getTvShowFavorite()
}
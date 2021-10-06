package com.dicoding.movieapp.ui.tvShow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.movieapp.core.local.entity.TvShowEntity
import com.dicoding.movieapp.core.network.response.DataTvShowResponse
import com.dicoding.movieapp.core.source.MovieRepository
import com.dicoding.movieapp.core.utils.Resource

class TvShowViewModel(private val MovieRepository: MovieRepository) : ViewModel() {
    fun getAllTvShow() : LiveData<Resource<List<TvShowEntity>>> = MovieRepository.getAllTvShow()
}
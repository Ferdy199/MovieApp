package com.dicoding.movieapp.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.movieapp.core.local.entity.MovieEntity
import com.dicoding.movieapp.core.network.response.DataMovieResponse
import com.dicoding.movieapp.core.source.MovieRepository
import com.dicoding.movieapp.core.utils.Resource

class MoviesViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    fun getAllMovies() : LiveData<Resource<List<MovieEntity>>> = movieRepository.getAllMovies()
}
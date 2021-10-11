package com.dicoding.movieapp.core.domain.repository

import androidx.lifecycle.LiveData
import com.dicoding.movieapp.core.domain.model.Movie
import com.dicoding.movieapp.core.domain.model.TvShow
import com.dicoding.movieapp.core.utils.Resource

interface MovieDataSource {
    fun getAllMovies() : LiveData<Resource<List<Movie>>>

    fun getAllTvShow() : LiveData<Resource<List<TvShow>>>

    fun getDetailMovie(movie_id : Int): LiveData<Resource<Movie>>

    fun getDetailTvShow(tvShow_id : Int) : LiveData<Resource<TvShow>>

    fun getMovieFavorite(): LiveData<List<Movie>>

    fun getTvShowFavorite(): LiveData<List<TvShow>>

    fun setMovieFavorite(movie : Movie, state: Boolean)

    fun setTvShowFavorite(tvShow : TvShow, state: Boolean)
}
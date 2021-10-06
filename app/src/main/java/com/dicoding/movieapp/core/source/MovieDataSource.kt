package com.dicoding.movieapp.core.source

import androidx.lifecycle.LiveData
import com.dicoding.movieapp.core.local.entity.MovieEntity
import com.dicoding.movieapp.core.local.entity.TvShowEntity
import com.dicoding.movieapp.core.network.response.ApiResponse
import com.dicoding.movieapp.core.network.response.DataMovieResponse
import com.dicoding.movieapp.core.network.response.DataTvShowResponse
import com.dicoding.movieapp.core.utils.Resource

interface MovieDataSource {
    fun getAllMovies() : LiveData<Resource<List<MovieEntity>>>

    fun getAllTvShow() : LiveData<Resource<List<TvShowEntity>>>

    fun getDetailMovie(movie_id : Int): LiveData<Resource<MovieEntity>>

    fun getDetailTvShow(tvShow_id : Int) : LiveData<Resource<TvShowEntity>>

    fun getMovieFavorite(): LiveData<List<MovieEntity>>

    fun getTvShowFavorite(): LiveData<List<TvShowEntity>>

    fun setMovieFavorite(movie : MovieEntity, state: Boolean)

    fun setTvShowFavorite(tvShow : TvShowEntity, state: Boolean)
}
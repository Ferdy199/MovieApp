package com.dicoding.movieapp.core.domain.repository

import com.dicoding.movieapp.core.domain.model.Movie
import com.dicoding.movieapp.core.domain.model.TvShow
import com.dicoding.movieapp.core.utils.Resource
import kotlinx.coroutines.flow.Flow

interface MovieDataSource {
    fun getAllMovies() : Flow<Resource<List<Movie>>>

    fun getAllTvShow() : Flow<Resource<List<TvShow>>>

    fun getDetailMovie(movie_id : Int): Flow<Resource<Movie>>

    fun getDetailTvShow(tvShow_id : Int) : Flow<Resource<TvShow>>

    fun getMovieFavorite(): Flow<List<Movie>>

    fun getTvShowFavorite(): Flow<List<TvShow>>

    fun setMovieFavorite(movie : Movie, state: Boolean)

    fun setTvShowFavorite(tvShow : TvShow, state: Boolean)
}
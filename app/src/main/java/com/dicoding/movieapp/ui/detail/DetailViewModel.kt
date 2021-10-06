package com.dicoding.movieapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.dicoding.movieapp.core.local.entity.MovieEntity
import com.dicoding.movieapp.core.local.entity.TvShowEntity
import com.dicoding.movieapp.core.source.MovieRepository
import com.dicoding.movieapp.core.utils.Resource

class DetailViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    var movieId = MutableLiveData<Int>()

    fun setMovieId(movie_id : Int){
        this.movieId.value = movie_id
    }

    fun getDetailMovie() : LiveData<Resource<MovieEntity>> = Transformations.switchMap(movieId){
        movieRepository.getDetailMovie(it)
    }
    fun getDetailTvShow() : LiveData<Resource<TvShowEntity>> = Transformations.switchMap(movieId){
        movieRepository.getDetailTvShow(it)
    }

    fun setMovieFavorite(movie : MovieEntity, newState : Boolean){
        return movieRepository.setMovieFavorite(movie, newState)
    }

    fun setTvShowFavorite(tvShow : TvShowEntity, newState: Boolean){
        return movieRepository.setTvShowFavorite(tvShow, newState)
    }
}
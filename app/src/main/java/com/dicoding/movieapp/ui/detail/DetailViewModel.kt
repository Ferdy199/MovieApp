package com.dicoding.movieapp.ui.detail

import androidx.lifecycle.*
import com.dicoding.movieapp.core.domain.model.Movie
import com.dicoding.movieapp.core.domain.model.TvShow
import com.dicoding.movieapp.core.domain.usecase.MovieUseCase
import com.dicoding.movieapp.core.utils.Resource
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val movieUseCase: MovieUseCase) : ViewModel() {
    private var movieId = MutableLiveData<Int>()

    fun setMovieId(movie_id: Int) {
        this.movieId.value = movie_id
    }

    fun getDetailMovie(): LiveData<Resource<Movie>> = Transformations.switchMap(movieId) {
        movieUseCase.getDetailMovie(it).asLiveData()
    }

    fun getDetailTvShow(): LiveData<Resource<TvShow>> = Transformations.switchMap(movieId) {
        movieUseCase.getDetailTvShow(it).asLiveData()
    }

    fun setMovieFavorite(movie: Movie, newState: Boolean) {
        return movieUseCase.setMovieFavorite(movie, newState)
    }

    fun setTvShowFavorite(tvShow: TvShow, newState: Boolean) {
        return movieUseCase.setTvShowFavorite(tvShow, newState)
    }
}
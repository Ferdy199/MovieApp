package com.dicoding.movieapp.core.domain.usecase

import androidx.lifecycle.LiveData
import com.dicoding.movieapp.core.domain.model.Movie
import com.dicoding.movieapp.core.domain.model.TvShow
import com.dicoding.movieapp.core.domain.repository.MovieDataSource
import com.dicoding.movieapp.core.utils.Resource

class MovieInteractor(private val movieRepository: MovieDataSource) : MovieUseCase {
    override fun getAllMovies(): LiveData<Resource<List<Movie>>> {
        return movieRepository.getAllMovies()
    }

    override fun getAllTvShow(): LiveData<Resource<List<TvShow>>> {
        return movieRepository.getAllTvShow()
    }

    override fun getDetailMovie(movie_id: Int): LiveData<Resource<Movie>> {
        return movieRepository.getDetailMovie(movie_id)
    }

    override fun getDetailTvShow(tvShow_id: Int): LiveData<Resource<TvShow>> {
        return movieRepository.getDetailTvShow(tvShow_id)
    }

    override fun getMovieFavorite(): LiveData<List<Movie>> {
        return movieRepository.getMovieFavorite()
    }

    override fun getTvShowFavorite(): LiveData<List<TvShow>> {
        return movieRepository.getTvShowFavorite()
    }

    override fun setMovieFavorite(movie: Movie, state: Boolean) {
        return movieRepository.setMovieFavorite(movie, state)
    }

    override fun setTvShowFavorite(tvShow: TvShow, state: Boolean) {
        return movieRepository.setTvShowFavorite(tvShow, state)
    }
}
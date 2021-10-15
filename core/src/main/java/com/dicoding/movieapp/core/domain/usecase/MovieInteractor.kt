package com.dicoding.movieapp.core.domain.usecase

import com.dicoding.movieapp.core.domain.model.Movie
import com.dicoding.movieapp.core.domain.model.TvShow
import com.dicoding.movieapp.core.domain.repository.MovieDataSource
import com.dicoding.movieapp.core.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieInteractor @Inject constructor(private val movieRepository: MovieDataSource) :
    MovieUseCase {
    override fun getAllMovies(): Flow<Resource<List<Movie>>> {
        return movieRepository.getAllMovies()
    }

    override fun getAllTvShow(): Flow<Resource<List<TvShow>>> {
        return movieRepository.getAllTvShow()
    }

    override fun getDetailMovie(movie_id: Int): Flow<Resource<Movie>> {
        return movieRepository.getDetailMovie(movie_id)
    }

    override fun getDetailTvShow(tvShow_id: Int): Flow<Resource<TvShow>> {
        return movieRepository.getDetailTvShow(tvShow_id)
    }

    override fun getMovieFavorite(): Flow<List<Movie>> {
        return movieRepository.getMovieFavorite()
    }

    override fun getTvShowFavorite(): Flow<List<TvShow>> {
        return movieRepository.getTvShowFavorite()
    }

    override fun setMovieFavorite(movie: Movie, state: Boolean) {
        return movieRepository.setMovieFavorite(movie, state)
    }

    override fun setTvShowFavorite(tvShow: TvShow, state: Boolean) {
        return movieRepository.setTvShowFavorite(tvShow, state)
    }
}
package com.dicoding.movieapp.core.source

import com.dicoding.movieapp.core.local.entity.MovieEntity
import com.dicoding.movieapp.core.local.entity.TvShowEntity
import com.dicoding.movieapp.core.local.room.MovieDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val movieDao: MovieDao) {

    //movies
    fun getAllMovies(): Flow<List<MovieEntity>> = movieDao.getAllMovies()
    fun getMovieFavorite(): Flow<List<MovieEntity>> = movieDao.getMovieFavorite()
    fun getDetailMovie(movie_id: Int): Flow<MovieEntity> = movieDao.getDetailMovie(movie_id)
    suspend fun insertMovies(moviesList: List<MovieEntity>) = movieDao.insertMovies(moviesList)
    fun setFavoriteMovies(movies: MovieEntity, newState: Boolean) {
        movies.favorite = newState
        movieDao.updateMovies(movies)
    }

    //tv show
    fun getAllTvShow(): Flow<List<TvShowEntity>> = movieDao.getAllTvShow()
    fun getTvShowFavorite(): Flow<List<TvShowEntity>> = movieDao.getTvShowFavorite()
    fun getDetailTvShow(tvShow_id: Int): Flow<TvShowEntity> = movieDao.getDetailTvShow(tvShow_id)
    suspend fun insertTvShow(tvShowList: List<TvShowEntity>) = movieDao.insertTvShow(tvShowList)
    fun setFavoriteTvShow(tvShow: TvShowEntity, newState: Boolean) {
        tvShow.favorite = newState
        movieDao.updateTvShow(tvShow)
    }
}
package com.dicoding.movieapp.core.source

import androidx.lifecycle.LiveData
import com.dicoding.movieapp.core.local.entity.MovieEntity
import com.dicoding.movieapp.core.local.entity.TvShowEntity
import com.dicoding.movieapp.core.local.room.MovieDao

class LocalDataSource private constructor(private val movieDao : MovieDao){
    companion object{
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(movieDao: MovieDao) : LocalDataSource =
            INSTANCE ?: LocalDataSource(movieDao)
    }

    //movies
    fun getAllMovies() : LiveData<List<MovieEntity>> = movieDao.getAllMovies()
    fun getMovieFavorite() : LiveData<List<MovieEntity>> = movieDao.getMovieFavorite()
    fun getDetailMovie(movie_id : Int) : LiveData<MovieEntity> = movieDao.getDetailMovie(movie_id)
    fun insertMovies(moviesList : List<MovieEntity>) = movieDao.insertMovies(moviesList)
    fun setFavoriteMovies(movies : MovieEntity, newState: Boolean){
        movies.favorite = newState
        movieDao.updateMovies(movies)
    }

    //tv show
    fun getAllTvShow() : LiveData<List<TvShowEntity>> = movieDao.getAllTvShow()
    fun getTvShowFavorite() : LiveData<List<TvShowEntity>> = movieDao.getTvShowFavorite()
    fun getDetailTvShow(tvShow_id : Int) : LiveData<TvShowEntity> = movieDao.getDetailTvShow(tvShow_id)
    fun insertTvShow(tvShowList : List<TvShowEntity>) = movieDao.insertTvShow(tvShowList)
    fun setFavoriteTvShow(tvShow : TvShowEntity, newState: Boolean){
        tvShow.favorite = newState
        movieDao.updateTvShow(tvShow)
    }
}
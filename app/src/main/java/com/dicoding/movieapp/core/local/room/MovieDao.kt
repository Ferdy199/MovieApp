package com.dicoding.movieapp.core.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.dicoding.movieapp.core.local.entity.MovieEntity
import com.dicoding.movieapp.core.local.entity.TvShowEntity
import com.dicoding.movieapp.core.utils.Resource

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie")
    fun getAllMovies() : LiveData<List<MovieEntity>>

    @Query("SELECT * FROM movie WHERE movie_favorite = 1 ")
    fun getMovieFavorite() : LiveData<List<MovieEntity>>

    @Query("SELECT * FROM tvShow")
    fun getAllTvShow() : LiveData<List<TvShowEntity>>

    @Query("SELECT * FROM tvShow WHERE tvShow_favorite = 1")
    fun getTvShowFavorite() : LiveData<List<TvShowEntity>>

    @Transaction
    @Query("SELECT * FROM movie WHERE id = :id")
    fun getDetailMovie(id : Int) : LiveData<MovieEntity>

    @Transaction
    @Query("SELECT *FROM tvShow WHERE id = :id")
    fun getDetailTvShow(id: Int) : LiveData<TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies : List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShow(tvShow : List<TvShowEntity>)

    @Update
    fun updateMovies(movies : MovieEntity)

    @Update
    fun updateTvShow(tvShow : TvShowEntity)
}
package com.dicoding.movieapp.core.local.room

import androidx.room.*
import com.dicoding.movieapp.core.local.entity.MovieEntity
import com.dicoding.movieapp.core.local.entity.TvShowEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie")
    fun getAllMovies() : Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie WHERE movie_favorite = 1 ")
    fun getMovieFavorite() : Flow<List<MovieEntity>>

    @Query("SELECT * FROM tvShow")
    fun getAllTvShow() : Flow<List<TvShowEntity>>

    @Query("SELECT * FROM tvShow WHERE tvShow_favorite = 1")
    fun getTvShowFavorite() : Flow<List<TvShowEntity>>

    @Transaction
    @Query("SELECT * FROM movie WHERE id = :id")
    fun getDetailMovie(id : Int) : Flow<MovieEntity>

    @Transaction
    @Query("SELECT *FROM tvShow WHERE id = :id")
    fun getDetailTvShow(id: Int) : Flow<TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies : List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvShow(tvShow : List<TvShowEntity>)

    @Update
    fun updateMovies(movies : MovieEntity)

    @Update
    fun updateTvShow(tvShow : TvShowEntity)
}
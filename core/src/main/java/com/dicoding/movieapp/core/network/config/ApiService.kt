package com.dicoding.movieapp.core.network.config

import com.dicoding.movieapp.core.BuildConfig
import com.dicoding.movieapp.core.network.response.DataMovieResponse
import com.dicoding.movieapp.core.network.response.DataTvShowResponse
import com.dicoding.movieapp.core.network.response.ResponseMovie
import com.dicoding.movieapp.core.network.response.ResponseTvShow
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("3/trending/movie/week")
    suspend fun getMovie(
        @Query("api_key") query: String = BuildConfig.TMDB_TOKEN
    ): ResponseMovie

    @GET("3/trending/tv/week")
    suspend fun getTvShow(
        @Query("api_key") query: String = BuildConfig.TMDB_TOKEN
    ): ResponseTvShow

    @GET("/3/movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") query: String = BuildConfig.TMDB_TOKEN
    ): DataMovieResponse

    @GET("/3/tv/{tv_id}")
    suspend fun getTvShowDetail(
        @Path("tv_id") tvShow_id: Int,
        @Query("api_key") query: String = BuildConfig.TMDB_TOKEN
    ): DataTvShowResponse
}
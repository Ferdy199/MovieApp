package com.dicoding.movieapp.core.network.config

import com.dicoding.movieapp.BuildConfig
import com.dicoding.movieapp.core.network.response.DataMovieResponse
import com.dicoding.movieapp.core.network.response.DataTvShowResponse
import com.dicoding.movieapp.core.network.response.ResponseMovie
import com.dicoding.movieapp.core.network.response.ResponseTvShow
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("3/trending/movie/week")
    fun getMovie(
        @Query("api_key")query : String = BuildConfig.TMDB_TOKEN
    ): Call<ResponseMovie>

    @GET("3/trending/tv/week")
    fun getTvShow(
        @Query("api_key")query : String = BuildConfig.TMDB_TOKEN
    ): Call<ResponseTvShow>

    @GET("/3/movie/{movie_id}")
    fun getMovieDetail(
        @Path("movie_id") movie_id : Int,
        @Query("api_key")query : String = BuildConfig.TMDB_TOKEN
    ): Call<DataMovieResponse>

    @GET("/3/tv/{tv_id}")
    fun getTvShowDetail(
        @Path("tv_id") tvShow_id : Int,
        @Query("api_key")query : String = BuildConfig.TMDB_TOKEN
    ): Call<DataTvShowResponse>
}
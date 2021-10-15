package com.dicoding.movieapp.core.network.response

import com.google.gson.annotations.SerializedName

data class DataMovieResponse(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("release_date")
    val release_date: String,

    @field:SerializedName("backdrop_path")
    val backdrop_path: String,

    @field:SerializedName("original_title")
    val original_title: String,

    @field:SerializedName("poster_path")
    val poster_path: String,

    @field:SerializedName("vote_average")
    val vote_average: Float
) {
    val imageBaseUrl get() = "https://image.tmdb.org/t/p/w500"
}
package com.dicoding.movieapp.core.network.response

import com.google.gson.annotations.SerializedName

data class DataTvShowResponse(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("first_air_date")
    val first_air_date: String,

    @field:SerializedName("backdrop_path")
    val backdrop_path: String,

    @field:SerializedName("original_name")
    val original_name: String,

    @field:SerializedName("poster_path")
    val poster_path: String,

    @field:SerializedName("vote_average")
    val vote_average: Float
) {
    val imageBaseUrl get() = "https://image.tmdb.org/t/p/w500"
}
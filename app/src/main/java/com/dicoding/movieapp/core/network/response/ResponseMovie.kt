package com.dicoding.movieapp.core.network.response

import com.google.gson.annotations.SerializedName

data class ResponseMovie(
    @field:SerializedName("page")
    val page: Int? = null,

    @field:SerializedName("results")
    val results: List<DataMovieResponse>? = null,

    )
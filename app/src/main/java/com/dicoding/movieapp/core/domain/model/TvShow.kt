package com.dicoding.movieapp.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShow(
    var id: Int,
    var overview: String,
    var first_air_date: String,
    var backdrop_path: String,
    var original_name: String,
    var poster_path: String,
    var vote_average: Float,
    var favorite: Boolean
) : Parcelable {
    val imageBaseUrl get() = "https://image.tmdb.org/t/p/w500"
}
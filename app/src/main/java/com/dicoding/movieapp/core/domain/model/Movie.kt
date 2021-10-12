package com.dicoding.movieapp.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    var id: Int,
    var overview: String,
    var release_date: String,
    var backdrop_path: String,
    var original_title: String,
    var poster_path: String,
    var vote_average: Float,
    var favorite: Boolean
) : Parcelable {
    val imageBaseUrl get() = "https://image.tmdb.org/t/p/w500"
}
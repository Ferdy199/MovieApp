package com.dicoding.movieapp.core.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "overview")
    var overview: String,

    @ColumnInfo(name = "release_date")
    var release_date: String,

    @ColumnInfo(name = "backdrop_path")
    var backdrop_path: String,

    @ColumnInfo(name = "original_title")
    var original_title: String,

    @ColumnInfo(name = "poster_path")
    var poster_path: String,

    @ColumnInfo(name = "vote_average")
    var vote_average: Float,

    @ColumnInfo(name = "movie_favorite")
    var favorite: Boolean
) : Parcelable {
    val imageBaseUrl get() = "https://image.tmdb.org/t/p/w500"
}
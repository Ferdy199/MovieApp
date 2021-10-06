package com.dicoding.movieapp.core.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "tvShow")
data class TvShowEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id : Int,

    @ColumnInfo(name = "overview")
    var overview : String,

    @ColumnInfo(name = "first_air_date")
    var first_air_date : String,

    @ColumnInfo(name = "backdrop_path")
    var backdrop_path : String,

    @ColumnInfo(name = "original_name")
    var original_name : String,

    @ColumnInfo(name = "poster_path")
    var poster_path : String,

    @ColumnInfo(name = "vote_average")
    var vote_average : Float,

    @ColumnInfo(name = "tvShow_favorite")
    var favorite : Boolean
) : Parcelable{
    val imageBaseUrl get() = "https://image.tmdb.org/t/p/w500"
}
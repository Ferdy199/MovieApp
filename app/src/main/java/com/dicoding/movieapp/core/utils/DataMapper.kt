package com.dicoding.movieapp.core.utils

import com.dicoding.movieapp.core.local.entity.MovieEntity
import com.dicoding.movieapp.core.local.entity.TvShowEntity
import com.dicoding.movieapp.core.network.response.DataMovieResponse
import com.dicoding.movieapp.core.network.response.DataTvShowResponse

object DataMapper {
    fun mapMovieResponsesToEntities(input : List<DataMovieResponse>) : List<MovieEntity>{
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                id = it.id,
                overview = it.overview,
                release_date = it.release_date,
                backdrop_path = it.backdrop_path,
                original_title = it.original_title,
                poster_path = it.poster_path,
                vote_average = it.vote_average,
                favorite = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapTvShowResponsesToEntities(input : List<DataTvShowResponse>) : List<TvShowEntity>{
        val tvShowList = ArrayList<TvShowEntity>()
        input.map {
            val tvShow = TvShowEntity(
                id = it.id,
                overview = it.overview,
                first_air_date = it.first_air_date,
                backdrop_path = it.backdrop_path,
                original_name = it.original_name,
                poster_path = it.poster_path,
                vote_average = it.vote_average,
                favorite = false
            )
            tvShowList.add(tvShow)
        }
        return tvShowList
    }

    fun mapDetailMovieResponsesToEntities(input : DataMovieResponse) : List<MovieEntity>{
        val detailMovieList = ArrayList<MovieEntity>()
        val dataMovie = MovieEntity(
            id = input.id,
            overview = input.overview,
            release_date = input.release_date,
            backdrop_path =input.backdrop_path,
            original_title = input.original_title,
            poster_path = input.poster_path,
            vote_average = input.vote_average,
            favorite = false
        )
        detailMovieList.add(dataMovie)
        return detailMovieList
    }

    fun mapDetailTvShowReponsesToEntities(input : DataTvShowResponse) : List<TvShowEntity>{
        val detailTvShowList = ArrayList<TvShowEntity>()
        val dataTvShow = TvShowEntity(
            id = input.id,
            overview = input.overview,
            first_air_date = input.first_air_date,
            backdrop_path =input.backdrop_path,
            original_name = input.original_name,
            poster_path = input.poster_path,
            vote_average = input.vote_average,
            favorite = false
        )
        detailTvShowList.add(dataTvShow)
        return detailTvShowList
    }
}
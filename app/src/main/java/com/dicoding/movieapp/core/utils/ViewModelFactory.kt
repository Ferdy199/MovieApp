package com.dicoding.movieapp.core.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.movieapp.core.di.Injection
import com.dicoding.movieapp.core.domain.usecase.MovieUseCase
import com.dicoding.movieapp.ui.detail.DetailViewModel
import com.dicoding.movieapp.ui.favorite.moviefav.MovieFavoriteViewModel
import com.dicoding.movieapp.ui.favorite.tvfav.TvFavoriteViewModel
import com.dicoding.movieapp.ui.movies.MoviesViewModel
import com.dicoding.movieapp.ui.tvShow.TvShowViewModel

class ViewModelFactory private constructor(private val movieUseCase: MovieUseCase) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(MoviesViewModel::class.java) -> {
                MoviesViewModel(movieUseCase) as T
            }
            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> {
                TvShowViewModel(movieUseCase) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) ->{
                DetailViewModel(movieUseCase) as T
            }
            modelClass.isAssignableFrom(MovieFavoriteViewModel::class.java) -> {
                MovieFavoriteViewModel(movieUseCase) as T
            }
            modelClass.isAssignableFrom(TvFavoriteViewModel::class.java) -> {
                TvFavoriteViewModel(movieUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel Class: " + modelClass.name)
        }
    }


    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context) : ViewModelFactory =
            instance ?: synchronized(this){
                instance ?: ViewModelFactory(Injection.provideMovieUseCase(context)).apply {
                    instance = this
                }
            }
    }
}
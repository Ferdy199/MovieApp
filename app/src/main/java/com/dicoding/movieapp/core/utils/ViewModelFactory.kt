package com.dicoding.movieapp.core.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.movieapp.core.domain.usecase.MovieUseCase
import com.dicoding.movieapp.di.AppScope
import com.dicoding.movieapp.ui.detail.DetailViewModel
import com.dicoding.movieapp.ui.favorite.moviefav.MovieFavoriteViewModel
import com.dicoding.movieapp.ui.favorite.tvfav.TvFavoriteViewModel
import com.dicoding.movieapp.ui.movies.MoviesViewModel
import com.dicoding.movieapp.ui.tvShow.TvShowViewModel
import javax.inject.Inject
import javax.inject.Provider

@AppScope
class ViewModelFactory @Inject constructor(
    private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
    ) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val creator = creators[modelClass] ?: creators.entries.firstOrNull(){
            modelClass.isAssignableFrom(it.key)
        }?.value ?: throw IllegalArgumentException("unknown model class $modelClass")
        return creator.get() as T
    }
}
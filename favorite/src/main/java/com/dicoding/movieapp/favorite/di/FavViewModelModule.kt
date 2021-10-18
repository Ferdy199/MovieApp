package com.dicoding.movieapp.favorite.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.movieapp.core.utils.ViewModelFactory
import com.dicoding.movieapp.favorite.moviefav.MovieFavViewModel
import com.dicoding.movieapp.favorite.tvfav.TvShowFavViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class FavViewModelModule {
    @Binds
    @IntoMap
    @FavViewModelKey(MovieFavViewModel::class)
    abstract fun bindMovieFavViewModel(viewModel: MovieFavViewModel) : ViewModel

    @Binds
    @IntoMap
    @FavViewModelKey(TvShowFavViewModel::class)
    abstract fun bindTvShowFavViewModel(viewModel: TvShowFavViewModel) : ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory : ViewModelFactory): ViewModelProvider.Factory
}
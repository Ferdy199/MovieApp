package com.dicoding.movieapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.movieapp.core.utils.ViewModelFactory
import com.dicoding.movieapp.ui.detail.DetailViewModel
import com.dicoding.movieapp.ui.favorite.moviefav.MovieFavoriteViewModel
import com.dicoding.movieapp.ui.favorite.tvfav.TvFavoriteViewModel
import com.dicoding.movieapp.ui.movies.MoviesViewModel
import com.dicoding.movieapp.ui.tvShow.TvShowViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MoviesViewModel::class)
    abstract fun bindHomeViewModel(viewModel: MoviesViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TvShowViewModel::class)
    abstract fun bindTvShowViewModel(viewModel: TvShowViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieFavoriteViewModel::class)
    abstract fun bindMovieFavoriteViewModel(viewModel: MovieFavoriteViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TvFavoriteViewModel::class)
    abstract fun bindTvShowFavoriteViewModel(viewModel: TvFavoriteViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindDetailViewModel(viewModel: DetailViewModel) : ViewModel

    @Binds
    abstract fun viewModelFactory(factory: ViewModelFactory) : ViewModelProvider.Factory
}
package com.dicoding.movieapp.favorite.di

import com.dicoding.movieapp.core.core_di.CoreComponent
import com.dicoding.movieapp.favorite.FavoriteActivity
import com.dicoding.movieapp.favorite.moviefav.MovieFavFragment
import com.dicoding.movieapp.favorite.tvfav.TvShowFavFragment
import dagger.Component


@FavScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [FavModule::class, FavViewModelModule::class]
)
interface FavComponent {
    @Component.Factory
    interface FavFactory{
        fun create(coreComponent: CoreComponent) : FavComponent
    }

    fun inject(activity: FavoriteActivity)
    fun inject(fragment: MovieFavFragment)
    fun inject(fragment: TvShowFavFragment)
}
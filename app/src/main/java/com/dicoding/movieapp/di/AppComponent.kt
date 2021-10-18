package com.dicoding.movieapp.di

import com.dicoding.movieapp.core.core_di.CoreComponent
import com.dicoding.movieapp.ui.detail.DetailActivity
import com.dicoding.movieapp.ui.movies.MoviesFragment
import com.dicoding.movieapp.ui.tvShow.TvShowFragment
import dagger.Component

@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class, ViewModelModule::class]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): AppComponent
    }

    fun inject(fragment: MoviesFragment)
    fun inject(fragment: TvShowFragment)
    fun inject(fragment: DetailActivity)

}
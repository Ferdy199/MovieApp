package com.dicoding.movieapp.core.core_di

import android.content.Context
import com.dicoding.movieapp.core.domain.repository.MovieDataSource
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class])
interface CoreComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): CoreComponent
    }

    fun provideRepository(): MovieDataSource
}
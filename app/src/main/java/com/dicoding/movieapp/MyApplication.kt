package com.dicoding.movieapp

import android.app.Application
import com.dicoding.movieapp.core.core_di.CoreComponent
import com.dicoding.movieapp.core.core_di.DaggerCoreComponent
import com.dicoding.movieapp.di.AppComponent
import com.dicoding.movieapp.di.DaggerAppComponent

open class MyApplication : Application() {

    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(applicationContext)
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(coreComponent)
    }
}
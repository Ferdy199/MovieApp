package com.dicoding.movieapp.core.utils

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource

object EspressoIdlingResource {
    private val Resource: String? = "GLOBAL"
    private val espressoTestIdlingResource = CountingIdlingResource(Resource)

    fun increment(){
        espressoTestIdlingResource.increment()
    }

    fun decrement(){
        espressoTestIdlingResource.decrement()
    }

    fun getEspressoIdlingResource() : IdlingResource{
        return espressoTestIdlingResource
    }
}
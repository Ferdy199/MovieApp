package com.dicoding.movieapp.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.movieapp.favorite.adapter.SectionsPagerAdapter
import com.dicoding.movieapp.favorite.databinding.ActivityFavoriteBinding
import com.google.android.material.tabs.TabLayoutMediator

class FavoriteActivity : AppCompatActivity() {

    private lateinit var  binding : ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager, lifecycle)
        with(binding) {
            favViewpager.adapter = sectionsPagerAdapter
            TabLayoutMediator(favTablayout, favViewpager) { tab, position ->
                when (position) {
                    0 -> tab.text = "Movies"
                    1 -> tab.text = "Tv Show"
                }
            }.attach()
        }
    }
}
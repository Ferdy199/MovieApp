package com.dicoding.movieapp.core.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dicoding.movieapp.ui.favorite.moviefav.MovieFavoriteFragment
import com.dicoding.movieapp.ui.favorite.tvfav.TvFavorite

class SectionsPagerAdapter(fm: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fm, lifecycle) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MovieFavoriteFragment()
            1 -> TvFavorite()
            else -> Fragment()
        }
    }

    override fun getItemCount(): Int {
        return 2
    }

}
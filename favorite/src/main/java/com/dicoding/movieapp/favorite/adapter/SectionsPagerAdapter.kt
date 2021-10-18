package com.dicoding.movieapp.favorite.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dicoding.movieapp.favorite.moviefav.MovieFavFragment
import com.dicoding.movieapp.favorite.tvfav.TvShowFavFragment

class SectionsPagerAdapter(fm: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fm, lifecycle) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MovieFavFragment()
            1 -> TvShowFavFragment()
            else -> Fragment()
        }
    }

    override fun getItemCount(): Int {
        return 2
    }

}
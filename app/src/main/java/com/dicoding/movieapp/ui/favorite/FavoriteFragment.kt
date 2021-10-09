         package com.dicoding.movieapp.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dicoding.movieapp.core.adapter.SectionsPagerAdapter
import com.dicoding.movieapp.databinding.FragmentFavoriteBinding
import com.google.android.material.tabs.TabLayoutMediator

         class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val sectionsPagerAdapter = SectionsPagerAdapter(parentFragmentManager, lifecycle)
        with(binding){
            favViewpager.adapter = sectionsPagerAdapter
            TabLayoutMediator(favTablayout, favViewpager){ tab, position ->
                when(position){
                    0 -> tab.text = "Movies"
                    1 -> tab.text = "Tv Show"
                }
            }.attach()
        }

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
         package com.dicoding.movieapp.ui.favorite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.movieapp.core.adapter.MovieAdapter
import com.dicoding.movieapp.core.local.entity.MovieEntity
import com.dicoding.movieapp.core.local.entity.TvShowEntity
import com.dicoding.movieapp.core.utils.ViewModelFactory
import com.dicoding.movieapp.databinding.FragmentFavoriteBinding
import com.dicoding.movieapp.ui.detail.DetailViewModel

         class FavoriteFragment : Fragment() {

    private lateinit var favoriteViewModel: FavoriteViewModel
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
        if (activity != null){
            val movieAdapter = MovieAdapter<MovieEntity>()
            val tvShowAdapter = MovieAdapter<TvShowEntity>()
            val factory = ViewModelFactory.getInstance(requireActivity())
            favoriteViewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]
            favoriteViewModel.getMovieFavorite().observe(viewLifecycleOwner, {
                if (it != null){
                    movieAdapter.setData(it)
                    Log.d("Isi Favorite", it.toString())
                }
            })

            favoriteViewModel.getTvShowFavorite().observe(viewLifecycleOwner, {
                if (it != null){
                    tvShowAdapter.setData(it)
                }
            })

            with(binding.rvFavorite){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
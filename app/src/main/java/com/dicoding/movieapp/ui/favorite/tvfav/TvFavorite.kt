package com.dicoding.movieapp.ui.favorite.tvfav

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.movieapp.R
import com.dicoding.movieapp.core.adapter.MovieAdapter
import com.dicoding.movieapp.core.local.entity.MovieEntity
import com.dicoding.movieapp.core.local.entity.TvShowEntity
import com.dicoding.movieapp.core.utils.ViewModelFactory
import com.dicoding.movieapp.databinding.MovieFavoriteFragmentBinding
import com.dicoding.movieapp.databinding.TvFavoriteFragmentBinding

class TvFavorite : Fragment() {

    private lateinit var viewModel: TvFavoriteViewModel
    private var _binding: TvFavoriteFragmentBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = TvFavoriteFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (activity != null){
            val movieAdapter = MovieAdapter<TvShowEntity>()
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[TvFavoriteViewModel::class.java]

            viewModel.getTvShowFavorite().observe(viewLifecycleOwner, {
                if (it !== null){
                    movieAdapter.setData(it)
                }
            })

            with(binding.rvTvshowFav){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
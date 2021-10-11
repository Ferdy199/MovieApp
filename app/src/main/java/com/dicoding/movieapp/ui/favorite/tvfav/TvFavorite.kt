package com.dicoding.movieapp.ui.favorite.tvfav

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.movieapp.core.adapter.MovieAdapter
import com.dicoding.movieapp.core.domain.model.TvShow
import com.dicoding.movieapp.core.utils.ViewModelFactory
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
            val movieAdapter = MovieAdapter<TvShow>()
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
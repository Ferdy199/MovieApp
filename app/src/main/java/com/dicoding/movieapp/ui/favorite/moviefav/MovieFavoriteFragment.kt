package com.dicoding.movieapp.ui.favorite.moviefav

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.movieapp.core.adapter.MovieAdapter
import com.dicoding.movieapp.core.local.entity.MovieEntity
import com.dicoding.movieapp.core.utils.ViewModelFactory
import com.dicoding.movieapp.databinding.MovieFavoriteFragmentBinding

class MovieFavoriteFragment : Fragment() {

    private lateinit var viewModel: MovieFavoriteViewModel
    private var _binding: MovieFavoriteFragmentBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MovieFavoriteFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (activity != null){
            val movieAdapter = MovieAdapter<MovieEntity>()
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[MovieFavoriteViewModel::class.java]

            viewModel.getMovieFavorite().observe(viewLifecycleOwner, {
                if (it !== null){
                    movieAdapter.setData(it)
                }else{
                    binding.mvTxt.text = "No Movies Found"
                }
            })

            with(binding.rvMoviesFav){
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
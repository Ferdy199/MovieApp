package com.dicoding.movieapp.ui.movies

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.movieapp.core.adapter.MovieAdapter
import com.dicoding.movieapp.core.domain.model.Movie
import com.dicoding.movieapp.core.utils.Resource
import com.dicoding.movieapp.core.utils.ViewModelFactory
import com.dicoding.movieapp.databinding.FragmentMoviesBinding

class MoviesFragment : Fragment() {

    private lateinit var moviesViewModel: MoviesViewModel
    private var _binding: FragmentMoviesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoading(true)
        if (activity != null){
            val movieAdapter = MovieAdapter<Movie>()
            val factory = ViewModelFactory.getInstance(requireActivity())
            moviesViewModel = ViewModelProvider(this, factory)[MoviesViewModel::class.java]

            moviesViewModel.getAllMovies.observe(viewLifecycleOwner, {
                if(it != null){
                    when(it){
                        is Resource.Loading -> showLoading(true)
                        is Resource.Success -> {
                            movieAdapter.setData(it.data)
                            showLoading(false)
                        }
                        is Resource.Error -> {
                            binding.mvTxt.visibility = View.VISIBLE
                            showLoading(false)
                        }
                    }
                }
               Log.d("ISI DATA", it.toString())
            })

            with(binding.rvMovies){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }

    private fun showLoading(loading : Boolean){
        when(loading){
            true -> {
              binding.loadingBar.visibility = View.VISIBLE
          }
            false -> {
                binding.loadingBar.visibility = View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
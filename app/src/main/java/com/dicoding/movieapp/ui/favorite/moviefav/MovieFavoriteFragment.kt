package com.dicoding.movieapp.ui.favorite.moviefav

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.movieapp.MyApplication
import com.dicoding.movieapp.core.adapter.MovieAdapter
import com.dicoding.movieapp.core.domain.model.Movie
import com.dicoding.movieapp.core.utils.ViewModelFactory
import com.dicoding.movieapp.databinding.MovieFavoriteFragmentBinding
import com.dicoding.movieapp.ui.detail.DetailActivity
import javax.inject.Inject

class MovieFavoriteFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: MovieFavoriteViewModel by viewModels {
        factory
    }

    private var _binding: MovieFavoriteFragmentBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MovieFavoriteFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApplication).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (activity != null) {
            val movieAdapter = MovieAdapter<Movie>()
            movieAdapter.onItemClick = {
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_ID.toString(), it.id)
                intent.putExtra(DetailActivity.DETAIL_TYPE, "Movie")
                startActivity(intent)
            }
            viewModel.getMovieFavorite().observe(viewLifecycleOwner, {
                if (it !== null) {
                    movieAdapter.setData(it)
                } else {
                    binding.mvTxt.text = "No Movies Found"
                }
            })

            with(binding.rvMoviesFav) {
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
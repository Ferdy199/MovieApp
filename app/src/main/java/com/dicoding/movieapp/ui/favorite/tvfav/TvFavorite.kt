package com.dicoding.movieapp.ui.favorite.tvfav

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
import com.dicoding.movieapp.core.domain.model.TvShow
import com.dicoding.movieapp.core.utils.ViewModelFactory
import com.dicoding.movieapp.databinding.TvFavoriteFragmentBinding
import com.dicoding.movieapp.ui.detail.DetailActivity
import javax.inject.Inject

class TvFavorite : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: TvFavoriteViewModel by viewModels {
        factory
    }

    private var _binding: TvFavoriteFragmentBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = TvFavoriteFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApplication).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (activity != null) {
            val movieAdapter = MovieAdapter<TvShow>()
            movieAdapter.onItemClick = {
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_ID.toString(), it.id)
                intent.putExtra(DetailActivity.DETAIL_TYPE, "Tv_Show")
                startActivity(intent)
            }
            viewModel.getTvShowFavorite().observe(viewLifecycleOwner, {
                if (it !== null) {
                    movieAdapter.setData(it)
                }
            })

            with(binding.rvTvshowFav) {
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
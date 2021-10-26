package com.dicoding.movieapp.favorite.tvfav

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.movieapp.R
import com.dicoding.movieapp.core.adapter.MovieAdapter
import com.dicoding.movieapp.core.core_di.CoreComponent
import com.dicoding.movieapp.core.core_di.DaggerCoreComponent
import com.dicoding.movieapp.core.domain.model.TvShow
import com.dicoding.movieapp.core.utils.ViewModelFactory
import com.dicoding.movieapp.favorite.databinding.TvShowFavFragmentBinding
import com.dicoding.movieapp.favorite.di.DaggerFavComponent
import com.dicoding.movieapp.favorite.di.FavComponent
import com.dicoding.movieapp.ui.detail.DetailActivity
import javax.inject.Inject

class TvShowFavFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: TvShowFavViewModel by viewModels {
        factory
    }

    private var _binding : TvShowFavFragmentBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = TvShowFavFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val coreComponent : CoreComponent by lazy {
            DaggerCoreComponent.factory().create(context)
        }

        val favComponent: FavComponent by lazy {
            DaggerFavComponent.factory().create(coreComponent)
        }
        favComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (activity != null) {
            val movieAdapter = MovieAdapter<TvShow>()
            movieAdapter.onItemClick = {
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_ID.toString(), it.id)
                intent.putExtra(DetailActivity.DETAIL_TYPE, "Movie")
                startActivity(intent)
            }
            viewModel.getTvShowFavorite().observe(viewLifecycleOwner, {
                if (it.isNotEmpty()) {
                    movieAdapter.setData(it)
                } else {
                    binding?.mvTxt?.visibility = View.VISIBLE
                    binding?.mvTxt?.text = getString(R.string.tv_not_found)
                }
            })

            with(binding?.rvMoviesFav) {
                this?.layoutManager = LinearLayoutManager(context)
                this?.setHasFixedSize(true)
                this?.adapter = movieAdapter
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
package com.dicoding.movieapp.ui.tvShow

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.movieapp.MyApplication
import com.dicoding.movieapp.core.adapter.MovieAdapter
import com.dicoding.movieapp.core.domain.model.TvShow
import com.dicoding.movieapp.core.utils.Resource
import com.dicoding.movieapp.core.utils.ViewModelFactory
import com.dicoding.movieapp.databinding.FragmentTvshowBinding
import com.dicoding.movieapp.ui.detail.DetailActivity
import javax.inject.Inject

class TvShowFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val tvShowViewModel: TvShowViewModel by viewModels {
        factory
    }

    private var _binding: FragmentTvshowBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTvshowBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApplication).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoading(true)
        if (activity != null) {
            val tvShowAdapter = MovieAdapter<TvShow>()
            tvShowAdapter.onItemClick = {
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_ID.toString(), it.id)
                intent.putExtra(DetailActivity.DETAIL_TYPE, "Tv_Show")
                startActivity(intent)
            }
            tvShowViewModel.getAllTvShow.observe(viewLifecycleOwner, {
                if (it != null) {
                    when (it) {
                        is Resource.Success -> {
                            tvShowAdapter.setData(it.data)
                            showLoading(false)
                        }
                        is Resource.Loading -> {
                            showLoading(true)
                        }
                        is Resource.Error -> {
                            binding?.tvTxt?.visibility = View.VISIBLE
                            showLoading(false)
                        }
                    }
                }
                Log.d("ISI DATA", it.toString())
            })

            with(binding?.rvTvshow) {
                this?.layoutManager = LinearLayoutManager(context)
                this?.setHasFixedSize(true)
                this?.adapter = tvShowAdapter
            }
        }
    }

    private fun showLoading(loading: Boolean) {
        when (loading) {
            true -> {
                binding?.loadingBar?.visibility = View.VISIBLE
            }
            false -> {
                binding?.loadingBar?.visibility = View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
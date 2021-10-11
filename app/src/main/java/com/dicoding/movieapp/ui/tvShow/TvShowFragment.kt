package com.dicoding.movieapp.ui.tvShow

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.movieapp.core.adapter.MovieAdapter
import com.dicoding.movieapp.core.domain.model.TvShow
import com.dicoding.movieapp.core.local.entity.TvShowEntity
import com.dicoding.movieapp.core.utils.Resource
import com.dicoding.movieapp.core.utils.ViewModelFactory
import com.dicoding.movieapp.databinding.FragmentTvshowBinding

class TvShowFragment : Fragment() {

    private lateinit var tvShowViewModel: TvShowViewModel
    private var _binding: FragmentTvshowBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTvshowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoading(true)
        if (activity != null){
            val tvShowAdapter = MovieAdapter<TvShow>()
            val factory = ViewModelFactory.getInstance(requireActivity())
            tvShowViewModel = ViewModelProvider(this, factory).get(TvShowViewModel::class.java)

            tvShowViewModel.getAllTvShow.observe(viewLifecycleOwner, {
                if (it != null){
                    when(it){
                        is Resource.Success -> {
                            tvShowAdapter.setData(it.data)
                            showLoading(false)
                        }
                        is Resource.Loading -> {
                            showLoading(true)
                        }
                        is Resource.Error -> {
                            binding.tvTxt.visibility = View.VISIBLE
                            showLoading(false)
                        }
                    }
                }
                Log.d("ISI DATA", it.toString())
            })

            with(binding.rvTvshow){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowAdapter
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
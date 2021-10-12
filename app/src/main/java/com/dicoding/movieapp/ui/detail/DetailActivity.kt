package com.dicoding.movieapp.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dicoding.movieapp.MyApplication
import com.dicoding.movieapp.R
import com.dicoding.movieapp.core.domain.model.Movie
import com.dicoding.movieapp.core.domain.model.TvShow
import com.dicoding.movieapp.core.utils.Resource
import com.dicoding.movieapp.core.utils.ViewModelFactory
import com.dicoding.movieapp.databinding.ActivityDetailBinding
import javax.inject.Inject

class DetailActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelFactory

    private lateinit var binding: ActivityDetailBinding

    private val detailViewModel: DetailViewModel by viewModels {
        factory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showLoading(true)

        //get Id
        val movieId = intent.getIntExtra(EXTRA_ID.toString(), 0)

        //get Type
        val detailType = intent.getStringExtra(DETAIL_TYPE)

        //set Id
        detailViewModel.setMovieId(movieId)

        //set halaman detail sesuai type
        when (detailType) {
            "Movie" -> {
                setDetailMovieData()
            }
            "Tv_Show" -> {
                setDetailTvShowData()
            }
        }

    }

    private fun setDetailMovie(movieDetail: Resource<Movie>) {
        var statusFavorite = movieDetail.data?.favorite
        with(binding) {
            detailPosterTxt.text = movieDetail.data?.original_title
            detailMoviesName.text = movieDetail.data?.original_title
            detailReleasedYear.text = movieDetail.data?.release_date
            detailRating.text = movieDetail.data?.vote_average.toString() + " /10"
            detailDescription.text = movieDetail.data?.overview

            setFavorite(statusFavorite)
            detailFavoriteButton.setOnClickListener {
                statusFavorite = !statusFavorite!!
                detailViewModel.setMovieFavorite(movieDetail.data!!, statusFavorite!!)
                setFavorite(statusFavorite)
                when (statusFavorite) {
                    true -> Toast.makeText(
                        this@DetailActivity,
                        "${movieDetail.data.original_title} Berhasil Disimpan Didalam Favorite",
                        Toast.LENGTH_SHORT
                    ).show()
                    false -> Toast.makeText(
                        this@DetailActivity,
                        "${movieDetail.data.original_title} Berhasil Dihapus ",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            Glide.with(this@DetailActivity)
                .load(movieDetail.data?.imageBaseUrl + movieDetail.data?.backdrop_path)
                .centerCrop()
                .into(detailPosterImg)

            Glide.with(this@DetailActivity)
                .load(movieDetail.data?.imageBaseUrl + movieDetail.data?.poster_path)
                .centerCrop()
                .into(detailImg)
        }
    }

    private fun setDetailTvSHow(tvShowDetail: Resource<TvShow>) {
        var statusFavorite = tvShowDetail.data?.favorite
        with(binding) {
            detailPosterTxt.text = tvShowDetail.data?.original_name
            detailMoviesName.text = tvShowDetail.data?.original_name
            detailReleasedYear.text = tvShowDetail.data?.first_air_date
            detailRating.text = tvShowDetail.data?.vote_average.toString() + " /10"
            detailDescription.text = tvShowDetail.data?.overview

            setFavorite(statusFavorite)
            detailFavoriteButton.setOnClickListener {
                statusFavorite = !statusFavorite!!
                detailViewModel.setTvShowFavorite(tvShowDetail.data!!, statusFavorite!!)
                setFavorite(statusFavorite)
                when (statusFavorite) {
                    true -> Toast.makeText(
                        this@DetailActivity,
                        "${tvShowDetail.data.original_name} Berhasil Disimpan Didalam Favorite",
                        Toast.LENGTH_SHORT
                    ).show()
                    false -> Toast.makeText(
                        this@DetailActivity,
                        "${tvShowDetail.data.original_name} Berhasil Dihapus ",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            Glide.with(this@DetailActivity)
                .load(tvShowDetail.data?.imageBaseUrl + tvShowDetail.data?.backdrop_path)
                .centerCrop()
                .into(detailPosterImg)

            Glide.with(this@DetailActivity)
                .load(tvShowDetail.data?.imageBaseUrl + tvShowDetail.data?.poster_path)
                .centerCrop()
                .into(detailImg)
        }
    }


    private fun setDetailMovieData() {
        detailViewModel.getDetailMovie().observe(this, {
            if (it != null) {
                setDetailMovie(it)
                showLoading(false)
            } else {
                Toast.makeText(this, "DATA KOSONG", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun setDetailTvShowData() {
        detailViewModel.getDetailTvShow().observe(this, {
            if (it != null) {
                setDetailTvSHow(it)
                showLoading(false)
            } else {
                Toast.makeText(this, "DATA KOSONG", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun showLoading(loading: Boolean) {
        when (loading) {
            true -> {
                binding.loadingBar.visibility = View.VISIBLE
            }
            false -> {
                binding.loadingBar.visibility = View.GONE
            }
        }
    }

    private fun setFavorite(favorite: Boolean?) {
        if (favorite == true) {
            binding.detailFavoriteButton.setImageResource(R.drawable.ic_menu_favorite)
        } else {
            binding.detailFavoriteButton.setImageResource(R.drawable.ic_favorite_border)
        }
    }

    companion object {
        const val EXTRA_ID = 0
        const val DETAIL_TYPE = "type"
    }

}
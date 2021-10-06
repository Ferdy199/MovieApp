package com.dicoding.movieapp.core.source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.movieapp.core.network.config.ApiService
import com.dicoding.movieapp.core.network.response.*
import com.dicoding.movieapp.core.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(private val apiService: ApiService){

    fun getAllMovie() : LiveData<ApiResponse<List<DataMovieResponse>>>{
        EspressoIdlingResource.increment()
        val resultMovie = MutableLiveData<ApiResponse<List<DataMovieResponse>>>()
        apiService.getMovie().enqueue(object: Callback<ResponseMovie>{
            override fun onResponse(
                call: Call<ResponseMovie>,
                response: Response<ResponseMovie>
            ) {
                if (response.isSuccessful){
                    val movieArray = response.body()?.results
                    resultMovie.value = if (movieArray != null) ApiResponse.Success(movieArray) else ApiResponse.Empty
                    EspressoIdlingResource.decrement()
                }
            }
            override fun onFailure(call: Call<ResponseMovie>, t: Throwable) {
                resultMovie.value = ApiResponse.Error(t.message.toString())
                Log.e("Error", t.message.toString())
            }
        })
        return resultMovie
    }

    fun getAllTvShow() : LiveData<ApiResponse<List<DataTvShowResponse>>>{
        EspressoIdlingResource.increment()
        val resultTvShow = MutableLiveData<ApiResponse<List<DataTvShowResponse>>>()
        apiService.getTvShow().enqueue(object : Callback<ResponseTvShow >{
            override fun onResponse(call: Call<ResponseTvShow>, response: Response<ResponseTvShow>) {
                if (response.isSuccessful){
                    val tvShowArray = response.body()?.results
                    resultTvShow.value = if (tvShowArray != null) ApiResponse.Success(tvShowArray) else ApiResponse.Empty
                    EspressoIdlingResource.decrement()
                }
            }
            override fun onFailure(call: Call<ResponseTvShow>, t: Throwable) {
                resultTvShow.value = ApiResponse.Error(t.message.toString())
                Log.e("Error", t.message.toString())
            }
        })
        return resultTvShow
    }

    fun getDetailMovie(movie_id : Int) : LiveData<ApiResponse<DataMovieResponse>>{
        EspressoIdlingResource.increment()
        val resultDetailMovie = MutableLiveData<ApiResponse<DataMovieResponse>>()
        apiService.getMovieDetail(movie_id).enqueue(object : Callback<DataMovieResponse>{
            override fun onResponse(
                call: Call<DataMovieResponse>,
                response: Response<DataMovieResponse>
            ) {
                if (response.isSuccessful){
                    val dataArray = response.body()
                    resultDetailMovie.value = if (dataArray != null) ApiResponse.Success(dataArray) else ApiResponse.Empty
                    EspressoIdlingResource.decrement()
                }
            }
            override fun onFailure(call: Call<DataMovieResponse>, t: Throwable) {
                Log.e("Error", t.message.toString())
            }
        })
        return resultDetailMovie
    }

    fun getDetailTvShow(tvShow_id : Int) : LiveData<ApiResponse<DataTvShowResponse>>{
        EspressoIdlingResource.increment()
        val resultDetailTvShow = MutableLiveData<ApiResponse<DataTvShowResponse>>()
        apiService.getTvShowDetail(tvShow_id).enqueue(object : Callback<DataTvShowResponse>{
            override fun onResponse(
                call: Call<DataTvShowResponse>,
                response: Response<DataTvShowResponse>
            ) {
                if (response.isSuccessful){
                    val dataArray = response.body()
                    resultDetailTvShow.value = if (dataArray != null) ApiResponse.Success(dataArray) else ApiResponse.Empty
                    EspressoIdlingResource.decrement()
                }
            }
            override fun onFailure(call: Call<DataTvShowResponse>, t: Throwable) {
                Log.e("Error", t.message.toString())
            }
        })
        return resultDetailTvShow
    }

    companion object{
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(service: ApiService) : RemoteDataSource =
            instance ?: synchronized(this){
                instance ?: RemoteDataSource(service).apply {
                    instance = this
                }
            }
    }
}
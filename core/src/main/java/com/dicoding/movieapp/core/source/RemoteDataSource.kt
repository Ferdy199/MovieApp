package com.dicoding.movieapp.core.source

import android.util.Log
import com.dicoding.movieapp.core.network.config.ApiService
import com.dicoding.movieapp.core.network.response.ApiResponse
import com.dicoding.movieapp.core.network.response.DataMovieResponse
import com.dicoding.movieapp.core.network.response.DataTvShowResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getAllMovie(): Flow<ApiResponse<List<DataMovieResponse>>> {
        return flow {
            try {
                val response = apiService.getMovie()
                val movieArray = response.results
                if (movieArray!!.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getAllTvShow(): Flow<ApiResponse<List<DataTvShowResponse>>> {
        return flow {
            try {
                val response = apiService.getTvShow()
                val tvShowArray = response.results
                if (tvShowArray!!.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetailMovie(movie_id: Int): Flow<ApiResponse<DataMovieResponse>> {
        return flow {
            try {
                val response = apiService.getMovieDetail(movie_id)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }
    }

    suspend fun getDetailTvShow(tvShow_id: Int): Flow<ApiResponse<DataTvShowResponse>> {
        return flow {
            try {
                val response = apiService.getTvShowDetail(tvShow_id)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }
    }
}
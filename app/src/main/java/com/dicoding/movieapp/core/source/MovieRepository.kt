package com.dicoding.movieapp.core.source

import androidx.lifecycle.LiveData
import com.dicoding.movieapp.core.local.entity.MovieEntity
import com.dicoding.movieapp.core.local.entity.TvShowEntity
import com.dicoding.movieapp.core.network.response.ApiResponse
import com.dicoding.movieapp.core.network.response.DataMovieResponse
import com.dicoding.movieapp.core.network.response.DataTvShowResponse
import com.dicoding.movieapp.core.utils.AppExecutors
import com.dicoding.movieapp.core.utils.DataMapper
import com.dicoding.movieapp.core.utils.Resource

class MovieRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : MovieDataSource {
    override fun getAllMovies(): LiveData<Resource<List<MovieEntity>>> =
        object : NetworkBoundResource<List<MovieEntity>, List<DataMovieResponse>>(appExecutors){
            override fun loadFromDB(): LiveData<List<MovieEntity>> {
                return localDataSource.getAllMovies()
            }

            override fun shouldFetch(data: List<MovieEntity>?): Boolean {
               return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<DataMovieResponse>>> {
                return remoteDataSource.getAllMovie()
            }

            override fun saveCallResult(data: List<DataMovieResponse>) {
                val movieList = DataMapper.mapMovieResponsesToEntities(data)
                localDataSource.insertMovies(movieList)
            }
        }.asLiveData()


    override fun getAllTvShow(): LiveData<Resource<List<TvShowEntity>>> =
         object : NetworkBoundResource<List<TvShowEntity>, List<DataTvShowResponse>>(appExecutors){
            override fun loadFromDB(): LiveData<List<TvShowEntity>> {
                return localDataSource.getAllTvShow()
            }

            override fun shouldFetch(data: List<TvShowEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<DataTvShowResponse>>> {
                return remoteDataSource.getAllTvShow()
            }

            override fun saveCallResult(data: List<DataTvShowResponse>) {
                val tvShowList = DataMapper.mapTvShowResponsesToEntities(data)
                localDataSource.insertTvShow(tvShowList)
            }
        }.asLiveData()

    override fun getDetailMovie(movie_id : Int): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, DataMovieResponse>(appExecutors){
            override fun loadFromDB(): LiveData<MovieEntity> {
                return localDataSource.getDetailMovie(movie_id)
            }

            override fun shouldFetch(data: MovieEntity?): Boolean {
                return data == null
            }

            override fun createCall(): LiveData<ApiResponse<DataMovieResponse>> {
                return remoteDataSource.getDetailMovie(movie_id)
            }

            override fun saveCallResult(data: DataMovieResponse) {
                val detailList = DataMapper.mapDetailMovieResponsesToEntities(data)
                localDataSource.insertMovies(detailList)
            }
        }.asLiveData()
    }

    override fun getDetailTvShow(tvShow_id: Int): LiveData<Resource<TvShowEntity>> {
        return object : NetworkBoundResource<TvShowEntity, DataTvShowResponse>(appExecutors){
            override fun loadFromDB(): LiveData<TvShowEntity> {
               return localDataSource.getDetailTvShow(tvShow_id)
            }

            override fun shouldFetch(data: TvShowEntity?): Boolean {
                return data == null
            }

            override fun createCall(): LiveData<ApiResponse<DataTvShowResponse>> {
                return remoteDataSource.getDetailTvShow(tvShow_id)
            }

            override fun saveCallResult(data: DataTvShowResponse) {
                val detailList = DataMapper.mapDetailTvShowReponsesToEntities(data)
                localDataSource.insertTvShow(detailList)
            }
        }.asLiveData()
    }

    override fun getMovieFavorite(): LiveData<List<MovieEntity>> {
        return localDataSource.getMovieFavorite()
    }

    override fun getTvShowFavorite(): LiveData<List<TvShowEntity>> {
        return localDataSource.getTvShowFavorite()
    }

    override fun setMovieFavorite(movie: MovieEntity, state: Boolean) {
       return appExecutors.diskIO().execute {
           localDataSource.setFavoriteMovies(movie, state)
       }
    }

    override fun setTvShowFavorite(tvShow: TvShowEntity, state: Boolean) {
        return appExecutors.diskIO().execute{
            localDataSource.setFavoriteTvShow(tvShow, state)
        }

    }

    companion object{
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(remoteData: RemoteDataSource, localData : LocalDataSource, appExecutors: AppExecutors) : MovieRepository =
            instance ?: synchronized(this){
                instance ?: MovieRepository(remoteData, localData, appExecutors).apply {
                    instance = this
                }
            }
    }
}
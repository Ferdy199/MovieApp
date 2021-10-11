package com.dicoding.movieapp.core.domain.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.dicoding.movieapp.core.domain.model.Movie
import com.dicoding.movieapp.core.domain.model.TvShow
import com.dicoding.movieapp.core.network.response.ApiResponse
import com.dicoding.movieapp.core.network.response.DataMovieResponse
import com.dicoding.movieapp.core.network.response.DataTvShowResponse
import com.dicoding.movieapp.core.source.LocalDataSource
import com.dicoding.movieapp.core.source.NetworkBoundResource
import com.dicoding.movieapp.core.source.RemoteDataSource
import com.dicoding.movieapp.core.utils.AppExecutors
import com.dicoding.movieapp.core.utils.DataMapper
import com.dicoding.movieapp.core.utils.Resource

class MovieRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : MovieDataSource {
    override fun getAllMovies(): LiveData<Resource<List<Movie>>> {
        return object : NetworkBoundResource<List<Movie>, List<DataMovieResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<Movie>> {
                return Transformations.map(localDataSource.getAllMovies()) {
                    DataMapper.mapMovieEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
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
    }


    override fun getAllTvShow(): LiveData<Resource<List<TvShow>>> {
        return object : NetworkBoundResource<List<TvShow>, List<DataTvShowResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<TvShow>> {
                return Transformations.map(localDataSource.getAllTvShow()) {
                    DataMapper.mapTvShowEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<TvShow>?): Boolean {
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
    }

    override fun getDetailMovie(movie_id : Int): LiveData<Resource<Movie>> {
        return object : NetworkBoundResource<Movie, DataMovieResponse>(appExecutors){
            override fun loadFromDB(): LiveData<Movie> {
                return Transformations.map(localDataSource.getDetailMovie(movie_id)){
                    DataMapper.mapDetailMovieEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: Movie?): Boolean {
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

    override fun getDetailTvShow(tvShow_id: Int): LiveData<Resource<TvShow>> {
        return object : NetworkBoundResource<TvShow, DataTvShowResponse>(appExecutors){
            override fun loadFromDB(): LiveData<TvShow> {
               return Transformations.map(localDataSource.getDetailTvShow(tvShow_id)){
                   DataMapper.mapDetailTvShowEntitiesToDomain(it)
               }
            }

            override fun shouldFetch(data: TvShow?): Boolean {
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

    override fun getMovieFavorite(): LiveData<List<Movie>> {
        return Transformations.map(localDataSource.getMovieFavorite()){
            DataMapper.mapMovieEntitiesToDomain(it)
        }
    }

    override fun getTvShowFavorite(): LiveData<List<TvShow>> {
        return Transformations.map(localDataSource.getTvShowFavorite()){
            DataMapper.mapTvShowEntitiesToDomain(it)
        }
    }

    override fun setMovieFavorite(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapMovieDomainToEntity(movie)
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteMovies(movieEntity, state)
        }
    }

    override fun setTvShowFavorite(tvShow: TvShow, state: Boolean) {
        val tvShowEntity = DataMapper.mapTvShowDomainToEntity(tvShow)
        appExecutors.diskIO().execute{
            localDataSource.setFavoriteTvShow(tvShowEntity, state)
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
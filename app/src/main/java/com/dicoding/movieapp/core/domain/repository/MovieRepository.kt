package com.dicoding.movieapp.core.domain.repository

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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : MovieDataSource {
    override fun getAllMovies(): Flow<Resource<List<Movie>>> {
        return object : NetworkBoundResource<List<Movie>, List<DataMovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovies().map {
                    DataMapper.mapMovieEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<DataMovieResponse>>> {
                return remoteDataSource.getAllMovie()
            }

            override suspend fun saveCallResult(data: List<DataMovieResponse>) {
                val movieList = DataMapper.mapMovieResponsesToEntities(data)
                localDataSource.insertMovies(movieList)
            }
        }.asFlow()
    }


    override fun getAllTvShow(): Flow<Resource<List<TvShow>>> {
        return object : NetworkBoundResource<List<TvShow>, List<DataTvShowResponse>>() {
            override fun loadFromDB(): Flow<List<TvShow>> {
                return localDataSource.getAllTvShow().map {
                    DataMapper.mapTvShowEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<TvShow>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<DataTvShowResponse>>> {
                return remoteDataSource.getAllTvShow()
            }

            override suspend fun saveCallResult(data: List<DataTvShowResponse>) {
                val tvShowList = DataMapper.mapTvShowResponsesToEntities(data)
                localDataSource.insertTvShow(tvShowList)
            }
        }.asFlow()
    }

    override fun getDetailMovie(movie_id : Int): Flow<Resource<Movie>> {
        return object : NetworkBoundResource<Movie, DataMovieResponse>(){
            override fun loadFromDB(): Flow<Movie> {
                return localDataSource.getDetailMovie(movie_id).map{
                    DataMapper.mapDetailMovieEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: Movie?): Boolean {
                return data == null
            }

            override suspend fun createCall(): Flow<ApiResponse<DataMovieResponse>> {
                return remoteDataSource.getDetailMovie(movie_id)
            }

            override suspend fun saveCallResult(data: DataMovieResponse) {
                val detailList = DataMapper.mapDetailMovieResponsesToEntities(data)
                localDataSource.insertMovies(detailList)
            }
        }.asFlow()
    }

    override fun getDetailTvShow(tvShow_id: Int): Flow<Resource<TvShow>> {
        return object : NetworkBoundResource<TvShow, DataTvShowResponse>(){
            override fun loadFromDB(): Flow<TvShow> {
               return localDataSource.getDetailTvShow(tvShow_id).map{
                   DataMapper.mapDetailTvShowEntitiesToDomain(it)
               }
            }

            override fun shouldFetch(data: TvShow?): Boolean {
                return data == null
            }

            override suspend fun createCall(): Flow<ApiResponse<DataTvShowResponse>> {
                return remoteDataSource.getDetailTvShow(tvShow_id)
            }

            override suspend fun saveCallResult(data: DataTvShowResponse) {
                val detailList = DataMapper.mapDetailTvShowReponsesToEntities(data)
                localDataSource.insertTvShow(detailList)
            }
        }.asFlow()
    }

    override fun getMovieFavorite(): Flow<List<Movie>> {
        return localDataSource.getMovieFavorite().map{
            DataMapper.mapMovieEntitiesToDomain(it)
        }
    }

    override fun getTvShowFavorite(): Flow<List<TvShow>> {
        return localDataSource.getTvShowFavorite().map{
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
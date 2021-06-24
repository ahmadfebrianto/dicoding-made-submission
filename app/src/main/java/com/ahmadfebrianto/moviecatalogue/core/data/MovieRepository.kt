package com.ahmadfebrianto.moviecatalogue.core.data

import com.ahmadfebrianto.moviecatalogue.core.data.source.local.LocalDataSource
import com.ahmadfebrianto.moviecatalogue.core.data.source.remote.RemoteDataSource
import com.ahmadfebrianto.moviecatalogue.core.data.source.remote.api.ApiResponse
import com.ahmadfebrianto.moviecatalogue.core.data.source.remote.response.MovieResponse
import com.ahmadfebrianto.moviecatalogue.core.domain.model.Movie
import com.ahmadfebrianto.moviecatalogue.core.domain.repository.DomainRepository
import com.ahmadfebrianto.moviecatalogue.core.utils.AppExecutors
import com.ahmadfebrianto.moviecatalogue.core.utils.DataMapper
import com.ahmadfebrianto.moviecatalogue.core.data.source.Resource
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MovieRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : DomainRepository {

    companion object {
        @Volatile
        private var instance: MovieRepository? = null
        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): MovieRepository {
            return instance ?: synchronized(this) {
                instance?: MovieRepository(remoteData, localData, appExecutors)
            }
        }
    }

    override fun getAllMovies(): Flowable<Resource<List<Movie>>> {
        return object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flowable<List<Movie>> {
                return localDataSource.getAllMovies().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): Flowable<ApiResponse<List<MovieResponse>>> {
                return remoteDataSource.getAllMovies()
            }

            override fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovies(movieList)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }
        }.asFlowable()
    }

    override fun getFavoriteMovies(): Flowable<List<Movie>> {
        return localDataSource.getFavoriteMovies().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteMovie(movieEntity, state)
        }
    }
}

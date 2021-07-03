package com.ahmadfebrianto.moviecatalogue.core.data

import com.ahmadfebrianto.moviecatalogue.core.data.source.Resource
import com.ahmadfebrianto.moviecatalogue.core.data.source.local.LocalDataSource
import com.ahmadfebrianto.moviecatalogue.core.data.source.remote.RemoteDataSource
import com.ahmadfebrianto.moviecatalogue.core.data.source.remote.api.ApiResponse
import com.ahmadfebrianto.moviecatalogue.core.data.source.remote.response.MovieResponse
import com.ahmadfebrianto.moviecatalogue.core.domain.model.Movie
import com.ahmadfebrianto.moviecatalogue.core.domain.repository.DomainRepository
import com.ahmadfebrianto.moviecatalogue.core.utils.AppExecutors
import com.ahmadfebrianto.moviecatalogue.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : DomainRepository {

    override fun getAllMovies(): Flow<Resource<List<Movie>>> {
        return object :
            com.ahmadfebrianto.moviecatalogue.core.data.NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovies().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> {
                return remoteDataSource.getAllMovies()
            }

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovies(movieList)
            }
        }.asFlow()
    }

    override fun getFavoriteMovies(): Flow<List<Movie>> {
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

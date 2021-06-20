package com.ahmadfebrianto.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ahmadfebrianto.moviecatalogue.data.source.local.LocalDataSource
import com.ahmadfebrianto.moviecatalogue.data.source.local.entity.MovieEntity
import com.ahmadfebrianto.moviecatalogue.data.source.remote.ApiResponse
import com.ahmadfebrianto.moviecatalogue.data.source.remote.RemoteDataSource
import com.ahmadfebrianto.moviecatalogue.data.source.remote.response.MovieResponse
import com.ahmadfebrianto.moviecatalogue.utils.AppExecutors
import com.ahmadfebrianto.moviecatalogue.vo.Resource

class CatalogRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : CatalogDataSource {

    companion object {

        @Volatile
        private var instance: CatalogRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): CatalogRepository {
            return instance ?: synchronized(this) {
                CatalogRepository(remoteData, localData, appExecutors).apply {
                    instance = this
                }
            }
        }
    }


    /*MOVIES*/

    override fun getAllMovies(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object :
            NetworkBoundResource<PagedList<MovieEntity>, List<MovieResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> {
                return remoteDataSource.getAllMovies()
            }

            override fun saveCallResult(data: List<MovieResponse>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in data) {
                    val movie = MovieEntity(
                        response.id,
                        response.backdropPath,
                        response.posterPath,
                        response.title,
                        response.description,
                        response.language,
                        response.rating,
                        response.releaseDate
                    )
                    movieList.add(movie)
                }
                localDataSource.insertMovies(movieList)
            }
        }.asLiveData()
    }

    override fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteMovies(), config).build()
    }

    override fun getMovieById(movieId: String): LiveData<MovieEntity> {
        return localDataSource.getMovieById(movieId)
    }

    override fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        appExecutors.diskIO()
            .execute { localDataSource.setFavoriteMovie(movie, newState) }
    }
}

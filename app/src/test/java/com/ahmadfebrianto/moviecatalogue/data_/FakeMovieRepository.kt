package com.ahmadfebrianto.moviecatalogue.data_

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ahmadfebrianto.moviecatalogue.core.data.MovieDataSource
import com.ahmadfebrianto.moviecatalogue.core.data.NetworkBoundResource
import com.ahmadfebrianto.moviecatalogue.core.data.source.local.LocalDataSource
import com.ahmadfebrianto.moviecatalogue.core.data.source.local.entity.MovieEntity
import com.ahmadfebrianto.moviecatalogue.core.data.source.remote.ApiResponse
import com.ahmadfebrianto.moviecatalogue.core.data.source.remote.RemoteDataSource
import com.ahmadfebrianto.moviecatalogue.core.utils.AppExecutors
import com.ahmadfebrianto.moviecatalogue.core.vo.Resource

class FakeMovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : MovieDataSource {

    /*MOVIES*/

    override fun getAllMovies(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object :
            NetworkBoundResource<PagedList<MovieEntity>, List<CatalogResponse>>(appExecutors) {
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

            override fun createCall(): LiveData<ApiResponse<List<CatalogResponse>>> {
                return remoteDataSource.getAllMovies()
            }

            override fun saveCallResult(data: List<CatalogResponse>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in data) {
                    val movie = MovieEntity(
                        response.id,
                        response.poster,
                        response.title,
                        response.description,
                        response.rating,
                        response.releaseYear,
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

    override fun getMovieById(movieId: String): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, CatalogResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieEntity> {
                return localDataSource.getMovieById(movieId)
            }

            override fun shouldFetch(data: MovieEntity?): Boolean {
                return data == null
            }

            override fun createCall(): LiveData<ApiResponse<CatalogResponse>> {
                return remoteDataSource.getMovieById(movieId)
            }

            override fun saveCallResult(data: CatalogResponse) {
                val movie = MovieEntity(
                    data.id,
                    data.poster,
                    data.title,
                    data.description,
                    data.rating,
                    data.releaseYear
                )
                localDataSource.insertMovie(movie)
            }
        }.asLiveData()
    }

    override fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movie, newState) }
    }


    /*TV SHOWS*/

    override fun getAllTvShows(): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object :
            NetworkBoundResource<PagedList<TvShowEntity>, List<CatalogResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllTvShows(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<CatalogResponse>>> {
                return remoteDataSource.getAllTvShows()
            }

            override fun saveCallResult(data: List<CatalogResponse>) {
                val tvShowList = ArrayList<TvShowEntity>()
                for (response in data) {
                    val tvShow = TvShowEntity(
                        response.id,
                        response.poster,
                        response.title,
                        response.description,
                        response.rating,
                        response.releaseYear
                    )
                    tvShowList.add(tvShow)
                }
                localDataSource.insertTvShows(tvShowList)
            }
        }.asLiveData()
    }

    override fun getFavoriteTvShows(): LiveData<PagedList<TvShowEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteTvShows(), config).build()
    }

    override fun getTvShowById(tvShowId: String): LiveData<Resource<TvShowEntity>> {
        return object : NetworkBoundResource<TvShowEntity, CatalogResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<TvShowEntity> {
                return localDataSource.getTvShowById(tvShowId)
            }

            override fun shouldFetch(data: TvShowEntity?): Boolean {
                return data == null
            }

            override fun createCall(): LiveData<ApiResponse<CatalogResponse>> {
                return remoteDataSource.getTvShowById(tvShowId)
            }

            override fun saveCallResult(data: CatalogResponse) {
                val tvShow = TvShowEntity(
                    data.id,
                    data.poster,
                    data.title,
                    data.description,
                    data.rating,
                    data.releaseYear
                )
                localDataSource.insertTvShow(tvShow)
            }
        }.asLiveData()
    }

    override fun setFavoriteTvShow(tvShow: TvShowEntity, newState: Boolean) {
        appExecutors.diskIO().execute { localDataSource.setFavoriteTvShow(tvShow, newState) }
    }
}
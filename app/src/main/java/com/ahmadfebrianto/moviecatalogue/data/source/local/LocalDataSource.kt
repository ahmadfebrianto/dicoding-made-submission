package com.ahmadfebrianto.moviecatalogue.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.ahmadfebrianto.moviecatalogue.data.source.local.entity.MovieEntity
import com.ahmadfebrianto.moviecatalogue.data.source.local.entity.TvShowEntity
import com.ahmadfebrianto.moviecatalogue.data.source.local.room.CatalogDao

class LocalDataSource private constructor(private val catalogDao: CatalogDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(mCatalogDao: CatalogDao): LocalDataSource {
            return INSTANCE ?: LocalDataSource(mCatalogDao).apply {
                INSTANCE = this
            }
        }
    }

    /*MOVIES*/

    fun getAllMovies(): DataSource.Factory<Int, MovieEntity> {
        return catalogDao.getAllMovies()
    }

    fun getFavoriteMovies(): DataSource.Factory<Int, MovieEntity> {
        return catalogDao.getFavoriteMovies()
    }

    fun getMovieById(movieId: String): LiveData<MovieEntity> {
        return catalogDao.getMovieById(movieId)
    }

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        catalogDao.updateMovie(movie)
    }

    fun insertMovies(movies: List<MovieEntity>) {
        catalogDao.insertMovies(movies)
    }

    fun insertMovie(movie: MovieEntity) {
        catalogDao.insertMovie(movie)
    }


    /*TV SHOWS*/

    fun getAllTvShows(): DataSource.Factory<Int, TvShowEntity> {
        return catalogDao.getAllTvShows()
    }

    fun getFavoriteTvShows(): DataSource.Factory<Int, TvShowEntity> {
        return catalogDao.getFavoriteTvShows()
    }

    fun getTvShowById(tvShowId: String): LiveData<TvShowEntity> {
        return catalogDao.getTvShowById(tvShowId)
    }

    fun setFavoriteTvShow(tvShow: TvShowEntity, newState: Boolean) {
        tvShow.isFavorite = newState
        catalogDao.updateTvShow(tvShow)
    }

    fun insertTvShows(tvShows: List<TvShowEntity>) {
        catalogDao.insertTvShows(tvShows)
    }

    fun insertTvShow(tvShow: TvShowEntity) {
        catalogDao.insertTvShow(tvShow)
    }


}
package com.ahmadfebrianto.moviecatalogue.core.data.source.local

import com.ahmadfebrianto.moviecatalogue.core.data.source.local.entity.MovieEntity
import com.ahmadfebrianto.moviecatalogue.core.data.source.local.room.MovieDao
import io.reactivex.Completable
import io.reactivex.Flowable

class LocalDataSource private constructor(private val movieDao: MovieDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(dao: MovieDao): LocalDataSource {
            return INSTANCE?: synchronized(this) {
                INSTANCE?: LocalDataSource(dao)
            }
        }
    }


    fun getAllMovies(): Flowable<List<MovieEntity>> {
        return movieDao.getAllMovies()
    }

    fun getFavoriteMovies(): Flowable<List<MovieEntity>> {
        return movieDao.getFavoriteMovies()
    }

    fun setFavoriteMovie(movie: MovieEntity, state: Boolean) {
        movie.isFavorite = state
        movieDao.updateMovie(movie)
    }

    fun insertMovies(movies: List<MovieEntity>): Completable {
        return movieDao.insertMovies(movies)
    }
}
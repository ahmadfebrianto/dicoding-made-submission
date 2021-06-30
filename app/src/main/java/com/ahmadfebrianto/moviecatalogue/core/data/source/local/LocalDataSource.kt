package com.ahmadfebrianto.moviecatalogue.core.data.source.local

import com.ahmadfebrianto.moviecatalogue.core.data.source.local.entity.MovieEntity
import com.ahmadfebrianto.moviecatalogue.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource private constructor(private val movieDao: MovieDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(dao: MovieDao): LocalDataSource {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: LocalDataSource(dao)
            }
        }
    }


    fun getAllMovies(): Flow<List<MovieEntity>> {
        return movieDao.getAllMovies()
    }

    fun getFavoriteMovies(): Flow<List<MovieEntity>> {
        return movieDao.getFavoriteMovies()
    }

    fun setFavoriteMovie(movie: MovieEntity, state: Boolean) {
        movie.isFavorite = state
        movieDao.updateMovie(movie)
    }

    suspend fun insertMovies(movies: List<MovieEntity>) {
        movieDao.insertMovies(movies)
    }
}
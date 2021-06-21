package com.ahmadfebrianto.moviecatalogue.core.data.source.local

import androidx.lifecycle.LiveData
import com.ahmadfebrianto.moviecatalogue.core.data.source.local.entity.MovieEntity
import com.ahmadfebrianto.moviecatalogue.core.data.source.local.room.MovieDao

class LocalDataSource private constructor(private val movieDao: MovieDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(mMovieDao: MovieDao): LocalDataSource {
            return INSTANCE ?: LocalDataSource(mMovieDao).apply {
                INSTANCE = this
            }
        }
    }

    /*MOVIES*/

    fun getAllMovies(): LiveData<List<MovieEntity>> {
        return movieDao.getAllMovies()
    }

    fun getFavoriteMovies(): LiveData<List<MovieEntity>> {
        return movieDao.getFavoriteMovies()
    }

//    fun getMovieById(movieId: String): LiveData<MovieEntity> {
//        return catalogDao.getMovieById(movieId)
//    }

    fun setFavoriteMovie(movie: MovieEntity, state: Boolean) {
        movie.isFavorite = state
        movieDao.updateMovie(movie)
    }

    fun insertMovies(movies: List<MovieEntity>) {
        movieDao.insertMovies(movies)
    }

//    fun insertMovie(movie: MovieEntity) {
//        catalogDao.insertMovie(movie)
//    }
}
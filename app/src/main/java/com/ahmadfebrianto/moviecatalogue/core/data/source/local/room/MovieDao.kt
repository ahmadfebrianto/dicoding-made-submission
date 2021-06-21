package com.ahmadfebrianto.moviecatalogue.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ahmadfebrianto.moviecatalogue.core.data.source.local.entity.MovieEntity

@Dao
interface MovieDao {

    /*MOVIES*/

    @Query("SELECT * FROM ${MovieDatabase.MOVIE_TABLE_NAME}")
    fun getAllMovies(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM ${MovieDatabase.MOVIE_TABLE_NAME} WHERE isFavorite = 1")
    fun getFavoriteMovies(): LiveData<List<MovieEntity>>

    @Transaction
    @Query("SELECT * FROM ${MovieDatabase.MOVIE_TABLE_NAME} WHERE id = :movieId")
    fun getMovieById(movieId: String): LiveData<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: MovieEntity)

    @Update
    fun updateMovie(movie: MovieEntity)

}
package com.ahmadfebrianto.moviecatalogue.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.ahmadfebrianto.moviecatalogue.data.source.local.entity.MovieEntity

@Dao
interface CatalogDao {

    /*MOVIES*/

    @Query("SELECT * FROM ${CatalogDatabase.MOVIE_TABLE_NAME}")
    fun getAllMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM ${CatalogDatabase.MOVIE_TABLE_NAME} WHERE isFavorite = 1")
    fun getFavoriteMovies(): DataSource.Factory<Int, MovieEntity>

    @Transaction
    @Query("SELECT * FROM ${CatalogDatabase.MOVIE_TABLE_NAME} WHERE id = :movieId")
    fun getMovieById(movieId: String): LiveData<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: MovieEntity)

    @Update
    fun updateMovie(movie: MovieEntity)

}
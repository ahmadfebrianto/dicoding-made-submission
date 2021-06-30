package com.ahmadfebrianto.moviecatalogue.core.data.source.local.room

import androidx.room.*
import com.ahmadfebrianto.moviecatalogue.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM ${MovieDatabase.MOVIE_TABLE_NAME}")
    fun getAllMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM ${MovieDatabase.MOVIE_TABLE_NAME} WHERE isFavorite = 1")
    fun getFavoriteMovies(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Update
    fun updateMovie(movie: MovieEntity)
}
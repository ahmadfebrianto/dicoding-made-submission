package com.ahmadfebrianto.moviecatalogue.core.data.source.local.room

import androidx.room.*
import com.ahmadfebrianto.moviecatalogue.core.data.source.local.entity.MovieEntity
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface MovieDao {

    @Query("SELECT * FROM ${MovieDatabase.MOVIE_TABLE_NAME}")
    fun getAllMovies(): Flowable<List<MovieEntity>>

    @Query("SELECT * FROM ${MovieDatabase.MOVIE_TABLE_NAME} WHERE isFavorite = 1")
    fun getFavoriteMovies(): Flowable<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>): Completable

    @Update
    fun updateMovie(movie: MovieEntity)
}
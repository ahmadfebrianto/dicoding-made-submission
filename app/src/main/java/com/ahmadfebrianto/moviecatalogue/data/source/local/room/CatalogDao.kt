package com.ahmadfebrianto.moviecatalogue.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.ahmadfebrianto.moviecatalogue.data.source.local.entity.MovieEntity
import com.ahmadfebrianto.moviecatalogue.data.source.local.entity.TvShowEntity

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


    /*TV SHOWS*/

    @Query("SELECT * FROM ${CatalogDatabase.TV_SHOW_TABLE_NAME}")
    fun getAllTvShows(): DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM ${CatalogDatabase.TV_SHOW_TABLE_NAME} WHERE isFavorite = 1")
    fun getFavoriteTvShows(): DataSource.Factory<Int, TvShowEntity>

    @Transaction
    @Query("SELECT * FROM ${CatalogDatabase.TV_SHOW_TABLE_NAME} WHERE id = :tvShowId")
    fun getTvShowById(tvShowId: String): LiveData<TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShows(tvShows: List<TvShowEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShow(tvShow: TvShowEntity)

    @Update
    fun updateTvShow(tvShow: TvShowEntity)

}
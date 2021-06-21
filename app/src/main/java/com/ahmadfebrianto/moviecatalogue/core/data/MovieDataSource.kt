package com.ahmadfebrianto.moviecatalogue.core.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.ahmadfebrianto.moviecatalogue.core.data.source.local.entity.MovieEntity
import com.ahmadfebrianto.moviecatalogue.core.vo.Resource

interface MovieDataSource {

    /*MOVIES*/
    fun getAllMovies(): LiveData<Resource<PagedList<MovieEntity>>>
    fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>>
    fun getMovieById(movieId: String): LiveData<MovieEntity>
    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean)

}
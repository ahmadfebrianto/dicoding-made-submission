package com.ahmadfebrianto.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.ahmadfebrianto.moviecatalogue.data.source.local.entity.MovieEntity
import com.ahmadfebrianto.moviecatalogue.data.source.local.entity.TvShowEntity
import com.ahmadfebrianto.moviecatalogue.vo.Resource

interface CatalogDataSource {

    /*MOVIES*/
    fun getAllMovies(): LiveData<Resource<PagedList<MovieEntity>>>
    fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>>
    fun getMovieById(movieId: String): LiveData<Resource<MovieEntity>>
    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean)


    /*TV SHOWS*/
    fun getAllTvShows(): LiveData<Resource<PagedList<TvShowEntity>>>
    fun getFavoriteTvShows(): LiveData<PagedList<TvShowEntity>>
    fun getTvShowById(tvShowId: String): LiveData<Resource<TvShowEntity>>
    fun setFavoriteTvShow(tvShow: TvShowEntity, newState: Boolean)
}
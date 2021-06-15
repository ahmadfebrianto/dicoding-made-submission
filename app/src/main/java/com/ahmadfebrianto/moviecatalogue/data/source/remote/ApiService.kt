package com.ahmadfebrianto.moviecatalogue.data.source.remote

import com.ahmadfebrianto.moviecatalogue.data.source.remote.response.MovieList
import com.ahmadfebrianto.moviecatalogue.data.source.remote.response.TvShowList
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("${ApiConfig.MOVIE_LIST_ID}?page=1&api_key=${ApiConfig.API_KEY}")
    fun getMovieList(): Call<MovieList>

    @GET("${ApiConfig.TV_SHOW_LIST_ID}?page=1&api_key=${ApiConfig.API_KEY}")
    fun getTVShowList(): Call<TvShowList>

}
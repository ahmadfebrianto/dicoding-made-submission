package com.ahmadfebrianto.moviecatalogue.core.data.source.remote

import com.ahmadfebrianto.moviecatalogue.core.data.source.remote.response.MovieList
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("${ApiConfig.MOVIE_LIST_ID}?page=1&api_key=${ApiConfig.API_KEY}")
    fun getMovieList(): Call<MovieList>

}
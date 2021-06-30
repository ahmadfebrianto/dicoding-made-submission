package com.ahmadfebrianto.moviecatalogue.core.data.source.remote.api

import com.ahmadfebrianto.moviecatalogue.BuildConfig
import com.ahmadfebrianto.moviecatalogue.core.data.source.remote.response.MovieResponseList
import retrofit2.http.GET

interface ApiService {

    companion object {
        const val API_KEY = BuildConfig.API_KEY
        const val MOVIE_LIST_ID = 7096949
    }

    @GET("${MOVIE_LIST_ID}?page=1&api_key=${API_KEY}")
    suspend fun getMovieList(): MovieResponseList

}
package com.ahmadfebrianto.moviecatalogue.core.data.source.remote.api

import com.ahmadfebrianto.moviecatalogue.core.BuildConfig
import com.ahmadfebrianto.moviecatalogue.core.data.source.remote.response.MovieResponseList
import retrofit2.http.GET

interface ApiService {

    companion object {
        private const val API_KEY = BuildConfig.API_KEY
        private const val MOVIE_LIST_ID = 7096949
        const val query = "${MOVIE_LIST_ID}?page=1&api_key=${API_KEY}"
    }

    @GET(query)
    suspend fun getMovieList(): MovieResponseList
}
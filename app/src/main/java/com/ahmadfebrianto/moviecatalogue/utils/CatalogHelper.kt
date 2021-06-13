package com.ahmadfebrianto.moviecatalogue.utils

import android.content.Context
import com.ahmadfebrianto.moviecatalogue.data.source.remote.response.CatalogResponse
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class CatalogHelper(private val context: Context) {

    /*JSON Asset Reader*/
    private fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }


    /*MOVIES*/

    fun getMovies(): ArrayList<CatalogResponse> {
        val results = ArrayList<CatalogResponse>()
        val fileName = "movies.json"
        val jsArray = JSONArray(getJsonDataFromAsset(context, fileName))

        for (i in 0 until jsArray.length()) {
            val jsonObject = jsArray.getJSONObject(i)
            val posterUri = "file:///android_asset/poster_movie/mv${i + 1}.png"
            val movie = jsonToDataClass(jsonObject, posterUri)
            results.add(movie)
        }
        return results
    }

    fun getMovieById(movieId: String): CatalogResponse {
        var movie = CatalogResponse()
        val movies = getMovies()
        for (item in movies) {
            if (item.id == movieId) {
                movie = item
            }
        }
        return movie
    }


    /*TV SHOWS*/

    fun getTvShows(): ArrayList<CatalogResponse> {
        val results = ArrayList<CatalogResponse>()
        val fileName = "tvshows.json"
        val jsArray = JSONArray(getJsonDataFromAsset(context, fileName))

        for (i in 0 until jsArray.length()) {
            val jsonObject = jsArray.getJSONObject(i)
            val posterUri = "file:///android_asset/poster_tv_show/tv${i + 1}.png"
            val movie = jsonToDataClass(jsonObject, posterUri)
            results.add(movie)
        }
        return results
    }

    fun getTvShowById(tvShowId: String): CatalogResponse {
        var tvShow = CatalogResponse()
        val tvShows = getTvShows()
        for (item in tvShows) {
            if (item.id == tvShowId) {
                tvShow = item
            }
        }
        return tvShow
    }


    /*JSON to Data Class*/

    private fun jsonToDataClass(jsonObject: JSONObject, posterUri: String): CatalogResponse {
        val movie = CatalogResponse()
        movie.id = jsonObject.getString("movieId")
        movie.poster = posterUri
        movie.title = jsonObject.getString("title")
        movie.description = jsonObject.getString("description")
        movie.rating = jsonObject.getString("rating")
        movie.releaseYear = jsonObject.getString("release_year")
        return movie
    }
}
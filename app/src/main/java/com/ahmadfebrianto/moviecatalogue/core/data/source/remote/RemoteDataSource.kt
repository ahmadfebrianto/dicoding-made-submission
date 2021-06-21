package com.ahmadfebrianto.moviecatalogue.core.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ahmadfebrianto.moviecatalogue.core.data.source.remote.response.MovieList
import com.ahmadfebrianto.moviecatalogue.core.data.source.remote.response.MovieResponse
import com.ahmadfebrianto.moviecatalogue.core.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    /*MOVIES*/

    fun getAllMovies(): LiveData<ApiResponse<List<MovieResponse>>> {
        EspressoIdlingResource.increment()

        val client = ApiConfig.getApiService().getMovieList()
        val resultMovies = MutableLiveData<ApiResponse<List<MovieResponse>>>()

        client.enqueue(object : Callback<MovieList> {
            override fun onResponse(call: Call<MovieList>, response: Response<MovieList>) {
                resultMovies.value = ApiResponse.success(response.body()!!.movieList)
                if (!EspressoIdlingResource.idlingResource.isIdleNow) {
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<MovieList>, t: Throwable) {
                Log.d("MOVIE RESPONSE", t.message.toString())
                if (!EspressoIdlingResource.idlingResource.isIdleNow) {
                    EspressoIdlingResource.decrement()
                }
            }
        })
        return resultMovies
    }
}
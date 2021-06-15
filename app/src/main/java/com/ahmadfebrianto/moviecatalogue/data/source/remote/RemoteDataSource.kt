package com.ahmadfebrianto.moviecatalogue.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ahmadfebrianto.moviecatalogue.data.source.remote.response.MovieList
import com.ahmadfebrianto.moviecatalogue.data.source.remote.response.MovieResponse
import com.ahmadfebrianto.moviecatalogue.data.source.remote.response.TvShowList
import com.ahmadfebrianto.moviecatalogue.data.source.remote.response.TvShowResponse
import com.ahmadfebrianto.moviecatalogue.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {
//    private val handler = Handler(Looper.getMainLooper())
//
//    companion object {
//
//        @Volatile
//        private var instance: RemoteDataSource? = null
//
//        fun getInstance(helper: CatalogHelper): RemoteDataSource {
//            return instance ?: synchronized(this) {
//                RemoteDataSource(helper).apply {
//                    instance = this
//                }
//            }
//        }
//    }


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

    /*TV SHOWS*/

    fun getAllTvShows(): LiveData<ApiResponse<List<TvShowResponse>>> {
        EspressoIdlingResource.increment()

        val resultTvShows = MutableLiveData<ApiResponse<List<TvShowResponse>>>()
        val client = ApiConfig.getApiService().getTVShowList()
        client.enqueue(object : Callback<TvShowList> {
            override fun onResponse(call: Call<TvShowList>, response: Response<TvShowList>) {
                resultTvShows.value = ApiResponse.success(response.body()!!.tvShowList)
                if (!EspressoIdlingResource.idlingResource.isIdleNow) {
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<TvShowList>, t: Throwable) {
                Log.d("TV SHOW RESPONSE", t.message.toString())
                if (!EspressoIdlingResource.idlingResource.isIdleNow) {
                    EspressoIdlingResource.decrement()
                }
            }
        })
        return resultTvShows
    }
}
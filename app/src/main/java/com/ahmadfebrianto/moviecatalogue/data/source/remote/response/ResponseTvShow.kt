package com.ahmadfebrianto.moviecatalogue.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvShowResponse(

    @SerializedName("id")
    val id: String,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("backdrop_path")
    val backdropPath: String,

    @SerializedName("name")
    val title: String,

    @SerializedName("overview")
    val description: String,

    @SerializedName("original_language")
    val language: String,

    @SerializedName("vote_average")
    val rating: String,

    @SerializedName("first_air_date")
    val releaseDate: String

)

data class TvShowList(
    @SerializedName("results")
    val tvShowList: List<TvShowResponse>
)
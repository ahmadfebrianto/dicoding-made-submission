package com.ahmadfebrianto.moviecatalogue.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(

    @SerializedName("id")
    val id: String,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("backdrop_path")
    val backdropPath: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("overview")
    val description: String,

    @SerializedName("original_language")
    val language: String,

    @SerializedName("vote_average")
    val rating: String,

    @SerializedName("release_date")
    val releaseDate: String

)

data class MovieResponseList(
    @SerializedName("results")
    val movieList: List<MovieResponse>
)
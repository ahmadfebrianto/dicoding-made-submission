package com.ahmadfebrianto.moviecatalogue.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    var id: String,
    var backdropPath: String,
    var posterPath: String,
    var title: String,
    var description: String,
    var language: String,
    var rating: String,
    var releaseDate: String,
    var isFavorite: Boolean
) : Parcelable

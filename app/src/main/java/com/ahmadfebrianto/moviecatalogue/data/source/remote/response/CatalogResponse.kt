package com.ahmadfebrianto.moviecatalogue.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CatalogResponse(
    var id: String = "",
    var poster: String = "",
    var title: String = "",
    var description: String = "",
    var rating: String = "",
    var releaseYear: String = "",
    var isFavorite: Boolean = false
) : Parcelable

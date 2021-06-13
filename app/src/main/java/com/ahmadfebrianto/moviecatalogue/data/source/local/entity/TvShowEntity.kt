package com.ahmadfebrianto.moviecatalogue.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ahmadfebrianto.moviecatalogue.data.source.local.room.CatalogDatabase

@Entity(tableName = CatalogDatabase.TV_SHOW_TABLE_NAME)
data class TvShowEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: String = "",

    @ColumnInfo(name = "poster")
    var poster: String = "",

    @ColumnInfo(name = "title")
    var title: String = "",

    @ColumnInfo(name = "description")
    var description: String = "",

    @ColumnInfo(name = "rating")
    var rating: String = "",

    @ColumnInfo(name = "releaseYear")
    var releaseYear: String = "",

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)
package com.ahmadfebrianto.moviecatalogue.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ahmadfebrianto.moviecatalogue.core.data.source.local.room.MovieDatabase

@Entity(tableName = MovieDatabase.MOVIE_TABLE_NAME)
data class MovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: String = "",

    @ColumnInfo(name = "backdropPath")
    var backdropPath: String = "",

    @ColumnInfo(name = "posterPath")
    var posterPath: String = "",

    @ColumnInfo(name = "title")
    var title: String = "",

    @ColumnInfo(name = "description")
    var description: String = "",

    @ColumnInfo(name = "language")
    var language: String = "",

    @ColumnInfo(name = "rating")
    var rating: String = "",

    @ColumnInfo(name = "releaseDate")
    var releaseDate: String = "",

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)
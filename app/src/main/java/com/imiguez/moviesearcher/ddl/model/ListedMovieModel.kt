package com.imiguez.moviesearcher.ddl.model

class ListedMovieModel(
    val id: Int,
    val title: String?,
    val adult: Boolean?,
    val posterPath: String?,
    val voteAverage: Double?,
    val releaseDate: String?
)
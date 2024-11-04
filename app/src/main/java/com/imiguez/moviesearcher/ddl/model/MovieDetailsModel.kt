package com.imiguez.moviesearcher.ddl.model

import com.imiguez.moviesearcher.ddl.dtos.Genre

class MovieDetailsModel (
    val adult: Boolean,
    val backdropPath: String,
    val genres: List<Genre>,
    val id: Int,
    val overview: String,
    val releaseDate: String,
    val title: String,
    val originalTitle: String,
    val voteAverage: Double,
)

package com.imiguez.moviesearcher.ddl.dtos

import com.google.gson.annotations.SerializedName

class ListedResponseDto (
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<ListedMovieDto>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)
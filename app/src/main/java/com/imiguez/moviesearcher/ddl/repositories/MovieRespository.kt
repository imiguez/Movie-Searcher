package com.imiguez.moviesearcher.ddl.repositories

import com.imiguez.moviesearcher.ddl.datasources.MovieRemoteDataSource
import com.imiguez.moviesearcher.ddl.model.ListedMovieModel
import javax.inject.Inject

class MovieRespository @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource
) {

    suspend fun getMoviesToDiscover(page: Int): List<ListedMovieModel>? {
        return movieRemoteDataSource.getMoviesToDiscover(1)
    }

}
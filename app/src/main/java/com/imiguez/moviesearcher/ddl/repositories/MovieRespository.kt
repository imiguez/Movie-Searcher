package com.imiguez.moviesearcher.ddl.repositories

import com.imiguez.moviesearcher.ddl.datasources.MovieRemoteDataSource
import com.imiguez.moviesearcher.ddl.model.ListedMovieModel
import com.imiguez.moviesearcher.ddl.model.MovieDetailsModel
import javax.inject.Inject

class MovieRespository @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource
) {
    suspend fun getPopularMovies(page: Int): List<ListedMovieModel>? {
        return movieRemoteDataSource.getPopularMovies(page)
    }

    suspend fun getMovieDetails(id: Int): MovieDetailsModel? {
        return movieRemoteDataSource.getMovieDetails(id)
    }

}
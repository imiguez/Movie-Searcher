package com.imiguez.moviesearcher.ddl.datasources

import com.imiguez.moviesearcher.ddl.apis.MoviesApi
import com.imiguez.moviesearcher.ddl.model.ListedMovieModel
import com.imiguez.moviesearcher.ddl.model.MovieDetailsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(
    private val moviesApi: MoviesApi
) {

    suspend fun getPopularMovies(
        page: Int
    ): MutableList<ListedMovieModel>? {
        return withContext(Dispatchers.IO) {
            val response = moviesApi.getPopularMovies(page)
            if (response.code() != 200) throw IOException("Response error, status code: " + response.code())
            var responseFormatted: MutableList<ListedMovieModel> = mutableListOf()
            response.body()?.results?.forEach({ dto ->
                responseFormatted.add(dto.toListedMovieModel())
            })
            return@withContext responseFormatted
        }
    }

    suspend fun getMovieDetails(id: Int): MovieDetailsModel? {
        return withContext(Dispatchers.IO) {
            val response = moviesApi.getMovieDetails(id)
            if (response.code() != 200) throw IOException("Response error, status code: " + response.code())
            return@withContext response.body()?.toMovieDetailsModel()
        }
    }

    suspend fun searchMovieByText(query: String, page: Int): MutableList<ListedMovieModel>? {
        return withContext(Dispatchers.IO) {
            val response = moviesApi.searchMovieByText(query, page)
            if (response.code() != 200) throw IOException("Response error, status code: " + response.code())
            var responseFormatted: MutableList<ListedMovieModel> = mutableListOf()
            response.body()?.results?.forEach({ dto ->
                responseFormatted.add(dto.toListedMovieModel())
            })
            return@withContext responseFormatted
        }
    }
}
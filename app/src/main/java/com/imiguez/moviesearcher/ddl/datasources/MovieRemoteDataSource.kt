package com.imiguez.moviesearcher.ddl.datasources

import android.util.Log
import com.imiguez.moviesearcher.ddl.apis.MoviesApi
import com.imiguez.moviesearcher.ddl.model.ListedMovieModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(
    private val moviesApi: MoviesApi
) {

    // TODO: implement error handling.
    suspend fun getMoviesToDiscover(
        page: Int
    ): MutableList<ListedMovieModel>? {
        return withContext(Dispatchers.IO) {
            try {
                val response = moviesApi.discoverMovies(page)
                var responseFormatted: MutableList<ListedMovieModel> = mutableListOf()
                response.body()?.results?.forEach({ dto ->
                    responseFormatted.add(dto.toListedMovieModel())
                })
                return@withContext responseFormatted
            } catch (e: Exception) {
                Log.e("API_ERROR", "Error al hacer la solicitud", e)
                return@withContext null
            }
        }
    }

}
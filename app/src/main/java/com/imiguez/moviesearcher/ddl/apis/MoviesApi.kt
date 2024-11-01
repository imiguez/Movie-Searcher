package com.imiguez.moviesearcher.ddl.apis

import com.imiguez.moviesearcher.ddl.dtos.ListedResponseDto
import com.imiguez.moviesearcher.ddl.dtos.MovieDetailsDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {

    @GET("discover/movie")
    suspend fun getMoviesToDiscover(
        @Query("page") page: Int,
    ): Response<ListedResponseDto>


    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int,
    ): Response<ListedResponseDto>


    @GET("movie/{id}")
    suspend fun getMovieDetails(
        @Path("id") id: Int
    ): Response<MovieDetailsDto>

}
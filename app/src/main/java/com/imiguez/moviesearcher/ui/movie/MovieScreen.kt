package com.imiguez.moviesearcher.ui.movie

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.imiguez.moviesearcher.ui.common.components.FetchError
import com.imiguez.moviesearcher.ui.movie.components.MovieDetails

@Composable
fun MovieScreen (
    id: Int,
    movieViewModel: MovieViewModel
) {
    val movie by movieViewModel.movie.collectAsStateWithLifecycle()
    val isLoading by movieViewModel.loading.collectAsStateWithLifecycle()
    val error by movieViewModel.error.collectAsStateWithLifecycle()
    val hasLaunched by movieViewModel.hasLaunched.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        if (movie?.id != id || !hasLaunched) {
            movieViewModel.getMovieDetails(id)
            movieViewModel.setHasLaunched(true)
        }
    }

    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            if (error.exists) FetchError(error)
            else MovieDetails(movie!!)
        }
    }
}
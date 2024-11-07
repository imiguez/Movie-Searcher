package com.imiguez.moviesearcher.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.imiguez.moviesearcher.ui.common.components.FetchError
import com.imiguez.moviesearcher.ui.home.components.MoviesScrollContainer
import com.imiguez.moviesearcher.ui.home.components.SearchBar

@Composable
fun HomeScreen (
    homeViewModel: HomeViewModel,
    onSelectMovie: (id: Int) -> Unit
) {
    val movies by homeViewModel.movies.collectAsStateWithLifecycle()
    val isLoading by homeViewModel.loading.collectAsStateWithLifecycle()
    val hasLaunched by homeViewModel.hasLaunched.collectAsStateWithLifecycle()
    val error by homeViewModel.error.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        if (!hasLaunched) {
            homeViewModel.getPopularMovies()
            homeViewModel.setHasLaunched(true)
        }
    }

    Column (
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    ) {
        SearchBar(
            viewModel = homeViewModel,
        )
        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            if (error.exists) FetchError(error)
            else MoviesScrollContainer(movies, onSelectMovie)
        }
    }

}

package com.imiguez.moviesearcher.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.imiguez.moviesearcher.ui.home.components.MoviesScrollContainer

@Composable
fun HomeScreen (
    homeViewModel: HomeViewModel
) {
    val movies by homeViewModel.movies.collectAsStateWithLifecycle()
    val isLoading by homeViewModel.loading.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        homeViewModel.loadMoviesToDiscover()
    }

    Column (
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    ) {
        // TODO: Search Bar Component
        Text(
            "Search Bar Component",
            Modifier.background(MaterialTheme.colorScheme.primary).fillMaxWidth().padding(horizontal = 5.dp, vertical = 10.dp),
            color = MaterialTheme.colorScheme.onPrimary
        )

        if (isLoading) CircularProgressIndicator()
        else MoviesScrollContainer(movies)
    }

}

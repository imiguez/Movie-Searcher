package com.imiguez.moviesearcher.ui.home

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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

    Column {
        // TODO: Search Bar Component
        Text("Search Bar Component")

        if (isLoading) CircularProgressIndicator()
        else MoviesScrollContainer(movies)
    }

}

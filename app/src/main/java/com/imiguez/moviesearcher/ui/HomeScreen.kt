package com.imiguez.moviesearcher.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen (

) {
    var movies = listOf(MovieUIModel("pepe", "www.pepe.com"))

    Column {
        // TODO: Search Bar Component
        MoviesScrollContainer(movies)
    }

}

@Composable
@Preview
private fun HomeScreenPreview () {
    HomeScreen()
}
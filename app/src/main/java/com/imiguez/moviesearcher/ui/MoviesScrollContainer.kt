package com.imiguez.moviesearcher.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.imiguez.moviesearcher.R

@Composable
fun MoviesScrollContainer(
    movies: List<MovieUIModel>
) {
    //val bg_primary = ContextCompat.getColor(LocalContext.current, R.color.bg_primary)
    val wh = ContextCompat.getColor(LocalContext.current, R.color.white);

    LazyColumn(
        modifier = Modifier.background(
            color = Color(wh)
        ).fillMaxWidth()
    ) {


        items(movies) { movie ->
            MoviePosterButton(movie)
        }
    }

}

@Composable
@Preview
private fun MoviesScrollContainerPreview() {
    MoviesScrollContainer(
        movies = listOf(
            MovieUIModel("Fast and Fourious X", "https://image.tmdb.org/t/p/w500/1E5baAaEse26fej7uHcjOgEE2t2.jpg")
        )
    )
}
package com.imiguez.moviesearcher.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MoviePosterButton (
    movie: MovieUIModel
) {

    Column(
    ) {
        // TODO: add images
        Text(text = "IMAGE")
        Text(
            text = movie.title
        )
    }
}

@Composable
@Preview
private fun MoviePosterButtonPreview () {
    MoviePosterButton(
        MovieUIModel(
            "Fast and Fourious X",
            "https://image.tmdb.org/t/p/w500/1E5baAaEse26fej7uHcjOgEE2t2.jpg"
        )
    )
}
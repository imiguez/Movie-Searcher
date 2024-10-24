package com.imiguez.moviesearcher.ui.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.imiguez.moviesearcher.ddl.model.ListedMovieModel

@Composable
fun MoviePosterButton (
    movie: ListedMovieModel
) {

    Column(
    ) {
        // TODO: add the image, and the rest of attributes.
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
        ListedMovieModel(
            adult = false,
            id = 917496,
            popularity = 1667.313,
            posterPath = "/kWJw7dCWHcfMLr0irTHAPIKrJ4I.jpg",
            title = "Bitelchús Bitelchús",
            voteAverage = 7.208,
            voteCount = 1478
        )
    )
}
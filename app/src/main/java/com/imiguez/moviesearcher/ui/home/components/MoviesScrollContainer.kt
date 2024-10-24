package com.imiguez.moviesearcher.ui.home.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.imiguez.moviesearcher.ddl.model.ListedMovieModel

@Composable
fun MoviesScrollContainer(
    movies: MutableList<ListedMovieModel>
) {

    LazyColumn(
        modifier = Modifier.fillMaxWidth()
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
        movies = mutableListOf(
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
    )
}
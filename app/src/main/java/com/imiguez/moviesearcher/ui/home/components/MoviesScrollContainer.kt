package com.imiguez.moviesearcher.ui.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imiguez.moviesearcher.ddl.model.ListedMovieModel

@Composable
fun MoviesScrollContainer(
    movies: MutableList<ListedMovieModel>
) {

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 150.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 8.dp),
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
                releaseDate = "1977-05-25",
            )
        )
    )
}
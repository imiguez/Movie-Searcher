package com.imiguez.moviesearcher.ui.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.imiguez.moviesearcher.ddl.model.ListedMovieModel

@Composable
fun MoviesScrollContainer(
    movies: MutableList<ListedMovieModel>,
    onSelectMovie: (id: Int) -> Unit
) {

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 150.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
    ) {
        items(movies) { movie ->
            MoviePosterButton(
                movie = movie,
                onPress = { onSelectMovie(movie.id) }
            )
        }
    }

}
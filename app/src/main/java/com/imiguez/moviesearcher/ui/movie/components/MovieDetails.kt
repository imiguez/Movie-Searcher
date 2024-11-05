package com.imiguez.moviesearcher.ui.movie.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.imiguez.moviesearcher.R
import com.imiguez.moviesearcher.ddl.model.MovieDetailsModel
import com.imiguez.moviesearcher.ui.common.utils.DateFormatter

@Composable
fun MovieDetails (
    movie: MovieDetailsModel
) {
    MovieBackdrop(movie.backdropPath, movie.voteAverage)
    Column(
        modifier = Modifier.padding(horizontal = 10.dp).padding(bottom = 10.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = if (movie.adult == true) "+18" else "ATP",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 10.sp,
            )
            Text(
                text = if (!movie.releaseDate.isNullOrBlank()) DateFormatter.format(movie.releaseDate) else "",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 10.sp,
            )
        }

        Text(
            modifier = Modifier.padding(vertical = 10.dp),
            text = movie.title ?: "",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        if (!movie.title.isNullOrBlank() && !movie.originalTitle.isNullOrBlank() && !movie.title.lowercase().equals(movie.originalTitle.lowercase())) {
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(stringResource(R.string.original_title)+ ": ")
                    }
                    append(movie.originalTitle)
                },
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        Text(
            modifier = Modifier.padding(top = 10.dp),
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(stringResource(R.string.synopsis)+ ": ")
                }
                append(movie.overview)
            },
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onBackground
        )

        var genres = ""
        if (!movie.genres.isNullOrEmpty()) {
            movie.genres.forEachIndexed { index, genre ->
                genres += genre.name + if (index+1 < movie.genres.size) ", " else "."
            }
            Text(
                modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
                text = stringResource(R.string.genres) +": $genres",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 12.sp,
            )
        }

        Button(
            modifier = Modifier.fillMaxWidth().padding(vertical = 20.dp),
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            onClick = {}
        ) {
            Text(
                modifier = Modifier.padding(vertical = 6.dp),
                text = stringResource(R.string.play)
            )
        }
        Spacer(
            modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.onBackground).height(1.dp)
        )
    }
}
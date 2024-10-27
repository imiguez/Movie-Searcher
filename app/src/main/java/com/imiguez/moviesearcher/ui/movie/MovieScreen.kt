package com.imiguez.moviesearcher.ui.movie

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.imiguez.moviesearcher.BuildConfig
import com.imiguez.moviesearcher.R

@Composable
fun MovieScreen (
    movie: Any,
) {

    Column(
      modifier = Modifier.fillMaxWidth(),
    ) {
        AsyncImage(
            model = "${BuildConfig.TMBD_IMAGES_BASE_URL}w500${movie.backdropPath}",
            contentDescription = null,
            modifier = Modifier.fillMaxWidth()
                .aspectRatio(500f/281f),
            onError = { error ->
                Log.e("Image Load Error", error.toString())
            }
        )
        Column(
            modifier = Modifier.padding(10.dp),
        ) {
            Text(
                text = movie.title,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 5.dp).padding(top = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = if (movie.adult) "+18" else "ATP",
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 10.sp,
                )
                Text(
                    text = movie.releaseDate,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 10.sp,
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Icon(
                    Icons.Default.Star,
                    null,
                    tint = colorResource(R.color.golden),
                    modifier = Modifier.width(25.dp)
                )
                Text(
                    modifier = Modifier.padding(start = 2.dp, top = 2.dp),
                    text = String.format("%.1f", movie.voteAverage),
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold,
                )
            }
            Text(
                modifier = Modifier.padding(top = 10.dp),
                text = movie.overview,
                color = MaterialTheme.colorScheme.onBackground
            )
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
            ) {
                Text(
                    text = stringResource(R.string.genres)+": ",
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 12.sp,
                )
                movie.genres.forEachIndexed { index, genre ->
                    val name = genre.name + if (index+1 < movie.genres.size) ", " else "."
                    Text(
                        text = name,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 12.sp,
                    )
                }
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
                modifier = Modifier.fillMaxWidth().background(Color.White).height(1.dp)
            )
        }
    }

}
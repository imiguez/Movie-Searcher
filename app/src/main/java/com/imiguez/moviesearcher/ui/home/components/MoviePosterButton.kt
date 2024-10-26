package com.imiguez.moviesearcher.ui.home.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.imiguez.moviesearcher.BuildConfig
import com.imiguez.moviesearcher.R
import com.imiguez.moviesearcher.ddl.model.ListedMovieModel
import com.imiguez.moviesearcher.ui.themes.dark.primaryContainerBorder

@Composable
fun MoviePosterButton (
    movie: ListedMovieModel
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.primaryContainer)
            .border(1.dp, MaterialTheme.colorScheme.primaryContainerBorder, RoundedCornerShape(10.dp))
            .padding(10.dp)
    ) {
        // TODO: handle image loading and error.
        Box {
            AsyncImage(
                model = "${BuildConfig.TMBD_IMAGES_BASE_URL}w500${movie.posterPath}",
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
                    .aspectRatio(500f/750f)
                    .clip(RoundedCornerShape(5.dp))
                    .background(Color.White),
                onError = { error ->
                    Log.e("Image Load Error", error.toString())
                }
            )
            val roundedCornerShape = RoundedCornerShape(topStart = 5.dp, bottomEnd = 5.dp)
            Row(
                modifier = Modifier
                    .width(50.dp)
                    .clip(roundedCornerShape)
                    .shadow(elevation = 5.dp, shape = roundedCornerShape, spotColor = Color.Black)
                    .background(colorResource(R.color.red_contrast))
                    .border(1.dp, Color.Black.copy(alpha = 0.1f), roundedCornerShape)
                    .border(2.dp, Color.Black.copy(alpha = 0.1f), roundedCornerShape)
                    .border(3.dp, Color.Black.copy(alpha = 0.1f), roundedCornerShape),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                ) {
                Icon(
                    Icons.Default.Star,
                    null,
                    tint = colorResource(R.color.golden),
                    modifier = Modifier.width(20.dp)
                )
                Text(
                    text = String.format("%.1f", movie.voteAverage),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(end = 5.dp)
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 5.dp),
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

        Text(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 5.dp),
            color = MaterialTheme.colorScheme.onBackground,
            text = movie.title,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            minLines = 2,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
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
            releaseDate = "1977-05-25",
        )
    )
}
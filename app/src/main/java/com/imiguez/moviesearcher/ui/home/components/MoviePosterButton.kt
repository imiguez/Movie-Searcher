package com.imiguez.moviesearcher.ui.home.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.imiguez.moviesearcher.BuildConfig
import com.imiguez.moviesearcher.R
import com.imiguez.moviesearcher.ddl.model.ListedMovieModel

@Composable
fun MoviePosterButton (
    movie: ListedMovieModel,
    onPress: () -> Unit
) {
    Button(
        onPress,
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // TODO: handle image loading and error.
            Box {
                AsyncImage(
                    model = "${BuildConfig.TMBD_IMAGES_BASE_URL}w500${movie.posterPath}",
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth()
                        .aspectRatio(500f / 750f)
                        .clip(RoundedCornerShape(topStart = 5.dp, topEnd = 5.dp)),
                    onError = { error ->
                        Log.e("Image Load Error", error.toString())
                    }
                )
                val roundedCornerShape = RoundedCornerShape(topStart = 5.dp, bottomEnd = 5.dp)
                Row(
                    modifier = Modifier
                        .width(50.dp).height(25.dp)
                        .clip(roundedCornerShape)
                        .shadow(
                            elevation = 5.dp,
                            shape = roundedCornerShape,
                            spotColor = Color.Black
                        )
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
}
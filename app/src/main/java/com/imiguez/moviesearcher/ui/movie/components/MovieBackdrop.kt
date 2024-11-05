package com.imiguez.moviesearcher.ui.movie.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.imiguez.moviesearcher.BuildConfig
import com.imiguez.moviesearcher.R
import com.imiguez.moviesearcher.ui.common.components.ImageFetchedError

@Composable
fun MovieBackdrop (
    path: String?,
    voteAverage: Double?
) {
    val redContrast = colorResource(R.color.red_contrast)
    val onBgColor = MaterialTheme.colorScheme.onBackground

    Box(
        modifier = Modifier.fillMaxWidth()
            .aspectRatio(500f / 281f)
            .drawBehind {
                val strokeWidth = 2 * density
                val y = size.height - strokeWidth / 2
                val c = onBgColor.copy(alpha = .1f)
                drawLine(
                    c,
                    Offset(0f, y),
                    Offset(size.width, y),
                    strokeWidth
                )
            }
        ,
        contentAlignment = if (!path.isNullOrBlank()) Alignment.TopStart else Alignment.Center,
    ) {
        if (!path.isNullOrBlank()) {
            AsyncImage(
                model = "${ BuildConfig.TMBD_IMAGES_BASE_URL}w500$path",
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
                    .aspectRatio(500f / 281f),
            )
            Row {
                Row(
                    modifier = Modifier.clip(RoundedCornerShape(topStart = 5.dp))
                        .background(colorResource(R.color.red_contrast))
                        .drawBehind {
                            val strokeWidth = 2 * density
                            val y = size.height - strokeWidth / 2
                            val c = Color.Black.copy(alpha = .1f)
                            drawLine(
                                c,
                                Offset(0f, y),
                                Offset(size.width, y),
                                strokeWidth
                            )
                            drawLine(
                                c,
                                Offset(0f, 2f),
                                Offset(size.width, 2f),
                                strokeWidth
                            )
                        }
                        .padding(vertical = 2.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        Icons.Default.Star,
                        null,
                        tint = colorResource(R.color.golden),
                        modifier = Modifier.width(25.dp)
                    )
                    Text(
                        modifier = Modifier.padding(start = 2.dp, top = 2.dp, end = 8.dp),
                        text = String.format("%.1f", voteAverage),
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.Bold,
                    )
                }
                Canvas(
                    modifier = Modifier.width(15.dp).height(30.dp)
                ) {
                    drawPath(
                        Path().apply {
                            moveTo(size.width, 0f)
                            lineTo(0f, size.height / 2)
                            lineTo(size.width, size.height)
                            lineTo(0f, size.height)
                            lineTo(0f, 0f)
                            lineTo(size.width, 0f)
                            close()
                        },
                        color = redContrast
                    )
                }
            }
        } else {
            ImageFetchedError(iconModifier = Modifier.size(100.dp))
        }
    }
}
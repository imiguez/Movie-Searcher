package com.imiguez.moviesearcher.ui.movie.components

import android.util.Log
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil3.compose.AsyncImage

@Composable
fun MovieBackdrop (
    path: String?,
) {
    if (path != null) {
        AsyncImage(
            model = path,
            contentDescription = null,
            modifier = Modifier.fillMaxWidth()
                .aspectRatio(500f/281f),
            onError = { error ->
                Log.e("Image Load Error", error.toString())
            }
        )
    } else {
        // TODO: Add a broken image icon
    }
}
package com.imiguez.moviesearcher.ui.home.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.imiguez.moviesearcher.R
import com.imiguez.moviesearcher.ui.home.HomeViewModel


@Composable
fun SearchBar(
    viewModel: HomeViewModel,
) {
    val query by viewModel.query.collectAsStateWithLifecycle()
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        TextField(
            modifier = Modifier.padding(horizontal = 8.dp).padding(top = 8.dp).fillMaxWidth(),
            value = query,
            onValueChange = { newText ->
                viewModel.setPage(1)
                viewModel.setQuery(newText)
            },
            placeholder = { Text(text = stringResource(R.string.search) + "...") },
            trailingIcon = {
                IconButton(onClick = { viewModel.setQuery("") }) {
                    Icon(Icons.Filled.Clear, contentDescription = "Clear text")
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    viewModel.searchMovieByText()
                }
            )
        )
    }
}
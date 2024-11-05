package com.imiguez.moviesearcher.ui.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.imiguez.moviesearcher.ui.common.utils.LanguageChanger
import com.imiguez.moviesearcher.ui.common.utils.LanguageCode
import com.imiguez.moviesearcher.R

@Composable
fun LanguageButton (

) {
    var isExpanded by rememberSaveable { mutableStateOf(false) }
    val dropdownItems = listOf<LanguageCode>(LanguageCode.EN, LanguageCode.ES)
    val ctx = LocalContext.current
    var currentLanguage by rememberSaveable { mutableStateOf(ctx.resources.configuration.locale) }

    @Composable
    @ReadOnlyComposable
    fun getFullLanguage(from: LanguageCode): String {
        return if (from.code == LanguageCode.EN.code) stringResource(R.string.english)
        else stringResource(R.string.spanish)
    }

    Card(
        modifier = Modifier.padding(horizontal = 8.dp).padding(vertical = 0.dp),
        shape = RoundedCornerShape(5.dp),
        onClick = { isExpanded = true },
    ) {
        Box(
            modifier = Modifier.width(50.dp).height(30.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(currentLanguage.language)
        }
        DropdownMenu(
            modifier = Modifier.widthIn(min = 50.dp, max = 100.dp),

            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
        ) {
            dropdownItems.forEach { item ->
                DropdownMenuItem(
                    modifier = Modifier.widthIn(min = 50.dp),

                    onClick = {
                        LanguageChanger.changeAppLanguage(ctx, item)
                        isExpanded = false
                    },
                    text = { Text(
                        text = getFullLanguage(item),
                        modifier = Modifier.fillMaxSize(),
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    ) }
                )
            }
        }
    }
}
package com.imiguez.moviesearcher.ui.common.utils

data class ErrorState (
    var msg: String,
    var exists: Boolean,
    var lastAction: () -> Unit
)
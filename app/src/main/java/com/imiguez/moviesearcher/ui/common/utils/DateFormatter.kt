package com.imiguez.moviesearcher.ui.common.utils

class DateFormatter {
    companion object{
        fun format(stringDate: String): String {
            return stringDate.replace("-", "/")
        }
    }
}
package com.imiguez.moviesearcher.ui.common.utils

import java.text.SimpleDateFormat
import java.util.Locale

class DateFormatter {
    companion object{
        fun format(stringDate: String, lenguage: Locale = Locale.getDefault()): String {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd", lenguage)
            val outputFormat = SimpleDateFormat("dd/MM/yyyy", lenguage)
            val date = inputFormat.parse(stringDate)
            return outputFormat.format(date)
        }

        fun getYear(stringDate: String): String {
            return stringDate.split("-")[0]
        }
    }
}
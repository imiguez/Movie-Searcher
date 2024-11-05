package com.imiguez.moviesearcher.ui.common.utils

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import java.util.Locale

class LanguageChanger {
    companion object {
        fun changeAppLanguage(context: Context, languageCode: LanguageCode) {
            val locale = Locale(languageCode.code)
            Locale.setDefault(locale)

            val config = Configuration(context.resources.configuration)
            config.setLocale(locale)

            context.resources.updateConfiguration(config, context.resources.displayMetrics)

            val intent = context.packageManager.getLaunchIntentForPackage(context.packageName)
            intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            context.startActivity(intent)
        }
    }
}
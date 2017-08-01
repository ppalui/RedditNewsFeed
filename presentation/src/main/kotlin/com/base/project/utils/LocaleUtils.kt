package com.base.project.utils

import android.content.Context
import android.content.res.Configuration
import com.navik.data.extentions.defaultSharedPreferences
import com.navik.data.extentions.putString
import java.util.*


object LocaleUtils {

    private var sLocale: Locale? = null
    private val KEY_APP_LOCALE = ":KEY_APP_LOCALE"

    fun setLocale(context: Context, locale: AppLocale) {
        val preferences = context.defaultSharedPreferences
        preferences.putString(KEY_APP_LOCALE, locale.text)
        setLocale(Locale(locale.text))
        updateConfig(context)
    }

    fun updateConfig(context: Context) {
        val default = getLocale(context)
        setLocale(Locale(default.text))
        val res = context.resources
        val configuration = res.configuration
        val config = Configuration(configuration)
        // We must use the now-deprecated config.locale and res.updateConfiguration here,
        // because the replacements aren't available till API level 24 and 17 respectively.
        config.locale = sLocale
        res.updateConfiguration(config, res.displayMetrics)
    }

    fun getLocale(context: Context): AppLocale {
        val preferences = context.defaultSharedPreferences
        val appLocale = when (preferences.getString(KEY_APP_LOCALE, Locale.getDefault().language)) {
            AppLocale.THAI.text -> AppLocale.THAI
            else -> AppLocale.ENGLISH
        }

        return AppLocale.valueOf(appLocale.name)
    }

    private fun setLocale(locale: Locale) {
        sLocale = locale
        if (sLocale != null) {
            Locale.setDefault(sLocale)
        }
    }
}

enum class AppLocale(val text: String) {
    ENGLISH("en"), THAI("th")
}
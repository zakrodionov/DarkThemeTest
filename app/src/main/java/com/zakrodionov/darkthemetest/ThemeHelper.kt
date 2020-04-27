package com.zakrodionov.darkthemetest

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate

object ThemeHelper {
    private const val LIGHT_MODE = "light"
    private const val DARK_MODE = "dark"
    const val DEFAULT_MODE = "default"

    fun applyTheme(theme: String) {
        when (theme) {
            LIGHT_MODE -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            DARK_MODE -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            else -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY)
                }
            }
        }
    }

    fun getThemeIndex(context: Context, theme: String): Int {
        val themeEntry = context.resources.getStringArray(R.array.themeEntryArray)
        Log.d("change_theme", "getThemeIndex :: $theme")

        return when (theme) {
            LIGHT_MODE -> {
                themeEntry.indexOf(theme)
            }
            DARK_MODE -> {
                themeEntry.indexOf(theme)
            }
            else -> {
                themeEntry.indexOf(DEFAULT_MODE)
            }
        }
    }
}
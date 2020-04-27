package com.zakrodionov.darkthemetest

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.util.Log
import com.zakrodionov.darkthemetest.MainActivity.Companion.PREFERENCE_KEY

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val themePref = sharedPreferences.getString(PREFERENCE_KEY, ThemeHelper.DEFAULT_MODE)
        Log.d("change_theme", "onCreateApp :: $themePref")
        ThemeHelper.applyTheme(themePref!!)
    }
}
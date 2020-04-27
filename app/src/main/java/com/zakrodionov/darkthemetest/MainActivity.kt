package com.zakrodionov.darkthemetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import androidx.core.content.edit
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        val PREFERENCE_KEY = "preference_theme_key"
    }

    val preference by lazy { PreferenceManager.getDefaultSharedPreferences(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvChangeTheme.setOnClickListener {
            showThemeDialog()
        }
    }

    private fun showThemeDialog() {
        val themeList = resources.getStringArray(R.array.themeListArray)
        val themeEntry = resources.getStringArray(R.array.themeEntryArray)

        MaterialAlertDialogBuilder(this)
                .setTitle(R.string.preferences_theme)
                .setNegativeButton("Отмена", null)
                .setSingleChoiceItems(
                        themeList,
                        ThemeHelper.getThemeIndex(this, preference.getString(PREFERENCE_KEY, ThemeHelper.DEFAULT_MODE) ?: ThemeHelper.DEFAULT_MODE)
                ) { dialog, which ->
                    val theme = themeEntry[which]
                    preference.edit { putString(PREFERENCE_KEY, theme) }
                    ThemeHelper.applyTheme(theme)
                    Log.d("change_theme", "apply :: $theme")
                    dialog.dismiss()
                    //recreate() //оказывается было поломано в новой версии 1.2.0-beta01
                }
                .create()
                .show()
    }
}

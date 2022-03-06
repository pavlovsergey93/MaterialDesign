package com.gmail.pavlovsv93.materialdesign.view.theme

import android.content.Context
import android.content.SharedPreferences

class ThemeStorage(private val context: Context) {
    companion object {
        private val ARG_THEME = "ARG_THEME"
    }

    fun saveTheme(theme: Theme) {
        context.getSharedPreferences("Theme", Context.MODE_PRIVATE).let {
            it
                .edit()
                .putInt(ARG_THEME, theme.key)
                .apply()
        }
    }

    fun getTheme(): Theme {
        context.getSharedPreferences("Theme", Context.MODE_PRIVATE)
            ?.let { sp ->
                sp.getInt(ARG_THEME, Theme.GREY_THEME.key)
                    .let { key ->
                        enumValues<Theme>().forEach { theme ->
                            if (key == theme.key) {
                                return theme
                            }
                        }
                    }
            }
        return Theme.GREY_THEME
    }
}
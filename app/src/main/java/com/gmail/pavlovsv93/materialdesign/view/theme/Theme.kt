package com.gmail.pavlovsv93.materialdesign.view.theme

import com.gmail.pavlovsv93.materialdesign.R


enum class Theme(
    val theme: Int,
    val nameTheme:Int,
    val key: Int
) {
    GREY_THEME(R.style.myThemeGrey,R.string.grey_theme, 1),
    RED_THEME(R.style.myThemeRed,R.string.red_theme, 2),
    PURPLE_THEME(R.style.myThemePurple,R.string.purple_theme, 3)
}
package com.gmail.pavlovsv93.materialdesign.utils

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.gmail.pavlovsv93.materialdesign.R
import com.google.android.material.snackbar.Snackbar
import java.time.LocalDate
import java.time.format.DateTimeFormatter

// Показать SnackBar с кнопкой действия
fun View.showSnackBarAction(
    title: String,
    actionText: String?,
    action: (View) -> Unit,
    lenght: Int = Snackbar.LENGTH_INDEFINITE
) {
        Snackbar.make(this, title, lenght)
            .setAction(actionText, action)
            .show()
}
// Показать SnackBar без кнопки действия
fun View.showSnackBarNoAction(
    title: String,
    lenght: Int = Snackbar.LENGTH_INDEFINITE
) {
        Snackbar.make(this, title, lenght)
            .show()
}

@RequiresApi(Build.VERSION_CODES.O)
fun getDate(daysAgo: Int): String {
    var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    var date = LocalDate.now()
    var otherDate = LocalDate.parse(date.format(formatter), formatter)
        .minusDays(daysAgo.toLong())
    return otherDate.toString()
}

package com.gmail.pavlovsv93.materialdesign.utils

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.gmail.pavlovsv93.materialdesign.R
import com.google.android.material.snackbar.Snackbar

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
fun View.showSnackBarNoAction(
    title: String,
    lenght: Int = Snackbar.LENGTH_INDEFINITE
) {
        Snackbar.make(this, title, lenght)
            .show()
}

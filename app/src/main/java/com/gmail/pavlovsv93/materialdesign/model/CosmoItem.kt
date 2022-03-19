package com.gmail.pavlovsv93.materialdesign.model


import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class CosmoItem(
    @DrawableRes
    var image: Int? = null,

    @StringRes
    var nameCosmo: Int? = null
)
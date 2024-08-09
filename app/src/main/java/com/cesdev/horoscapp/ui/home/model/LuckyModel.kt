package com.cesdev.horoscapp.ui.home.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class LuckyModel(
    @DrawableRes val image:Int,
    @StringRes val prediction:Int,
)

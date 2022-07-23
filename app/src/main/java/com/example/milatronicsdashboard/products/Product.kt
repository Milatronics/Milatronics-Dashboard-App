package com.example.milatronicsdashboard.products

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Product(
    @StringRes val titleResourceId: Int,
    @DrawableRes val imageResourceId: Int,
    @StringRes val descriptionResourceId: Int,
    @StringRes val advantagesResourceId: MutableList<Int>,
)

package com.example.milatronicsdashboard.shrimpfarmingtips

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class Tip(
    @StringRes val titleResourceId: Int,
    @DrawableRes val imageResourceId: Int,
    @StringRes val descriptionResourceId: Int,
) : Parcelable

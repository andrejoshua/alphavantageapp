package com.alphavantage.app.domain.model.general

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Currency(
    val code: String,
    val name: String
) : Parcelable
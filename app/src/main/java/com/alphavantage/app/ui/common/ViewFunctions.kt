package com.alphavantage.app.ui.common

import android.view.View

fun View.setOnSafeClickListener(onSafeClick: (View) -> Unit) {
    val listener = SafeClickListener {
        onSafeClick(it)
    }

    setOnClickListener(listener)
}
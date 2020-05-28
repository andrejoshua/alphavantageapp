package com.alphavantage.app

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class AlphaVantageAppRunner : AndroidJUnitRunner() {

    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, AlphaVantageApp::class.java.name, context)
    }
}
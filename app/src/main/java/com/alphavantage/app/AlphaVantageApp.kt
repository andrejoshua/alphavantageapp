package com.alphavantage.app

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.multidex.MultiDexApplication
import com.alphavantage.app.data.di.dataModules
import com.alphavantage.app.data.local.ObjectBox
import com.alphavantage.app.di.navModules
import com.alphavantage.app.di.viewModelModules
import com.alphavantage.app.domain.di.domainModules
import com.alphavantage.app.nav.NavManager
import com.alphavantage.app.ui.currencies.CurrenciesFragment
import com.alphavantage.app.ui.currencies.CurrenciesFragmentArgs
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin
import timber.log.Timber

class AlphaVantageApp : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        ObjectBox.init(this)
        startKoin {
            androidContext(this@AlphaVantageApp)
            modules(dataModules + domainModules + navModules + viewModelModules)
        }
    }

    companion object {
        init {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        }
    }
}
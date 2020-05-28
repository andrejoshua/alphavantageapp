package com.alphavantage.app.di

import com.alphavantage.app.nav.NavManager
import org.koin.dsl.module

val navModules = module {
    single { NavManager() }
}
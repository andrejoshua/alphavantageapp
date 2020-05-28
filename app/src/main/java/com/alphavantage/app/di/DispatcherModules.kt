package com.alphavantage.app.di

import com.alphavantage.app.domain.widget.DefaultDispatcherProvider
import com.alphavantage.app.domain.widget.DispatcherProvider
import org.koin.dsl.module

val dispatcherModules = module {
    factory<DispatcherProvider> { DefaultDispatcherProvider() }
}
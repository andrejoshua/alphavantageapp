package com.alphavantage.app.domain.di.general

import com.alphavantage.app.domain.usecase.general.FetchCurrencies
import org.koin.dsl.module

val openApiModules = module(override = true) {
    single { FetchCurrencies(get()) }
}
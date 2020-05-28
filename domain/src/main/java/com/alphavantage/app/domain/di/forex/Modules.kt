package com.alphavantage.app.domain.di.forex

import com.alphavantage.app.domain.usecase.forex.CalculateExchangeRate
import org.koin.dsl.module

val generalModules = module(override = true) {
    single { CalculateExchangeRate(get()) }
}
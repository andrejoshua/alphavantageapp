package com.alphavantage.app.di

import com.alphavantage.app.di.submodule.currenciesViewModules
import com.alphavantage.app.di.submodule.exchangeRateViewModules

val viewModelModules = listOf(dispatcherModules, exchangeRateViewModules, currenciesViewModules)
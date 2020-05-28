package com.alphavantage.app.di.submodule

import com.alphavantage.app.ui.currencies.CurrenciesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val currenciesViewModules = module {
    viewModel { CurrenciesViewModel(get(), get(), get()) }
}
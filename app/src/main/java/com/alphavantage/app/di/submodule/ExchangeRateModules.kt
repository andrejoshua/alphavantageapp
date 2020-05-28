package com.alphavantage.app.di.submodule

import com.alphavantage.app.ui.exchangerate.ExchangeRateViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val exchangeRateViewModules = module {
    viewModel {
        ExchangeRateViewModel(
            get(),
            get(),
            get()
        )
    }
}
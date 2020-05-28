package com.alphavantage.app.domain.usecase.forex

import com.alphavantage.app.domain.model.Result
import com.alphavantage.app.domain.model.general.Currency
import com.alphavantage.app.domain.repository.forex.ForexRemoteRepository
import com.alphavantage.app.domain.usecase.UseCase
import com.alphavantage.app.domain.widget.DefaultDispatcherProvider
import com.alphavantage.app.domain.widget.DispatcherProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class CalculateExchangeRate(private val remote: ForexRemoteRepository) :
    UseCase() {

    fun execute(
        fromCurrency: Currency?,
        toCurrency: Currency?,
        input: Double?,
        dispatcherProvider: DispatcherProvider = DefaultDispatcherProvider()
    ): Flow<Result<Double?>> {
        if (fromCurrency == null) {
            return flow { emit(Result.error("Kurs asal kosong")) }
        }

        if (toCurrency == null) {
            return flow { emit(Result.error("Kurs tujuan kosong")) }
        }

        if (input == null) {
            return flow { emit(Result.error("Input kosong")) }
        }

        return retrieveNetwork(
            { remote.getExchangeRate(fromCurrency, toCurrency) },
            dispatcherProvider
        ).map {
            when (it.status) {
                Result.Status.LOADING -> Result.loading()
                Result.Status.SUCCESS -> Result.success(it.data!!.rate * input)
                Result.Status.ERROR -> Result.error(it.message!!)
            }
        }
    }
}
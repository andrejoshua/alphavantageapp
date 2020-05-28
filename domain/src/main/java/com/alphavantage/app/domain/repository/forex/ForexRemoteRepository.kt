package com.alphavantage.app.domain.repository.forex

import com.alphavantage.app.domain.model.Result
import com.alphavantage.app.domain.model.forex.ExchangeRate
import com.alphavantage.app.domain.model.general.Currency

interface ForexRemoteRepository {

    suspend fun getExchangeRate(
        fromCurrencyCode: Currency,
        toCurrencyCode: Currency
    ): Result<ExchangeRate>
}
package com.alphavantage.app.data.remote.implementation

import com.alphavantage.app.data.remote.api.ForexService
import com.alphavantage.app.data.remote.mapper.forex.ExchangeRateMapper
import com.alphavantage.app.domain.model.Result
import com.alphavantage.app.domain.model.forex.ExchangeRate
import com.alphavantage.app.domain.model.general.Currency
import com.alphavantage.app.domain.repository.forex.ForexRemoteRepository

class ForexRemoteRepositoryImplementation constructor(private val service: ForexService) :
    BaseImplementation(), ForexRemoteRepository {

    override suspend fun getExchangeRate(
        fromCurrencyCode: Currency,
        toCurrencyCode: Currency
    ): Result<ExchangeRate> {
        return getResult(
            { service.getExchangeRate(fromCurrencyCode.code, toCurrencyCode.code) },
            { ExchangeRateMapper.mapExchangeRate(it) })
    }
}
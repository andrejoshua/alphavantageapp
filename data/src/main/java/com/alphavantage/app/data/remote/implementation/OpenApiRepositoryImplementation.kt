package com.alphavantage.app.data.remote.implementation

import com.alphavantage.app.data.remote.api.OpenApiService
import com.alphavantage.app.data.remote.mapper.open.CurrencyMapper
import com.alphavantage.app.domain.model.Result
import com.alphavantage.app.domain.model.general.Currency
import com.alphavantage.app.domain.repository.open.OpenApiRepository

class OpenApiRepositoryImplementation(private val service: OpenApiService) : BaseImplementation(),
    OpenApiRepository {

    override suspend fun getAllCurrencies(): Result<List<Currency>> = getResult(
        { service.getCurrencies("https://openexchangerates.org/api/currencies.json") },
        { CurrencyMapper.mapCurrencies(it) })
}
package com.alphavantage.app.domain.repository.open

import com.alphavantage.app.domain.model.Result
import com.alphavantage.app.domain.model.general.Currency

interface OpenApiRepository {

    suspend fun getAllCurrencies() : Result<List<Currency>>
}
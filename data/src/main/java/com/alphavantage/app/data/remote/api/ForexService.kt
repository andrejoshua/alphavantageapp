package com.alphavantage.app.data.remote.api

import com.alphavantage.app.data.remote.response.forex.RealtimeExchangeRateResponse
import com.google.gson.JsonElement
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ForexService {

    @GET("query?function=CURRENCY_EXCHANGE_RATE")
    suspend fun getExchangeRate(
        @Query("from_currency") fromCurrency: String,
        @Query("to_currency") toCurrency: String
    ): Response<RealtimeExchangeRateResponse>

    @GET("query?function=FX_DAILY")
    suspend fun getDailyRate(
        @Query("from_symbol") fromCurrency: String,
        @Query("to_symbol") toCurrency: String
    ): Response<JsonElement>
}
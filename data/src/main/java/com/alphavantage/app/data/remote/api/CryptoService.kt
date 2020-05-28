package com.alphavantage.app.data.remote.api

import com.google.gson.JsonElement
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoService {

    @GET("query?function=DIGITAL_CURRENCY_MONTHLY")
    suspend fun getMonthlySeries(
        @Query("symbol") symbol: String,
        @Query("market") market: String
    ): Response<JsonElement>
}
package com.alphavantage.app.data.remote.response.forex

import com.google.gson.annotations.SerializedName

data class RealtimeExchangeRateResponse(
    @field:SerializedName("Realtime Currency Exchange Rate")
    val exchangeRateResponse: ExchangeRateResponse
)
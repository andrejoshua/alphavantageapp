package com.alphavantage.app.data.remote.response.forex

import com.google.gson.annotations.SerializedName

data class ExchangeRateResponse (
    @field:SerializedName("1. From_Currency Code")
    val fromCurrencyCode: String,
    @field:SerializedName("2. From_Currency Name")
    val fromCurrencyName: String,
    @field:SerializedName("3. To_Currency Code")
    val toCurrencyCode: String,
    @field:SerializedName("4. To_Currency Name")
    val toCurrencyName: String,
    @field:SerializedName("5. Exchange Rate")
    val exchangeRate: String,
    @field:SerializedName("6. Last Refreshed")
    val lastRefreshed: String,
    @field:SerializedName("7. Time Zone")
    val timeZone: String,
    @field:SerializedName("8. Bid Price")
    val bidPrice: String,
    @field:SerializedName("9. Ask Price")
    val askPrice: String
)
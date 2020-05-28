package com.alphavantage.app.domain.model.forex

import java.util.*

data class ExchangeRate(
    val rate: Double,
    val bidPrice: Double,
    val askPrice: Double,
    val lastRefreshedDate: Date?
)
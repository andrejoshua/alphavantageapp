package com.alphavantage.app.data.local.`object`.forex

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.annotation.NameInDb
import java.util.*

@Entity
data class ExchangeRateEntity (
    @Id
    var id: Long = 0,
    @NameInDb("currency_from")
    var currencyFrom: String,
    @NameInDb("currency_to")
    var currencyTo: String,
    @NameInDb("exchange_rate")
    var exchangeRate: Double,
    @NameInDb("bid_price")
    var bidPrice: Double,
    @NameInDb("ask_price")
    var askPrice: Double,
    @NameInDb("last_refreshed")
    var lastRefreshed: Date?
)
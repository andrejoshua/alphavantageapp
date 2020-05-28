package com.alphavantage.app.data.remote.mapper.forex

import com.alphavantage.app.data.remote.response.forex.RealtimeExchangeRateResponse
import com.alphavantage.app.domain.model.forex.ExchangeRate
import com.alphavantage.app.domain.util.parseToDate

class ExchangeRateMapper {

    companion object {

        fun mapExchangeRate(response: RealtimeExchangeRateResponse): ExchangeRate {
            val lastDate = response.exchangeRateResponse.lastRefreshed.parseToDate(
                response.exchangeRateResponse.timeZone
            )
            return ExchangeRate(
                response.exchangeRateResponse.exchangeRate.toDouble(),
                response.exchangeRateResponse.bidPrice.toDouble(),
                response.exchangeRateResponse.askPrice.toDouble(),
                lastDate
            )
        }
    }
}
package com.alphavantage.app.data.local.mapper.forex

import com.alphavantage.app.data.local.`object`.forex.ExchangeRateEntity
import com.alphavantage.app.domain.model.general.Currency
import com.alphavantage.app.domain.model.forex.ExchangeRate

class ExchangeRateMapper {

    companion object {

        fun mapExchangeRate(entity: ExchangeRateEntity) : ExchangeRate {
            return ExchangeRate(
                entity.exchangeRate,
                entity.bidPrice,
                entity.askPrice,
                entity.lastRefreshed
            )
        }

        fun mapExchangeRate(model: ExchangeRate, fromCurrency: Currency, toCurrency: Currency) : ExchangeRateEntity {

            return ExchangeRateEntity(
                currencyFrom = fromCurrency.code,
                currencyTo = toCurrency.code,
                exchangeRate = model.rate,
                bidPrice = model.bidPrice,
                askPrice = model.askPrice,
                lastRefreshed = model.lastRefreshedDate
            )
        }
    }
}
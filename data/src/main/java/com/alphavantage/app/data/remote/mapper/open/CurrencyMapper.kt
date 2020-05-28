package com.alphavantage.app.data.remote.mapper.open

import com.alphavantage.app.domain.model.general.Currency
import com.google.gson.JsonElement
import com.google.gson.JsonObject

class CurrencyMapper {

    companion object {

        fun mapCurrencies(response: JsonElement) : List<Currency> {
            val obj: JsonObject = response.asJsonObject
            return obj.entrySet()
                .map {
                    Currency(
                        code = it.key,
                        name = it.value.asString
                    ) }
        }
    }
}
package com.alphavantage.app.domain.usecase.general

import com.alphavantage.app.domain.repository.open.OpenApiRepository
import com.alphavantage.app.domain.usecase.UseCase
import com.alphavantage.app.domain.widget.DefaultDispatcherProvider
import com.alphavantage.app.domain.widget.DispatcherProvider

class FetchCurrencies(private val remote: OpenApiRepository) : UseCase() {

    fun execute(
        dispatcherProvider: DispatcherProvider = DefaultDispatcherProvider()
    ) = retrieveNetwork(
        { remote.getAllCurrencies() },
        dispatcherProvider
    )
}
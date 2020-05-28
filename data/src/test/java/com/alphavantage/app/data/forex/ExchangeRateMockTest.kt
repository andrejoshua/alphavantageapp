package com.alphavantage.app.data.forex

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.Assert.assertEquals
import org.junit.Test

class ExchangeRateMockTest {

    @Test
    fun testRetrofit() {
        val server = MockWebServer()
        server.dispatcher = object : Dispatcher() {

            override fun dispatch(request: RecordedRequest): MockResponse {
                return when (request.path) {
                    "query?function=CURRENCY_EXCHANGE_RATE&from_currency=USD&to_currency=JPY&apikey=demo" ->
                        MockResponse()
                            .setResponseCode(200)
                            .addHeader("Cache-control", "no-cache")
                            .setBody(exchangeRateResponse)
                    else ->
                        MockResponse()
                            .setResponseCode(404)
                }
            }
        }

        server.start(1337)

        val request = server.takeRequest()
        assertEquals(
            "/query?function=CURRENCY_EXCHANGE_RATE&from_currency=USD&to_currency=JPY&apikey=demo",
            request.path
        )

        server.shutdown()
    }

    private val exchangeRateResponse = """
        {
            "Realtime Currency Exchange Rate": {
                "1. From_Currency Code": "USD",
                "2. From_Currency Name": "United States Dollar",
                "3. To_Currency Code": "JPY",
                "4. To_Currency Name": "Japanese Yen",
                "5. Exchange Rate": "109.55000000",
                "6. Last Refreshed": "2019-11-29 10:18:07",
                "7. Time Zone": "UTC",
                "8. Bid Price": "109.55000000",
                "9. Ask Price": "109.55000000"
            }
        }
    """.trimIndent()
}
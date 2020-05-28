package com.alphavantage.app.data.open

import com.alphavantage.app.data.networkModule
import com.alphavantage.app.data.remote.api.OpenApiService
import com.alphavantage.app.data.remote.implementation.OpenApiRepositoryImplementation
import com.alphavantage.app.domain.model.Result
import com.alphavantage.app.domain.model.general.Currency
import com.google.gson.JsonParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response
import retrofit2.Retrofit

@RunWith(MockitoJUnitRunner::class)
class CurrenciesRepositoryTest : KoinTest {

    private val retrofit by inject<Retrofit>()
    private lateinit var service: OpenApiService
    private val mainThreadSurrogate = newSingleThreadContext("UI Thread")

    @Before
    fun setUp() {
        startKoin {
            modules(networkModule)
        }

        service = retrofit.create(OpenApiService::class.java)
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @Test
    fun testLocal() {
        val res = runBlocking {
            val mockElement = JsonParser.parseString(mockCurrenciesJson)
            val mockResponse = Response.success(mockElement)
            val mockService = Mockito.mock(OpenApiService::class.java)

            `when`(mockService.getCurrencies("https://openexchangerates.org/api/currencies.json")).thenReturn(mockResponse)
            val repository = OpenApiRepositoryImplementation(mockService)
            repository.getAllCurrencies()
        }

        assertEquals(Result.Status.SUCCESS, res.status)
        assertEquals(mockList, res.data)
    }

    @Test
    fun testRemote() {
        val repository = OpenApiRepositoryImplementation(service)

        val res = runBlocking {
            repository.getAllCurrencies()
        }

        assertEquals(Result.Status.SUCCESS, res.status)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()

        stopKoin()
    }

    private val mockList = listOf(
        Currency("AED", "United Arab Emirates Dirham"),
        Currency("AFN", "Afghan Afghani")
    )

    private val mockCurrenciesJson = """
        {
          "AED": "United Arab Emirates Dirham",
          "AFN": "Afghan Afghani"
        }
    """.trimIndent()
}
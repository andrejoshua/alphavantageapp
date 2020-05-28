package com.alphavantage.app.open

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.alphavantage.app.domain.model.Result
import com.alphavantage.app.getOrAwaitValue
import com.alphavantage.app.rule.CoroutinesTestRule
import com.alphavantage.app.rule.runBlockingTest
import com.alphavantage.app.testModules
import com.alphavantage.app.ui.currencies.CurrenciesViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject
import org.koin.test.mock.declare

@RunWith(JUnit4::class)
class CurrenciesViewModelTest : AutoCloseKoinTest() {

    @get:Rule
    val executorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesRule = CoroutinesTestRule()

    private val viewModel: CurrenciesViewModel by inject()

    @Before
    fun setUp() {
        startKoin {
            modules(testModules)
        }

        declare { factory { coroutinesRule.testDispatcherProvider } }
    }

    @Test
    fun testGetCurrencies() {
        coroutinesRule.runBlockingTest {
            viewModel.getItems()

            val results2 = viewModel.items.getOrAwaitValue()
            assertEquals(Result.Status.SUCCESS, results2.status)
        }
    }
}
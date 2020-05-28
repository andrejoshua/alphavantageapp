package com.alphavantage.app.fragments.forex

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.alphavantage.app.R
import com.alphavantage.app.addons.createRule
import com.alphavantage.app.addons.testModules
import com.alphavantage.app.ui.exchangerate.ExchangeRateFragment
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.startKoin

@RunWith(AndroidJUnit4ClassRunner::class)
class ExchangeRateFragmentTest {

    private val fragment =
        ExchangeRateFragment()

    @get:Rule
    val fragmentRule = createRule(fragment)

    @Before
    fun setUp() {
        startKoin {
            modules(testModules)
        }


    }

    @Test
    fun getExchangeRate() {
        onView(withId(R.id.priceInputEdit)).perform(typeText("1"))
        onView(withId(R.id.fromEditText)).perform(typeText("JPY"))
        onView(withId(R.id.toEditText)).perform(typeText("IDR"))

        onView(withId(R.id.calculateButton)).perform(click())
        onView(withId(R.id.priceOutputText)).check(matches(withText("129.18")))
    }
}
package com.alphavantage.app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavArgsLazy
import androidx.navigation.fragment.findNavController
import com.alphavantage.app.R
import com.alphavantage.app.nav.NavManager
import com.alphavantage.app.ui.currencies.CurrenciesFragment
import com.alphavantage.app.ui.currencies.CurrenciesFragmentArgs
import com.alphavantage.app.ui.exchangerate.ExchangeRateFragment
import com.alphavantage.app.ui.exchangerate.ExchangeRateFragmentArgs
import com.alphavantage.app.util.getNavArgsInstance
import kotlinx.android.synthetic.main.main_activity.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val navController get() = container.findNavController()

    private val navManager: NavManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        initNavManager()
        handleActivity()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ExchangeRateFragment.newInstance())
                .commitNow()
        }
    }

    private fun initNavManager() {
        navManager.setOnNavEvent {
            navController.navigate(it)
        }
    }

    private fun handleActivity() {
        supportFragmentManager.registerFragmentLifecycleCallbacks(object : FragmentManager.FragmentLifecycleCallbacks() {
            override fun onFragmentCreated(
                fm: FragmentManager,
                f: Fragment,
                savedInstanceState: Bundle?
            ) {
                super.onFragmentCreated(fm, f, savedInstanceState)

                navManager.setArgsEvent(f::getNavArgsInstance)
            }
        }, true)
    }
}

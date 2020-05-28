package com.alphavantage.app.addons

import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.test.rule.ActivityTestRule
import com.alphavantage.app.R
import com.alphavantage.app.ui.MainActivity

abstract class FragmentTestRule<T : Fragment> :
    ActivityTestRule<MainActivity>(MainActivity::class.java, true) {

    override fun afterActivityLaunched() {
        super.afterActivityLaunched()
        activity.runOnUiThread {
            val fm = activity.supportFragmentManager
            val transaction = fm.beginTransaction()
            transaction.replace(
                R.id.container,
                createFragment()
            ).commit()
        }
    }

    protected abstract fun createFragment(): T

    fun launch() {
        launchActivity(Intent())
    }
}

fun <T : Fragment> createRule(fragment: T): FragmentTestRule<T> =
    object : FragmentTestRule<T>() {
        override fun createFragment(): T = fragment
    }
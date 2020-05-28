package com.alphavantage.app.nav

import androidx.navigation.NavArgs
import androidx.navigation.NavDirections

class NavManager {

    private var navEventListener: ((navDirections: NavDirections) -> Unit)? = null
    private var argsEventListener: (() -> NavArgs?)? = null

    fun navigate(navDirections: NavDirections) {
        navEventListener?.invoke(navDirections)
    }

    fun getArgs(): NavArgs? = argsEventListener?.invoke()

    fun setOnNavEvent(navEventListener: (navDirections: NavDirections) -> Unit) {
        this.navEventListener = navEventListener
    }

    fun setArgsEvent(eventListener: () -> NavArgs?) {
        this.argsEventListener = eventListener
    }
}
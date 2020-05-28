package com.alphavantage.app.util

import androidx.fragment.app.Fragment
import androidx.navigation.NavArgs
import androidx.navigation.NavArgsLazy
import kotlin.reflect.KClass

fun Fragment.getNavArgsInstance(): NavArgs? {
    val arguments = arguments ?: return null

    val set = arguments.keySet()
    if (set.isEmpty())
        return null

    // SafeArgs plugin adds "Agrs" suffix to the fragment class eg.
    // If com.abc.MyFragment class has arguments defined in nav_graph.xml then
    // class com.abc.MyFragmentArgs will be generated.
    val safeArgsClassSuffix = "Args"
    val className = "${this::class.java.canonicalName}$safeArgsClassSuffix"

    @Suppress("UNCHECKED_CAST")
    val navArgsClass = requireNotNull(getArgNavClass(className)) {
        // This may happen when nav graph resource does not define arguments for particular fragment
        "Fragment $className has arguments, but corresponding navArgs class $className does not exist."
    }

    // Let's check if Args class actually exists
    val navArgs by NavArgsLazy(navArgsClass) { arguments }
    return navArgs
}

private fun getArgNavClass(className: String): KClass<NavArgs>? = try {
    @Suppress("UNCHECKED_CAST")
    Class.forName(className).kotlin as KClass<NavArgs>
} catch (e: ClassNotFoundException) {
    null
}
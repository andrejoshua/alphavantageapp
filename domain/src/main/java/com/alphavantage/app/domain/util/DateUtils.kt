package com.alphavantage.app.domain.util

import java.text.SimpleDateFormat
import java.util.*

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
fun String.parseToDate(timezoneString: String?, pattern: String = "yyyy-MM-dd HH:mm:ss"): Date {
    val formatter = SimpleDateFormat(pattern, Locale.getDefault())
    if (timezoneString != null)
        formatter.timeZone = TimeZone.getTimeZone(timezoneString)
    return formatter.parse(this)
}
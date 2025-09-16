package io.github.yuu0922.lrinsight.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun formatDate(date: Date, pattern: String = "yyyy/MM/dd"): String {
    val sdf = SimpleDateFormat(pattern, Locale.getDefault())
    return sdf.format(date)
}

fun parseIsoDateToDate(isoString: String): Date? {
    return try {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
        sdf.timeZone = TimeZone.getTimeZone("UTC")
        sdf.parse(isoString)
    } catch (e: Exception) {
        null
    }
}
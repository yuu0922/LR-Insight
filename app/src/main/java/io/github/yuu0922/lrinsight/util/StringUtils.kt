package io.github.yuu0922.lrinsight.util

object StringUtils {
    fun String.addCommas(): String {
        return this.toLongOrNull()?.let { "%,d".format(it) } ?: this
    }
}
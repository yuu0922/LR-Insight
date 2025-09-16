package io.github.yuu0922.lrinsight.util

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.net.toUri

object AppUtils {
    fun openUrl(context: Context, url: String, useCustomTabs: Boolean) {
        if (useCustomTabs) {
            val customTabsIntent = CustomTabsIntent.Builder().build()
            customTabsIntent.launchUrl(context, url.toUri())
        } else {
            val intent = Intent(Intent.ACTION_VIEW, url.toUri())
            try {
                context.startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                e.printStackTrace()
            }
        }
    }

    fun getAppUserAgent(context: Context): String {
        val versionName = context.packageManager.getPackageInfo(context.packageName, 0).versionName
        return "LR-Insight/${versionName}"
    }
}
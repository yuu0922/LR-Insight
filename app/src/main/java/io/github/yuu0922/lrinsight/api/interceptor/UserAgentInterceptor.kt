package io.github.yuu0922.lrinsight.api.interceptor

import android.content.Context
import io.github.yuu0922.lrinsight.util.AppUtils.getAppUserAgent
import okhttp3.Interceptor
import okhttp3.Response

class UserAgentInterceptor(
    private val appContext: Context
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequest = request.newBuilder()
            .addHeader("User-Agent", getAppUserAgent(appContext))
        return chain.proceed(newRequest.build())
    }
}
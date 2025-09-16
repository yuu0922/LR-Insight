package io.github.yuu0922.lrinsight.api.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class UserAgentInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequest = request.newBuilder()
            .addHeader("User-Agent", "")
        return chain.proceed(newRequest.build())
    }
}
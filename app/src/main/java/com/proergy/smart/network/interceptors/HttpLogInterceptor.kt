package com.proergy.smart.network.interceptors

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class HttpLogInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        val bodyStr = response.peekBody(Long.MAX_VALUE).string()

        Log.d("HTTP",
            """
            ${request.method} ${request.url}
            Response: ${response.code}
            Body: $bodyStr
            """.trimIndent()
        )

        return response
    }
}

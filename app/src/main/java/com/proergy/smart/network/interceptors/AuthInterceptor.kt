package com.proergy.smart.network.interceptors

import android.util.Base64
import com.proergy.smart.apis.SkipAuth
import com.proergy.smart.network.TokenManager
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.Invocation

const val OAUTH2_MOBILE_CLIENT = "app:app"
val encoded: String? =
    Base64.encodeToString(OAUTH2_MOBILE_CLIENT.toByteArray(Charsets.UTF_8), Base64.NO_WRAP)
val BasicAuth: String = "Basic $encoded"

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val skipAuth = request.tag(Invocation::class.java)
            ?.method()
            ?.getAnnotation(SkipAuth::class.java) != null

        if (skipAuth) {
            return chain.proceed(request.newBuilder().addHeader("Authorization", BasicAuth).build())
        }

        val token = TokenManager.token

        val newRequest = if (!token.isNullOrEmpty()) {
            request.newBuilder()
                .removeHeader("skipAuth")
                .addHeader("Authorization", "Bearer $token")
                .build()
        } else {
            request
        }

        return chain.proceed(newRequest)
    }
}

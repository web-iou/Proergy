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
        val newRequest = request.newBuilder()
        newRequest.addHeader("RSC","app").addHeader("LNG","zh-CN")
        if (skipAuth) {
            newRequest.removeHeader("skipAuth")
        } else {
            val token = TokenManager.token
            if (token.isNullOrEmpty()) {
                newRequest
                    .addHeader("Authorization", "Bearer $token")
            } else {
                newRequest.addHeader("Authorization", BasicAuth)
            }
        }

        return chain.proceed(newRequest.build())
    }
}

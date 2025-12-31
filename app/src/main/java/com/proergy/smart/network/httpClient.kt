package com.proergy.smart.network

import com.proergy.smart.BuildConfig
import com.proergy.smart.network.interceptors.AuthInterceptor
import com.proergy.smart.network.interceptors.HttpLogInterceptor
import com.proergy.smart.network.interceptors.ResponseInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
const val timeout= 10L
object HttpClient {
     private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor()) // 自动鉴权
            .addInterceptor(ResponseInterceptor())
            .addInterceptor(HttpLogInterceptor())
            .connectTimeout(timeout, TimeUnit.SECONDS)
            .build()
    }

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    inline fun <reified T> service(): T = retrofit.create(T::class.java)
}

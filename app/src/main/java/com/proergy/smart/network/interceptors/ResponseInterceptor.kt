package com.proergy.smart.network.interceptors

import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.proergy.smart.Application
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class ResponseInterceptor: Interceptor {
    private val mainHandler = Handler(Looper.getMainLooper())
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        val body = response.body
        if (!response.isSuccessful) {
            // 你可以在这里统一处理
            // 比如 401 / 403 / 500
        }else {
            val source = body.source()
            source.request(Long.MAX_VALUE)
            val buffer = source.buffer.clone()
            val json = buffer.readUtf8()
            val gson = Gson()
            val base = gson.fromJson(json, JsonObject::class.java)
            val code = base["code"]?.asInt ?: 0

            if (code != 0) {
                val msg = base["msg"]?.asString ?: "Unknown error"
                mainHandler.post {
                    Toast.makeText(
                        Application.appContext,
                        msg,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }
        val contentType = body.contentType()
        val bodyString = body.string()
        return response.newBuilder()
            .body(bodyString.toResponseBody(contentType))
            .build()
    }
}
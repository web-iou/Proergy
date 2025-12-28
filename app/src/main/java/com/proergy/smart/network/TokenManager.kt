package com.proergy.smart.network

import com.tencent.mmkv.MMKV

object TokenManager {

    private const val KEY_TOKEN = "auth_token"

    private val mmkv by lazy {
        MMKV.defaultMMKV()
    }

    // 内存态（应用生命周期内）
    @Volatile
    var token: String? = null
        private set

    fun init() {
        token = mmkv.decodeString(KEY_TOKEN)
    }

    fun saveToken(token: String) {
        this.token = token
        mmkv.encode(KEY_TOKEN, token)
    }

    fun clearToken() {
        token = null
        mmkv.remove(KEY_TOKEN)
    }

    fun isLogin(): Boolean {
        return !token.isNullOrEmpty()
    }
}

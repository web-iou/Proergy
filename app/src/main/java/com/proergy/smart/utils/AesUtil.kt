package com.proergy.smart.utils

import android.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object AesUtil {
    const val KEY= "thanks,tsc2cloud"
    private const val TRANSFORMATION = "AES/CFB/NoPadding"
    fun encrypt(content: String,keywords: String=KEY): String {
        val keyBytes = keywords.toByteArray(Charsets.UTF_8)
        val keySpec = SecretKeySpec(keyBytes, "AES")
        val cipher = Cipher.getInstance(TRANSFORMATION)
        val ivSpec = IvParameterSpec(keyBytes)
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec)
        val encrypted = cipher.doFinal(content.toByteArray())
        return Base64.encodeToString(encrypted, Base64.NO_WRAP)
    }
}

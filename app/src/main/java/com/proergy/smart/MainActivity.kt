package com.proergy.smart

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.proergy.smart.network.TokenManager
import com.tencent.mmkv.MMKV

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MMKV.initialize(this)
        TokenManager.init()
        setContentView(R.layout.activity_main)
    }
}

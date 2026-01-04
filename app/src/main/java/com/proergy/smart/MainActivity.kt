package com.proergy.smart

import android.os.Build
import android.os.Bundle
import android.view.ViewGroup
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.proergy.smart.network.TokenManager
import com.proergy.smart.ui.SplashView
import com.tencent.mmkv.MMKV

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MMKV.initialize(this)
        TokenManager.init()
        setContentView(R.layout.activity_main)
        val splashView = SplashView(this)
        val layoutParams = CoordinatorLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        val root = findViewById<CoordinatorLayout>(R.id.root)
        root.addView(splashView,layoutParams)
    }

    fun setStatusBarVisible(open: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val controller = window.insetsController ?: return
            if (open) {
                controller.show(WindowInsets.Type.statusBars())
            } else {
                controller.hide(WindowInsets.Type.statusBars())
            }
        } else {
            @Suppress("DEPRECATION")
            if (open) {
                window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            } else {
                window.setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
                )
            }
        }
    }
}

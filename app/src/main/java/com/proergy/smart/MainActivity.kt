package com.proergy.smart

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Build
import android.os.Bundle
import android.view.ViewGroup
import android.view.WindowInsets
import android.view.WindowManager
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.lifecycleScope
import com.proergy.smart.apis.Http.common
import com.proergy.smart.network.TokenManager
import com.proergy.smart.ui.SplashView
import com.proergy.smart.ui.pages.AdsPreview
import com.tencent.mmkv.MMKV
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Splash)
        super.onCreate(savedInstanceState)
        MMKV.initialize(this)
        TokenManager.init()
        setContentView(R.layout.activity_main)
        val splashView = SplashView(this)
        val layoutParams = CoordinatorLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        val overlay = findViewById<FrameLayout>(R.id.overlay_container)
        overlay.addView(splashView,layoutParams)
        splashView.startAnimate{
            splashView.animate()
                .alpha(0f)
                .setDuration(500)
                .setInterpolator(AccelerateDecelerateInterpolator())
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        // 动画结束后移除视图
                        overlay.removeView(splashView)
                    }
                })
                .start()
        }
        val adsPreview= AdsPreview()
        lifecycleScope.launch {
            val response= common.getAds().data
        }
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

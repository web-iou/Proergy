package com.proergy.smart.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.proergy.smart.R

class SplashView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context)
            .inflate(R.layout.splash_screen, this, true)
        startAnimate()
    }

    fun startAnimate() {
        // findViewById / ViewBinding
        val logo = findViewById<ImageView>(R.id.logo)
        val alphaAnimator = ObjectAnimator.ofFloat(
            logo,                // 动画作用的目标View
            "alpha",             // 要操作的属性名（透明度属性）
            0f,                  // 动画起始值
            1f                   // 动画结束值
        ).apply {
            duration = 1500 // 动画时长，单位：毫秒（1500ms=1.5秒）
            startDelay = 300 // 延迟多久开始动画，单位：毫秒
            interpolator = AccelerateDecelerateInterpolator() // 插值器（先加速后减速，更自然）
            addListener(object : AnimatorListenerAdapter(){
                override fun onAnimationEnd(animation: Animator) {
                    this@SplashView.animate()
                        .alpha(0f)
                        .setDuration(500)
                        .setInterpolator(AccelerateDecelerateInterpolator())
                        .setListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator) {
                                // 动画结束后移除视图
                                (parent as? ViewGroup)?.removeView(this@SplashView)
                            }
                        })
                        .start()
                }
            })
        }
        alphaAnimator.start()
        val linearImage= ImageView(context)
    }
}

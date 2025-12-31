package com.proergy.smart

import android.app.Application
import android.content.Context

class Application: Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = this
    }

    companion object {
        lateinit var appContext: Context
            private set
    }
}
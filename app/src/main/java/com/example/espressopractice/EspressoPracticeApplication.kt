package com.example.espressopractice

import android.app.Application
import timber.log.Timber

class EspressoPracticeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}

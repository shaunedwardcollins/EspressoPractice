package com.example.espressopractice

import android.util.Log
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import timber.log.Timber

class TimberRule : TestWatcher() {
    private val tree = object : Timber.DebugTree() {
        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
            val level = when (priority) {
                Log.VERBOSE -> "[VERBOSE]"
                Log.DEBUG -> "[DEBUG]"
                Log.INFO -> "[INFO]"
                Log.WARN -> "[WARN]"
                Log.ERROR -> "[ERROR]"
                Log.ASSERT -> "[ASSERT]"
                else -> "[LOG]"
            }
            // For unit tests, println will show up in the console
            println("${System.currentTimeMillis()} [$tag] [TEST] $level $message")
            t?.printStackTrace()
        }
    }

    override fun starting(description: Description) {
        Timber.plant(tree)
    }

    override fun finished(description: Description) {
        Timber.uproot(tree)
    }
}

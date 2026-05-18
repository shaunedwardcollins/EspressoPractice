package com.example.espressopractice

import android.util.Log
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import timber.log.Timber

/**
 * A JUnit Rule for Instrumentation tests that ensures Timber is initialized.
 * Since the Application class already plants a DebugTree in debug builds,
 * this rule is mainly useful for ensuring consistent logging behavior
 * or adding additional trees for test reporting.
 */
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
            super.log(priority, tag, "[TEST] $level $message", t)
        }
    }

    override fun starting(description: Description) {
        // Uproot existing trees to avoid duplicate logs if the App class already planted one
        Timber.uprootAll()
        Timber.plant(tree)
        Timber.i("--- TEST START: ${description.methodName}")
    }

    override fun finished(description: Description) {
        Timber.i("--- TEST END: ${description.methodName}")
        Timber.uproot(tree)
    }
}

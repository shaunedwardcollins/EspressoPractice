package com.example.espressopractice

import android.content.Context
import android.content.SharedPreferences

/**
 * A simple manager to handle authentication state for practice purposes.
 */
object AuthManager {
    private const val PREFS_NAME = "auth_prefs"
    private const val KEY_IS_LOGGED_IN = "is_logged_in"

    private fun getPrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun isLoggedIn(context: Context): Boolean {
        return getPrefs(context).getBoolean(KEY_IS_LOGGED_IN, false)
    }

    fun login(context: Context) {
        getPrefs(context).edit().putBoolean(KEY_IS_LOGGED_IN, true).apply()
    }

    fun logout(context: Context) {
        getPrefs(context).edit().putBoolean(KEY_IS_LOGGED_IN, false).apply()
    }
}

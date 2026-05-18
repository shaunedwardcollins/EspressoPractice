package com.example.espressopractice.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.espressopractice.R
import timber.log.Timber

class LoginPage {

    private fun usernameField() = onView(withId(R.id.etUsername))
    private fun passwordField() = onView(withId(R.id.etPassword))
    private fun loginButton() = onView(withId(R.id.btnLogin))
    private fun loginTitle() = onView(withId(R.id.tvLoginTitle))

    fun verifyPageLoaded(): LoginPage {
        Timber.d("locating element: loginTitle")
        try {
            loginTitle().check(matches(isDisplayed()))
            loginTitle().check(matches(withText(R.string.login_title)))
        } catch (e: Throwable) {
            Timber.e("FAILED to verify element: loginTitle")
            throw e
        }
        return this
    }

    fun enterUsername(username: String): LoginPage {
        Timber.i("typing username: $username")
        try {
            usernameField().perform(typeText(username), closeSoftKeyboard())
        } catch (e: Throwable) {
            Timber.e("FAILED to type username")
            throw e
        }
        return this
    }

    fun enterPassword(password: String): LoginPage {
        Timber.i("typing password")
        try {
            passwordField().perform(typeText(password), closeSoftKeyboard())
        } catch (e: Throwable) {
            Timber.e("FAILED to type password")
            throw e
        }
        return this
    }

    fun clickLogin(): LoginPage {
        Timber.i("clicking login button")
        try {
            loginButton().perform(click())
        } catch (e: Throwable) {
            Timber.e("FAILED to click login button")
            throw e
        }
        return this
    }
}

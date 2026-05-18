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
    private fun errorMessage() = onView(withId(R.id.tvErrorMessage))

    fun verifyPageLoaded(): LoginPage {
        try {
            Timber.d("located element: loginTitle")
            loginTitle().check(matches(isDisplayed()))
            loginTitle().check(matches(withText(R.string.login_title)))
        } catch (e: Throwable) {
            Timber.e("FAILED to verify element: loginTitle")
            throw e
        }
        return this
    }

    fun verifyErrorDisplayed(): LoginPage {
        try {
            Timber.d("locating element: errorMessage")
            errorMessage().check(matches(isDisplayed()))
            errorMessage().check(matches(withText(R.string.error_invalid_credentials)))
        } catch (e: Throwable) {
            Timber.e("FAILED to verify error message display")
            throw e
        }
        return this
    }

    fun enterUsername(username: String): LoginPage {
        try {
            Timber.i("typed username: $username")
            usernameField().perform(typeText(username), closeSoftKeyboard())
        } catch (e: Throwable) {
            Timber.e("FAILED to type username")
            throw e
        }
        return this
    }

    fun enterPassword(password: String): LoginPage {
        try {
            Timber.i("typed password: $password")
            passwordField().perform(typeText(password), closeSoftKeyboard())
        } catch (e: Throwable) {
            Timber.e("FAILED to type password")
            throw e
        }
        return this
    }

    fun clickLogin(): LoginPage {
        try {
            Timber.i("clicked login button")
            loginButton().perform(click())
        } catch (e: Throwable) {
            Timber.e("FAILED to click login button")
            throw e
        }
        return this
    }
}

package com.example.espressopractice.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.espressopractice.R
import timber.log.Timber

class LandingPage {

    private fun title() = onView(withId(R.id.tvTitle))
    private fun simpleUiButton() = onView(withId(R.id.btnSimpleUI))

    fun verifyTitleIsDisplayed(): LandingPage {
        Timber.d("locating element: title")
        try {
            title().check(matches(isDisplayed()))
        } catch (e: Throwable) {
            Timber.e("TEST FAILED to verify element: title is not displayed")
            throw e
        }
        return this
    }

    fun verifyTitleText(): LandingPage {
        Timber.d("locating element: title")
        try {
            title().check(matches(withText(R.string.landing_title)))
        } catch (e: Throwable) {
            Timber.e("TEST FAILED to verify element: title text mismatch")
            throw e
        }
        return this
    }

    fun verifyButtonIsDisplayed(): LandingPage {
        Timber.d("locating element: simpleUiButton")
        try {
            simpleUiButton().check(matches(isDisplayed()))
        } catch (e: Throwable) {
            Timber.e("TEST FAILED to verify element: simpleUiButton is not displayed")
            throw e
        }
        return this
    }

    fun verifyButtonText(): LandingPage {
        Timber.d("locating element: simpleUiButton")
        try {
            simpleUiButton().check(matches(withText(R.string.btn_simple_ui)))
        } catch (e: Throwable) {
            Timber.e("TEST FAILED to verify element: simpleUiButton text mismatch")
            throw e
        }
        return this
    }
}

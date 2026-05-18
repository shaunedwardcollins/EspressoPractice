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
        try {
            Timber.d("located element: title")
            title().check(matches(isDisplayed()))
        } catch (e: Throwable) {
            Timber.e("FAILED: title is not displayed")
            throw e
        }
        return this
    }

    fun verifyTitleText(): LandingPage {
        try {
            Timber.d("located element: title")
            title().check(matches(withText(R.string.landing_title)))
        } catch (e: Throwable) {
            Timber.e("FAILED: title text mismatch")
            throw e
        }
        return this
    }

    fun verifyButtonIsDisplayed(): LandingPage {
        try {
            Timber.d("located element: simpleUiButton")
            simpleUiButton().check(matches(isDisplayed()))
        } catch (e: Throwable) {
            Timber.e("FAILED: simpleUiButton is not displayed")
            throw e
        }
        return this
    }

    fun verifyButtonText(): LandingPage {
        try {
            Timber.d("located element: simpleUiButton")
            simpleUiButton().check(matches(withText(R.string.btn_simple_ui)))
        } catch (e: Throwable) {
            Timber.e("FAILED: simpleUiButton text mismatch")
            throw e
        }
        return this
    }
}

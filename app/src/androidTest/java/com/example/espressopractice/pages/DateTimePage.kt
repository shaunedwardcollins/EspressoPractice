package com.example.espressopractice.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.PickerActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.espressopractice.R
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.containsString
import timber.log.Timber
import android.widget.DatePicker
import android.widget.TimePicker

class DateTimePage {

    private fun dateButton() = onView(withId(R.id.btnOpenDatePicker))
    private fun timeButton() = onView(withId(R.id.btnOpenTimePicker))
    private fun dateResult() = onView(withId(R.id.tvSelectedDate))
    private fun timeResult() = onView(withId(R.id.tvSelectedTime))

    fun verifyPageLoaded(): DateTimePage {
        try {
            Timber.d("located element: dateTimeTitle")
            onView(withId(R.id.tvDateTimeTitle)).check(matches(isDisplayed()))
        } catch (e: Throwable) {
            Timber.e("FAILED to verify element: dateTimeTitle")
            throw e
        }
        return this
    }

    fun clickSelectDate(): DateTimePage {
        try {
            Timber.i("clicked select date button")
            dateButton().perform(click())
        } catch (e: Throwable) {
            Timber.e("FAILED to click select date button")
            throw e
        }
        return this
    }

    fun setDate(year: Int, month: Int, day: Int): DateTimePage {
        try {
            Timber.i("set date to: $day/$month/$year")
            onView(withClassName(`is`(DatePicker::class.java.name)))
                .perform(PickerActions.setDate(year, month, day))
            onView(withText("OK")).perform(click())
        } catch (e: Throwable) {
            Timber.e("FAILED to set date")
            throw e
        }
        return this
    }

    fun clickSelectTime(): DateTimePage {
        try {
            Timber.i("clicked select time button")
            timeButton().perform(click())
        } catch (e: Throwable) {
            Timber.e("FAILED to click select time button")
            throw e
        }
        return this
    }

    fun setTime(hours: Int, minutes: Int): DateTimePage {
        try {
            Timber.i("set time to: $hours:$minutes")
            onView(withClassName(`is`(TimePicker::class.java.name)))
                .perform(PickerActions.setTime(hours, minutes))
            onView(withText("OK")).perform(click())
        } catch (e: Throwable) {
            Timber.e("FAILED to set time")
            throw e
        }
        return this
    }

    fun clickTimeModeToggle(): DateTimePage {
        try {
            Timber.i("clicked time mode toggle")
            // The toggle button in the native TimePicker
            onView(withContentDescription(containsString("mode"))).perform(click())
        } catch (e: Throwable) {
            Timber.e("FAILED to click time mode toggle")
            throw e
        }
        return this
    }

    fun verifyRadialPickerDisplayed(): DateTimePage {
        try {
            Timber.d("located element: radialPicker")
            // Search for the radial picker view by class name or id if possible
            onView(withClassName(containsString("RadialTimePickerView"))).check(matches(isDisplayed()))
        } catch (e: Throwable) {
            Timber.e("FAILED to verify radial picker display")
            throw e
        }
        return this
    }

    fun verifyInputModeDisplayed(): DateTimePage {
        try {
            Timber.d("located element: inputMode")
            // Search for the text input picker view by class name or id if possible
            onView(withClassName(containsString("TextInputTimePickerView"))).check(matches(isDisplayed()))
        } catch (e: Throwable) {
            Timber.e("FAILED to verify input mode display")
            throw e
        }
        return this
    }

    fun verifyDateResult(expectedDate: String): DateTimePage {
        try {
            Timber.d("verified date result: $expectedDate")
            dateResult().check(matches(withText(containsString(expectedDate))))
        } catch (e: Throwable) {
            Timber.e("FAILED to verify date result")
            throw e
        }
        return this
    }

    fun verifyTimeResult(expectedTime: String): DateTimePage {
        try {
            Timber.d("verified time result: $expectedTime")
            timeResult().check(matches(withText(containsString(expectedTime))))
        } catch (e: Throwable) {
            Timber.e("FAILED to verify time result")
            throw e
        }
        return this
    }
}

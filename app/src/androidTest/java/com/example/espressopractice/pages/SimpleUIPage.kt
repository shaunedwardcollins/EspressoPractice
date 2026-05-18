package com.example.espressopractice.pages

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.espressopractice.R
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.containsString
import org.hamcrest.Matchers.instanceOf
import org.hamcrest.Matchers.`is`
import timber.log.Timber

class SimpleUIPage {

    private fun title() = onView(withId(R.id.tvSimpleUITitle))
    private fun inputField() = onView(withId(R.id.etInput))
    private fun submitButton() = onView(withId(R.id.btnSubmit))
    private fun checkbox() = onView(withId(R.id.cbOption))
    private fun radioButton1() = onView(withId(R.id.rbOption1))
    private fun radioButton2() = onView(withId(R.id.rbOption2))
    private fun switchToggle() = onView(withId(R.id.switchToggle))
    private fun spinner() = onView(withId(R.id.spinnerOptions))
    private fun seekBar() = onView(withId(R.id.seekBar))
    private fun resultText() = onView(withId(R.id.tvResult))

    fun verifyPageLoaded(): SimpleUIPage {
        try {
            Timber.d("located element: simpleUiTitle")
            title().check(matches(isDisplayed()))
            title().check(matches(withText(R.string.simple_ui_title)))
        } catch (e: Throwable) {
            Timber.e("FAILED to verify element: simpleUiTitle")
            throw e
        }
        return this
    }

    fun typeInput(text: String): SimpleUIPage {
        try {
            Timber.i("typed input: $text")
            inputField().perform(typeText(text), closeSoftKeyboard())
        } catch (e: Throwable) {
            Timber.e("FAILED to type input")
            throw e
        }
        return this
    }

    fun clickSubmit(): SimpleUIPage {
        try {
            Timber.i("clicked submit button")
            submitButton().perform(click())
        } catch (e: Throwable) {
            Timber.e("FAILED to click submit button")
            throw e
        }
        return this
    }

    fun clickCheckbox(): SimpleUIPage {
        try {
            Timber.i("clicked checkbox")
            checkbox().perform(click())
        } catch (e: Throwable) {
            Timber.e("FAILED to click checkbox")
            throw e
        }
        return this
    }

    fun selectRadioOption1(): SimpleUIPage {
        try {
            Timber.i("selected radio option 1")
            radioButton1().perform(click())
        } catch (e: Throwable) {
            Timber.e("FAILED to select radio option 1")
            throw e
        }
        return this
    }

    fun selectRadioOption2(): SimpleUIPage {
        try {
            Timber.i("selected radio option 2")
            radioButton2().perform(click())
        } catch (e: Throwable) {
            Timber.e("FAILED to select radio option 2")
            throw e
        }
        return this
    }

    fun toggleSwitch(): SimpleUIPage {
        try {
            Timber.i("toggled switch")
            switchToggle().perform(click())
        } catch (e: Throwable) {
            Timber.e("FAILED to toggle switch")
            throw e
        }
        return this
    }

    fun selectSpinnerOption(option: String): SimpleUIPage {
        try {
            Timber.i("selected spinner option: $option")
            spinner().perform(click())
            onData(allOf(`is`(instanceOf(String::class.java)), `is`(option))).perform(click())
        } catch (e: Throwable) {
            Timber.e("FAILED to select spinner option: $option")
            throw e
        }
        return this
    }

    fun setSliderValue(progress: Int): SimpleUIPage {
        try {
            Timber.i("set slider value to: $progress")
            seekBar().perform(setProgress(progress))
        } catch (e: Throwable) {
            Timber.e("FAILED to set slider value")
            throw e
        }
        return this
    }

    fun verifyResult(expectedText: String): SimpleUIPage {
        try {
            Timber.d("located element: resultText")
            resultText().check(matches(withText(containsString(expectedText))))
        } catch (e: Throwable) {
            Timber.e("FAILED to verify result text: expected $expectedText")
            throw e
        }
        return this
    }
    
    fun verifyCheckboxState(checked: Boolean): SimpleUIPage {
        try {
            Timber.d("verified checkbox is checked: $checked")
            if (checked) {
                checkbox().check(matches(isChecked()))
            } else {
                checkbox().check(matches(isNotChecked()))
            }
        } catch (e: Throwable) {
            Timber.e("FAILED to verify checkbox state")
            throw e
        }
        return this
    }

    /**
     * Custom ViewAction to set progress on a SeekBar.
     */
    private fun setProgress(progress: Int): ViewAction {
        return object : ViewAction {
            override fun getConstraints() = isAssignableFrom(android.widget.SeekBar::class.java)
            override fun getDescription() = "set progress on SeekBar to $progress"
            override fun perform(uiController: androidx.test.espresso.UiController?, view: android.view.View?) {
                (view as android.widget.SeekBar).progress = progress
            }
        }
    }
}

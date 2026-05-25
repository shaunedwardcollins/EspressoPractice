package com.example.espressopractice

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.espressopractice.pages.DateTimePage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DateTimeTest {

    @get:Rule
    val timberRule = TimberRule()

    private val dateTimePage = DateTimePage()

    @Test
    fun testDatePicker() {
        ActivityScenario.launch(DateTimeActivity::class.java).use {
            val year = 2024
            val month = 10
            val day = 15

            dateTimePage
                .verifyPageLoaded()
                .clickSelectDate()
                .setDate(year, month, day)
                .verifyDateResult("15/10/2024")
        }
    }

    @Test
    fun testTimePicker() {
        ActivityScenario.launch(DateTimeActivity::class.java).use {
            val hour = 14
            val minute = 30
            
            dateTimePage
                .verifyPageLoaded()
                .clickSelectTime()
                .setTime(hour, minute)
                .verifyTimeResult("14:30")
        }
    }

    @Test
    fun testTimePickerModeToggle() {
        ActivityScenario.launch(DateTimeActivity::class.java).use {
            dateTimePage
                .verifyPageLoaded()
                .clickSelectTime()
                .verifyRadialPickerDisplayed()
                .clickTimeModeToggle()
                .verifyInputModeDisplayed()
                .clickTimeModeToggle()
                .verifyRadialPickerDisplayed()
        }
    }
}
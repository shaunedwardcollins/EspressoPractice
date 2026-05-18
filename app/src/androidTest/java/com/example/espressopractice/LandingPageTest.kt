package com.example.espressopractice

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.espressopractice.pages.LandingPage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import timber.log.Timber

@RunWith(AndroidJUnit4::class)
class LandingPageTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    val timberRule = TimberRule()

    private val landingPage = LandingPage()

    @Test
    fun testLandingPageTitle() {
        Timber.i("Starting test: testLandingPageTitle")
        
        landingPage
            .verifyTitleIsDisplayed()
            .verifyTitleText()
            
        Timber.i("Test passed: Landing page title is verified")
    }

    @Test
    fun testLandingPageButton() {
        Timber.i("Starting test: testLandingPageButton")
        
        landingPage
            .verifyButtonIsDisplayed()
            .verifyButtonText()
            
        Timber.i("Test passed: Landing page button is verified")
    }
}

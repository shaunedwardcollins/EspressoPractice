package com.example.espressopractice

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.espressopractice.pages.LandingPage
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import timber.log.Timber

@RunWith(AndroidJUnit4::class)
class LandingPageTest {

    @get:Rule
    val timberRule = TimberRule()

    private val landingPage = LandingPage()

    @Before
    fun setUp() {
        Timber.i("Pre-test setup: Injecting logged-in state")
        // We set the state in SharedPreferences BEFORE launching the activity
        AuthManager.login(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun testLandingPageTitle() {
        ActivityScenario.launch(MainActivity::class.java).use {
            landingPage
                .verifyTitleIsDisplayed()
                .verifyTitleText()
        }
    }

    @Test
    fun testLandingPageButton() {
        ActivityScenario.launch(MainActivity::class.java).use {
            landingPage
                .verifyButtonIsDisplayed()
                .verifyButtonText()
        }
    }
}

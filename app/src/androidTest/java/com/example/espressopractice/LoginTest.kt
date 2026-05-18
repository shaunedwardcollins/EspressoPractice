package com.example.espressopractice

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.espressopractice.pages.LandingPage
import com.example.espressopractice.pages.LoginPage
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import timber.log.Timber

@RunWith(AndroidJUnit4::class)
class LoginTest {

    @get:Rule
    val timberRule = TimberRule()

    private val loginPage = LoginPage()
    private val landingPage = LandingPage()

    @Before
    fun setUp() {
        Timber.i("Resetting login state")
        AuthManager.logout(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun testRedirectToLogin() {
        // launch MainActivity specifically to test that the REDIRECT happens
        ActivityScenario.launch(MainActivity::class.java).use {
            loginPage.verifyPageLoaded()
        }
    }

    @Test
    fun testSuccessfulLogin() {
        // launch LoginActivity DIRECTLY to test the LOGIN flow
        ActivityScenario.launch(LoginActivity::class.java).use {
            loginPage
                .verifyPageLoaded()
                .enterUsername("admin")
                .enterPassword("password")
                .clickLogin()
            landingPage.verifyTitleIsDisplayed()
        }
    }

    @Test
    fun testFailedLogin() {
        ActivityScenario.launch(LoginActivity::class.java).use {
            loginPage
                .verifyPageLoaded()
                .enterUsername("wrongUser")
                .enterPassword("wrongPassword")
                .clickLogin()
                .verifyErrorDisplayed()
        }
    }
}

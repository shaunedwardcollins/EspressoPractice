package com.example.espressopractice

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.espressopractice.pages.SimpleUIPage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SimpleUITest {

    @get:Rule
    val timberRule = TimberRule()

    private val simpleUiPage = SimpleUIPage()

    @Test
    fun testTextInputAndSubmit() {
        ActivityScenario.launch(SimpleUIActivity::class.java).use {
            simpleUiPage
                .verifyPageLoaded()
                .typeInput("Espresso Test")
                .clickSubmit()
                .verifyResult("Submitted: Espresso Test")
        }
    }

    @Test
    fun testCheckboxToggle() {
        ActivityScenario.launch(SimpleUIActivity::class.java).use {
            simpleUiPage
                .verifyPageLoaded()
                .verifyCheckboxState(false)
                .clickCheckbox()
                .verifyCheckboxState(true)
                .verifyResult("Checkbox is Checked")
                .clickCheckbox()
                .verifyCheckboxState(false)
                .verifyResult("Checkbox is Unchecked")
        }
    }

    @Test
    fun testRadioButtons() {
        ActivityScenario.launch(SimpleUIActivity::class.java).use {
            simpleUiPage
                .verifyPageLoaded()
                .selectRadioOption1()
                .verifyResult("Selected: Option 1")
                .selectRadioOption2()
                .verifyResult("Selected: Option 2")
        }
    }

    @Test
    fun testSwitchToggle() {
        ActivityScenario.launch(SimpleUIActivity::class.java).use {
            simpleUiPage
                .verifyPageLoaded()
                .toggleSwitch()
                .verifyResult("Switch is ON")
                .toggleSwitch()
                .verifyResult("Switch is OFF")
        }
    }

    @Test
    fun testSpinnerSelection() {
        ActivityScenario.launch(SimpleUIActivity::class.java).use {
            simpleUiPage
                .verifyPageLoaded()
                .selectSpinnerOption("Option B")
                .verifyResult("Spinner: Option B")
                .selectSpinnerOption("Option C")
                .verifyResult("Spinner: Option C")
        }
    }

    @Test
    fun testSliderInteraction() {
        ActivityScenario.launch(SimpleUIActivity::class.java).use {
            simpleUiPage
                .verifyPageLoaded()
                .setSliderValue(45)
                .verifyResult("Slider: 45")
                .setSliderValue(80)
                .verifyResult("Slider: 80")
        }
    }
}

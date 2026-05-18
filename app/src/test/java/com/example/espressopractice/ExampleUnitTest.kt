package com.example.espressopractice

import org.junit.Test
import org.junit.Assert.*
import org.junit.Rule
import timber.log.Timber

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @get:Rule
    val timberRule = TimberRule()

    @Test
    fun addition_isCorrect() {
        Timber.d("Starting addition test")
        assertEquals(4, 2 + 2)
        Timber.d("Addition test completed successfully")
    }
}

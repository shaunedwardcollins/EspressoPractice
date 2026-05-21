package com.example.espressopractice.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.espressopractice.ProductAdapter
import com.example.espressopractice.R
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import android.view.View
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.containsString

import timber.log.Timber

class ProductListPage {

    fun verifyPageLoaded(): ProductListPage {
        try {
            Timber.d("located element: productListTitle")
            onView(withText(R.string.product_list_title))
                .check(matches(isDisplayed()))
        } catch (e: Throwable) {
            Timber.e("FAILED to verify element: productListTitle")
            throw e
        }
        return this
    }

    fun verifyProductDisplayed(productName: String): ProductListPage {
        try {
            Timber.d("located product: $productName")
            onView(withText(productName))
                .check(matches(isDisplayed()))
        } catch (e: Throwable) {
            Timber.e("FAILED to verify product displayed: $productName")
            throw e
        }
        return this
    }

    fun scrollToProduct(productName: String): ProductListPage {
        try {
            Timber.i("scrolled to product: $productName")
            onView(withId(R.id.rvProducts))
                .perform(RecyclerViewActions.scrollTo<ProductAdapter.ProductViewHolder>(
                    hasDescendant(withText(productName))
                ))
        } catch (e: Throwable) {
            Timber.e("FAILED to scroll to product: $productName")
            throw e
        }
        return this
    }

    fun clickAddToCart(productName: String): ProductListPage {
        try {
            Timber.i("clicked add to cart for product: $productName")
            onView(withId(R.id.rvProducts))
                .perform(
                    RecyclerViewActions.actionOnItem<ProductAdapter.ProductViewHolder>(
                        hasDescendant(withText(productName)),
                        clickChildViewWithId(R.id.btnAddToCart)
                    )
                )
        } catch (e: Throwable) {
            Timber.e("FAILED to click add to cart for product: $productName")
            throw e
        }
        return this
    }

    // Helper function to click a view with a specific ID inside a RecyclerView item
    private fun clickChildViewWithId(id: Int): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View?> {
                return allOf(isDisplayed(), isClickable()) // Ensure the view is displayed and clickable
            }

            override fun getDescription(): String {
                return "Click on a child view with specified ID."
            }

            override fun perform(uiController: UiController?, view: View?) {
                val v = view?.findViewById<View>(id)
                v?.performClick()
            }
        }
    }

    fun verifyResult(expectedText: String): ProductListPage {
        try {
            Timber.d("verified result text: $expectedText")
            onView(withId(R.id.tvListResult))
                .check(matches(withText(containsString(expectedText))))
        } catch (e: Throwable) {
            Timber.e("FAILED to verify result text: expected $expectedText")
            throw e
        }
        return this
    }

    fun verifyResultContains(expectedPartialText: String): ProductListPage {
        try {
            Timber.d("verified result text contains: $expectedPartialText")
            onView(withId(R.id.tvListResult))
                .check(matches(withText(containsString(expectedPartialText))))
        } catch (e: Throwable) {
            Timber.e("FAILED to verify result text contains: $expectedPartialText")
            throw e
        }
        return this
    }

    fun verifyProductPrice(productName: String, expectedPrice: String): ProductListPage {
        try {
            Timber.d("verified product price: $productName - $expectedPrice")
            scrollToProduct(productName)
            onView(allOf(
                withText(expectedPrice),
                isDescendantOfA(hasDescendant(withText(productName)))
            ))
                .check(matches(isDisplayed()))
        } catch (e: Throwable) {
            Timber.e("FAILED to verify product price: $productName")
            throw e
        }
        return this
    }
}

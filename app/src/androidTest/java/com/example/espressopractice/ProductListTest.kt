package com.example.espressopractice

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.espressopractice.pages.ProductListPage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ProductListTest {

    @get:Rule
    val timberRule = TimberRule()

    private val productListPage = ProductListPage()

    @Test
    fun testAllProductsAndPricesAreCorrect() {
        ActivityScenario.launch(ProductListActivity::class.java).use {
            val products = ProductRepository.getProducts()

            productListPage.verifyPageLoaded()

            products.forEach { product ->
                productListPage
                    .scrollToProduct(product.name)
                    .verifyProductDisplayed(product.name)
                    .verifyProductPrice(product.name, product.price)
            }
        }
    }

    @Test
    fun testAddSingleProductToCart() {
        ActivityScenario.launch(ProductListActivity::class.java).use {
            productListPage
                .verifyPageLoaded()
                .clickAddToCart("Laptop")
                .verifyResult("Added Laptop to cart")
        }
    }

    @Test
    fun testAddMultipleProductsToCart() {
        ActivityScenario.launch(ProductListActivity::class.java).use {
            productListPage
                .verifyPageLoaded()
                .clickAddToCart("Laptop")
                .verifyResult("Added Laptop to cart")
                .clickAddToCart("Smartphone")
                .verifyResultContains("Added Laptop to cart, Smartphone")
                .clickAddToCart("E-Reader")
                .verifyResultContains("Added Laptop to cart, Smartphone, E-Reader")
        }
    }

    @Test
    fun testInitialResultPlaceholder() {
        ActivityScenario.launch(ProductListActivity::class.java).use {
            productListPage
                .verifyPageLoaded()
                .verifyResult("Interaction Result")
        }
    }
}

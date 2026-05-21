package com.example.espressopractice

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import timber.log.Timber

class ProductListActivity : AppCompatActivity() {

    private lateinit var rvProducts: RecyclerView
    private lateinit var tvListResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_product_list)

        rvProducts = findViewById(R.id.rvProducts)
        tvListResult = findViewById(R.id.tvListResult)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.product_list_layout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupRecyclerView()
        loadProducts()
        Timber.d("ProductListActivity created and product list displayed")
    }

    private fun setupRecyclerView() {
        rvProducts.layoutManager = LinearLayoutManager(this)
        // Adapter will be set here once it's created
    }

    private fun loadProducts() {
        val products = ProductRepository.getProducts()

        val adapter = ProductAdapter(products) { product ->
            // Handle add to cart click, e.g., update tvListResult
            val currentText = tvListResult.text.toString()
            if (currentText == getString(R.string.result_placeholder)) {
                tvListResult.text = "Added ${product.name} to cart"
            } else {
                tvListResult.text = "$currentText, ${product.name}"
            }
            Timber.d("Added ${product.name} to cart. Current list result: ${tvListResult.text}")
        }
        rvProducts.adapter = adapter
    }
}

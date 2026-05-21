package com.example.espressopractice

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        if (!AuthManager.isLoggedIn(this)) {
            Timber.d("User not logged in, redirecting to LoginActivity")
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
            return
        }

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<Button>(R.id.btnSimpleUI).setOnClickListener {
            Timber.d("Simple UI button clicked")
            val intent = Intent(this, SimpleUIActivity::class.java)
            startActivity(intent)
        }

        Timber.d("MainActivity created and landing page displayed")

        findViewById<Button>(R.id.btnProductList).setOnClickListener {
            Timber.d("Product List button clicked")
            val intent = Intent(this, ProductListActivity::class.java)
            startActivity(intent)
        }
    }
}

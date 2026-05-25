package com.example.espressopractice

/**
 * * A mock database/repository acting as a single source of truth for product data.
 */
object ProductRepository {
    private val products = listOf(
        Product(1, "Laptop", "$1200.00"),
        Product(2, "Smartphone", "$800.00"),
        Product(3, "Headphones", "$150.00"),
        Product(4, "Smartwatch", "$250.00"),
        Product(5, "Tablet", "$500.00"),
        Product(6, "Gaming Console", "$400.00"),
        Product(7, "E-Reader", "$100.00"),
        Product(8, "Bluetooth Speaker", "$75.00"),
        Product(9, "Wireless Earbuds", "$120.00"),
        Product(10, "External Hard Drive", "$90.00"),
        Product(11, "Monitor", "$300.00"),
        Product(12, "Keyboard", "$70.00"),
        Product(13, "Mouse", "$30.00"),
        Product(14, "Webcam", "$50.00"),
        Product(15, "Printer", "$200.00")
    )

    fun getProducts(): List<Product> = products
    
    fun getProductByName(name: String): Product? {
        return products.find { it.name == name }
    }
}
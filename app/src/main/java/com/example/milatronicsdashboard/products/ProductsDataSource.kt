package com.example.milatronicsdashboard.products

import com.example.milatronicsdashboard.R

class ProductsDataSource {
    fun loadProducts(): List<Product> {
        return listOf<Product>(
            Product(R.string.product_1_title, R.drawable.product_1_img, R.string.product_1_description),
            Product(R.string.product_2_title, R.drawable.product_2_img, R.string.product_2_description),
            Product(R.string.product_3_title, R.drawable.product_3_img, R.string.product_3_description),
        )
    }
}
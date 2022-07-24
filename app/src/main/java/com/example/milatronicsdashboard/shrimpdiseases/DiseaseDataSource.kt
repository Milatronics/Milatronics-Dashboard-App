package com.example.milatronicsdashboard.shrimpdiseases

import com.example.milatronicsdashboard.R

class DiseaseDataSource {
    fun loadProducts(): List<Disease> {
        return listOf<Disease>(
            Disease(R.string.product_2_title, R.drawable.product_2_img, R.string.product_2_description),
            Disease(R.string.product_3_title, R.drawable.product_3_img, R.string.product_3_description),
            Disease(R.string.product_1_title, R.drawable.product_1_img, R.string.product_1_description),
        )
    }
}
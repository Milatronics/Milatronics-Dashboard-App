package com.example.milatronicsdashboard.shrimpdiseases

import com.example.milatronicsdashboard.R

class DiseaseDataSource {
    fun loadProducts(): List<Disease> {
        return listOf(
            Disease(R.string.disease_1_title, R.drawable.disease_1_img, R.string.disease_1_description),
            Disease(R.string.disease_3_title, R.drawable.disease_3_img, R.string.disease_3_description),
            Disease(R.string.disease_8_title, R.drawable.disease_8_img, R.string.disease_8_description),
            Disease(R.string.disease_2_title, R.drawable.disease_2_img, R.string.disease_2_description),
            Disease(R.string.disease_4_title, R.drawable.disease_4_img, R.string.disease_4_description),
            Disease(R.string.disease_5_title, R.drawable.disease_5_img, R.string.disease_5_description),
            Disease(R.string.disease_6_title, R.drawable.disease_6_img, R.string.disease_6_description),
            Disease(R.string.disease_7_title, R.drawable.disease_7_img, R.string.disease_7_description),
        )
    }
}
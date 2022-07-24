package com.example.milatronicsdashboard.shrimpfarmingtips

import com.example.milatronicsdashboard.R

class TipsDataSource {
    fun loadProducts(): List<Tip> {
        return listOf<Tip>(
            Tip(R.string.product_3_title, R.drawable.product_3_img, R.string.product_3_description),
            Tip(R.string.product_2_title, R.drawable.product_2_img, R.string.product_2_description),
            Tip(R.string.product_1_title, R.drawable.product_1_img, R.string.product_1_description),
            Tip(R.string.product_2_title, R.drawable.product_2_img, R.string.product_2_description),
            Tip(R.string.product_1_title, R.drawable.product_1_img, R.string.product_1_description),
            Tip(R.string.product_3_title, R.drawable.product_3_img, R.string.product_3_description),
            Tip(R.string.product_2_title, R.drawable.product_2_img, R.string.product_2_description),
            Tip(R.string.product_1_title, R.drawable.product_1_img, R.string.product_1_description),
            Tip(R.string.product_3_title, R.drawable.product_3_img, R.string.product_3_description),
        )
    }
}
package com.example.milatronicsdashboard.shrimpfarmingtips

import com.example.milatronicsdashboard.R

class TipsDataSource {
    fun loadProducts(): List<Tip> {
        return listOf(
            Tip(R.string.tip_10_title, R.drawable.tip_10_img, R.string.tip_10_description),
            Tip(R.string.tip_9_title, R.drawable.tip_9_img, R.string.tip_9_description),
            Tip(R.string.tip_8_title, R.drawable.tip_8_img, R.string.tip_8_description),
            Tip(R.string.tip_7_title, R.drawable.tip_7_img, R.string.tip_7_description),
            Tip(R.string.tip_6_title, R.drawable.tip_6_img, R.string.tip_6_description),
            Tip(R.string.tip_5_title, R.drawable.tip_5_img, R.string.tip_5_description),
            Tip(R.string.tip_4_title, R.drawable.tip_4_img, R.string.tip_4_description),
            Tip(R.string.tip_3_title, R.drawable.tip_3_img, R.string.tip_3_description),
            Tip(R.string.tip_2_title, R.drawable.tip_2_img, R.string.tip_2_description),
            Tip(R.string.tip_1_title, R.drawable.tip_1_img, R.string.tip_1_description),
        )
    }
}
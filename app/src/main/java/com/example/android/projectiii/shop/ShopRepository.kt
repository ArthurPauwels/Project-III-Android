package com.example.android.projectiii.shop

import com.example.android.projectiii.R

class ShopRepository {

    fun getShopItems(): List<ShopItem> {
        return listOf(
            ShopItem(
                "1",
                "25% off Basic Fit Membership",
                90,
                R.drawable.basic_fit
            ),
            ShopItem(
                "2",
                "One free apple",
                30,
                R.drawable.apple
            )
        )
    }
}
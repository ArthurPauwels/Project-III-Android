package com.example.android.projectiii.shop

import androidx.lifecycle.ViewModel


class ShopViewModel(private val shopRepository: ShopRepository) : ViewModel() {
    var items: List<ShopItem> = shopRepository.getShopItems()
}
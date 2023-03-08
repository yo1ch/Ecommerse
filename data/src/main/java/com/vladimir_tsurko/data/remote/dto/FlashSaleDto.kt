package com.vladimir_tsurko.data.remote.dto

data class FlashSaleDto(
    val category: String,
    val discount: Int,
    val image_url: String,
    val name: String,
    val price: Double
)
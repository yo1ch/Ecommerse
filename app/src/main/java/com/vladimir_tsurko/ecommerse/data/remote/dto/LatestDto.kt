package com.vladimir_tsurko.ecommerse.data.remote.dto

data class LatestDto(
    val category: String,
    val image_url: String,
    val name: String,
    val price: Int
)
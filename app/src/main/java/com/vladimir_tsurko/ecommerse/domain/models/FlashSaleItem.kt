package com.vladimir_tsurko.ecommerse.domain.models

import com.vladimir_tsurko.ecommerse.domain.models.base.ListItem

data class FlashSaleItem(
    val id: Long,
    val name: String,
    val price: Double,
    val image: String,
    val discount: Int,
    val category: String,
): ListItem {
    override val itemId: Long = id
}
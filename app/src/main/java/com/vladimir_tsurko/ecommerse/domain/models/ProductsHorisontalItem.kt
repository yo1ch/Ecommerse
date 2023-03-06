package com.vladimir_tsurko.ecommerse.domain.models

import com.vladimir_tsurko.ecommerse.domain.models.base.ListItem

data class ProductsHorisontalItem(
    val title: String,
    val products: List<ListItem>
): ListItem {
    override val itemId: Long = title.hashCode().toLong()
}
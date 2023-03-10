package com.vladimir_tsurko.domain.models

import com.vladimir_tsurko.domain.models.base.ListItem

data class BrandsItem(
    val id: Long,
    val name: String,
    val imageUrl: String,
): ListItem {
    override val itemId: Long = id
}
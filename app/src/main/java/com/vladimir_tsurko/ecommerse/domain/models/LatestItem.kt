package com.vladimir_tsurko.ecommerse.domain.models

import com.vladimir_tsurko.ecommerse.domain.models.base.ListItem

data class LatestItem(
    val id: Long,
    val title: String,
): ListItem {
    override val itemId: Long = id
}
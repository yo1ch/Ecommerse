package com.vladimir_tsurko.ecommerse.domain.models

data class ColorModel(
    val id: Int,
    val color: String,
    var isSelected: Boolean = false
)

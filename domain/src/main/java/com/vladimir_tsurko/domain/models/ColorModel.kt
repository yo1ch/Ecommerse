package com.vladimir_tsurko.domain.models

import com.google.gson.Gson

data class ColorModel(
    val id: Int,
    val color: String,
    var isSelected: Boolean = false
){
    fun deepCopy() : ColorModel {
        return Gson().fromJson(Gson().toJson(this), this.javaClass)
    }
}


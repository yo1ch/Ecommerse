package com.vladimir_tsurko.ecommerse.presentation.adapters.colorsAdapter

import androidx.recyclerview.widget.DiffUtil
import com.vladimir_tsurko.domain.models.ColorModel

object ColorsDiffUtilCallback: DiffUtil.ItemCallback<ColorModel>() {

    override fun areItemsTheSame(oldItem: ColorModel, newItem: ColorModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ColorModel, newItem: ColorModel): Boolean {
        return oldItem == newItem
    }
}
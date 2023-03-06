package com.vladimir_tsurko.ecommerse.presentation.adapters.categoriesAdapter

import androidx.recyclerview.widget.DiffUtil
import com.vladimir_tsurko.ecommerse.domain.models.CategoryModel
import com.vladimir_tsurko.ecommerse.domain.models.ColorModel

object CategoriesDiffUtillCallback: DiffUtil.ItemCallback<CategoryModel>() {

    override fun areItemsTheSame(oldItem: CategoryModel, newItem: CategoryModel): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: CategoryModel, newItem: CategoryModel): Boolean {
        return oldItem == newItem
    }
}
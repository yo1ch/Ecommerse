package com.vladimir_tsurko.ecommerse.presentation.adapters.categoriesAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.vladimir_tsurko.domain.models.CategoryModel
import com.vladimir_tsurko.ecommerse.databinding.CategoryListItemBinding

class CategoriesAdapter() : ListAdapter<CategoryModel, CategoriesViewHolder>(CategoriesDiffUtillCallback) {

    var onItemClickListener: (() -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val binding = CategoryListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return CategoriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding){
            categoryImage.setImageResource(item.image)
            categoryText.text = item.name
            root.setOnClickListener {
                onItemClickListener?.invoke()
            }
        }

    }


}
package com.vladimir_tsurko.ecommerse.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.vladimir_tsurko.domain.models.base.ListItem


open class DiffUtilItemCallback: DiffUtil.ItemCallback<ListItem>() {
    override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
        return oldItem.itemId == newItem.itemId
    }

    override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
        return oldItem.equals(newItem)
    }
}
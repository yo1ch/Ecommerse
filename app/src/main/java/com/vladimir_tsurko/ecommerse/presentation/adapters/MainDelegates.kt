package com.vladimir_tsurko.ecommerse.presentation.adapters

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.vladimir_tsurko.ecommerse.databinding.HorisontalProductsItemBinding
import com.vladimir_tsurko.ecommerse.databinding.ItemLatestBinding
import com.vladimir_tsurko.ecommerse.domain.models.LatestItem
import com.vladimir_tsurko.ecommerse.domain.models.ProductsHorisontalItem
import com.vladimir_tsurko.ecommerse.domain.models.base.ListItem

object MainDelegates {

    val horizontalProductsDelegate = adapterDelegateViewBinding<ProductsHorisontalItem, ListItem, HorisontalProductsItemBinding>(
        { inflater, container ->
            HorisontalProductsItemBinding.inflate(inflater, container, false)
        }
    ){
        val adapter = ProductsAdapter()
        binding.recyclerView.adapter = adapter
        bind {
            binding.titleTextView.text = item.title
            adapter.items = item.products
        }
    }

    val latestProductsDelegate = adapterDelegateViewBinding<LatestItem, ListItem, ItemLatestBinding>(
        { inflater, container ->
            ItemLatestBinding.inflate(inflater, container, false)
        }
    ){
        bind{
            binding.titleTextView.text = item.title
        }
    }

}
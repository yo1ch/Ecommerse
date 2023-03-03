package com.vladimir_tsurko.ecommerse.presentation.adapters

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.vladimir_tsurko.ecommerse.databinding.HorisontalProductsItemBinding
import com.vladimir_tsurko.ecommerse.databinding.ItemFlashSaleBinding
import com.vladimir_tsurko.ecommerse.databinding.ItemLatestBinding
import com.vladimir_tsurko.ecommerse.domain.models.FlashSaleItem
import com.vladimir_tsurko.ecommerse.domain.models.LatestItem
import com.vladimir_tsurko.ecommerse.domain.models.ProductsHorisontalItem
import com.vladimir_tsurko.ecommerse.domain.models.base.ListItem

object MainDelegates {



    fun horizontalProductsDelegate(
        onClick: ()->Unit
    ) = adapterDelegateViewBinding<ProductsHorisontalItem, ListItem, HorisontalProductsItemBinding>(
        { inflater, container ->
            HorisontalProductsItemBinding.inflate(inflater, container, false)
        }
    ){
        val adapter = ProductsAdapter(
            onClick
        )
        binding.recyclerView.adapter = adapter
        bind {
            binding.titleTextView.text = item.title
            adapter.items = item.products
        }
    }

    fun latestProductsDelegate(
        onClick: () -> Unit
    ) = adapterDelegateViewBinding<LatestItem, ListItem, ItemLatestBinding>(
        { inflater, container ->
            ItemLatestBinding.inflate(inflater, container, false)

        }
    ){
        bind{
            binding.root.setOnClickListener { onClick.invoke() }
            binding.titleTextView.text = item.price.toString()
            binding.typeTextView.text = item.category
            binding.modelTextView.text = item.name


        }
    }

    fun flashSaleProductsDelegate(
        onClick: () -> Unit
    ) = adapterDelegateViewBinding<FlashSaleItem, ListItem, ItemFlashSaleBinding>(
        { inflater, container ->
            ItemFlashSaleBinding.inflate(inflater, container, false)

        }
    ){
        bind{
            binding.root.setOnClickListener { onClick.invoke() }
            binding.titleTextView.text = item.title

        }
    }

}
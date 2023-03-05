package com.vladimir_tsurko.ecommerse.presentation.adapters

import com.bumptech.glide.Glide
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.vladimir_tsurko.ecommerse.databinding.HorisontalProductsItemBinding
import com.vladimir_tsurko.ecommerse.databinding.ItemFlashSaleBinding
import com.vladimir_tsurko.ecommerse.databinding.ItemFlashSalePlaceholderBinding
import com.vladimir_tsurko.ecommerse.databinding.ItemLatestBinding
import com.vladimir_tsurko.ecommerse.databinding.ItemLatestPlaceholderBinding
import com.vladimir_tsurko.ecommerse.domain.models.FlashSaleItem
import com.vladimir_tsurko.ecommerse.domain.models.LatestItem
import com.vladimir_tsurko.ecommerse.domain.models.ProductsHorisontalItem
import com.vladimir_tsurko.ecommerse.domain.models.base.FlashSalePlaceholder
import com.vladimir_tsurko.ecommerse.domain.models.base.LatestPlaceholder
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
            binding.titleTv.text = item.title
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
            binding.priceTextView.text = item.price.toString()
            binding.categoryTextView.text = item.category
            binding.nameTextView.text = item.name
            Glide.with(context)
                .load(item.image)
                .into(binding.imageView)


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
            binding.priceTextView.text = item.price.toString()
            binding.categoryTextView.text = item.category
            binding.nameTextView.text = item.name
            Glide.with(context)
                .load(item.image)
                .into(binding.imageView)
        }
    }

    fun latestPlaceholder() = adapterDelegateViewBinding<LatestPlaceholder, ListItem, ItemLatestPlaceholderBinding>(
        { inflater, container ->
            ItemLatestPlaceholderBinding.inflate(inflater, container,false)
        }
    ){}
    fun flashSalePlaceHolder() = adapterDelegateViewBinding<FlashSalePlaceholder, ListItem, ItemFlashSalePlaceholderBinding>(
        { inflater, container ->
            ItemFlashSalePlaceholderBinding.inflate(inflater, container,false)
        }
    ){}

}
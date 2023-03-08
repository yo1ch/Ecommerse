package com.vladimir_tsurko.ecommerse.presentation.adapters

import com.bumptech.glide.Glide
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.vladimir_tsurko.domain.models.BrandsItem
import com.vladimir_tsurko.domain.models.FlashSaleItem
import com.vladimir_tsurko.domain.models.LatestItem
import com.vladimir_tsurko.domain.models.ProductsHorisontalItem
import com.vladimir_tsurko.domain.models.base.BrandsPlaceHolder
import com.vladimir_tsurko.domain.models.base.FlashSalePlaceholder
import com.vladimir_tsurko.domain.models.base.LatestPlaceholder
import com.vladimir_tsurko.domain.models.base.ListItem
import com.vladimir_tsurko.ecommerse.databinding.*

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

    fun latestProductsDelegate() = adapterDelegateViewBinding<LatestItem, ListItem, ItemLatestBinding>(
        { inflater, container ->
            ItemLatestBinding.inflate(inflater, container, false)

        }
    ){
        bind{
            binding.priceTextView.text = "$${item.price.toString()}"
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
            binding.priceTextView.text = "$${item.price.toString()}"
            binding.categoryTextView.text = item.category
            binding.nameTextView.text = item.name
            binding.saleTextView.text = "${item.discount}% off"
            Glide.with(context)
                .load(item.image)
                .into(binding.imageView)
        }
    }

    fun brandsProductsDelegate() = adapterDelegateViewBinding<BrandsItem, ListItem, ItemBrandsBinding>(
        { inflater, container ->
            ItemBrandsBinding.inflate(inflater, container, false)
        }
    ){
        bind {
            binding.nameTextView.text = item.name
            Glide.with(context)
                .load(item.imageUrl)
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

    fun brandsPlaceholder() = adapterDelegateViewBinding<BrandsPlaceHolder, ListItem, ItemBrandsPlaceholderBinding>(
        { inflater, container ->
            ItemBrandsPlaceholderBinding.inflate(inflater, container, false)
        }
    ){}

}
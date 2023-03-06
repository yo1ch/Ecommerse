package com.vladimir_tsurko.ecommerse.presentation.adapters

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.vladimir_tsurko.ecommerse.domain.models.base.ListItem

class ProductsAdapter(
    onClick: () -> Unit
): AsyncListDifferDelegationAdapter<ListItem>(DiffUtilItemCallback()) {

    init{
        delegatesManager.addDelegate(MainDelegates.latestProductsDelegate())
            .addDelegate(MainDelegates.flashSaleProductsDelegate(onClick))
            .addDelegate(MainDelegates.brandsProductsDelegate())
            .addDelegate(MainDelegates.latestPlaceholder())
            .addDelegate(MainDelegates.flashSalePlaceHolder())
            .addDelegate(MainDelegates.brandsPlaceholder())
    }

}
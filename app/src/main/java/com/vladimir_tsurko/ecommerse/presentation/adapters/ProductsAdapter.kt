package com.vladimir_tsurko.ecommerse.presentation.adapters

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.vladimir_tsurko.ecommerse.domain.models.base.ListItem

class ProductsAdapter(
    onClick: () -> Unit
): AsyncListDifferDelegationAdapter<ListItem>(DiffUtilItemCallback()) {

    init{
        delegatesManager.addDelegate(MainDelegates.latestProductsDelegate(onClick))
            .addDelegate(MainDelegates.flashSaleProductsDelegate(onClick))
    }

}
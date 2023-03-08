package com.vladimir_tsurko.ecommerse.presentation.adapters

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.vladimir_tsurko.domain.models.base.ListItem

class MainScreenAdapter(
    onClickListener: ()->Unit
): AsyncListDifferDelegationAdapter<ListItem>(DiffUtilItemCallback()) {

    init{
        delegatesManager.addDelegate(MainDelegates.horizontalProductsDelegate(onClickListener))
    }


}
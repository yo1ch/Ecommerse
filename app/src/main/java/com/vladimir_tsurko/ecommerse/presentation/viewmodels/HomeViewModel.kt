package com.vladimir_tsurko.ecommerse.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vladimir_tsurko.ecommerse.domain.models.LatestItem
import com.vladimir_tsurko.ecommerse.domain.models.ProductsHorisontalItem
import com.vladimir_tsurko.ecommerse.domain.models.base.ListItem
import javax.inject.Inject

class HomeViewModel @Inject constructor(

): ViewModel() {

    private val _data = MutableLiveData<List<ListItem>>()
    val data: LiveData<List<ListItem>> = _data

    init{
        val items = getItems()
        _data.postValue(items)
    }

    private fun getItems(): List<ListItem>{
        return listOf(
            ProductsHorisontalItem(
                title = "Latest",
                products = IntRange(1,20).map{LatestItem(it.toLong(), "Product title $it")}
            )
        )



    }

}